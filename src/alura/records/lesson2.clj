(ns alura.records.lesson2
  (:import (java.time LocalDateTime ZoneId)))

(defprotocol Dateable
  (to-ms [this]))

(extend-type Number Dateable
  (to-ms [this] this))

(extend-type LocalDateTime Dateable
  (to-ms [this]
    (let [zone (ZoneId/systemDefault)]
      (-> this
          (.atZone zone)
          .toInstant
          .toEpochMilli))))

(extend-type String Dateable
  (to-ms [this]
    (-> this
        LocalDateTime/parse
        to-ms)))

(defrecord Patient [id name birth-date])
(defrecord PrivatePatient [id name birth-date])
(defrecord InsuredPatient [id name birth-date health-insurance])

(defn needs-authorization?
  [patient exam cost]
  (if (= PrivatePatient (type patient))
    (>= cost 50)
    (if (= InsuredPatient (type patient))
      (let [covered-exams (:health-insurance patient [])]
        (not (some #(= % exam) covered-exams)))
      true)))

(defprotocol Authorizable
  (patient-needs-authorization? [patient exam cost]))

(extend-type PrivatePatient Authorizable
  (patient-needs-authorization? [_patient _exam cost]
    (>= cost 50)))

(extend-type InsuredPatient Authorizable
  (patient-needs-authorization? [patient exam _cost]
    (let [covered-exams (:health-insurance patient [])]
      (not (some #(= % exam) covered-exams)))))

(extend-type Patient Authorizable
  (patient-needs-authorization? [_patient _exam _cost]
    true))