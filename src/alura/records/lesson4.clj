(ns alura.records.lesson4
  (:import (alura.records.lesson2 PrivatePatient InsuredPatient)))

(defmulti needs-authorization-multi? class)

(defmethod needs-authorization-multi?
  PrivatePatient
  [patient]
  true)

(defmethod needs-authorization-multi?
  InsuredPatient
  [patient]
  false)

(defn print-and-get-class
  [arg]
  (print arg)
  (class arg))

(defmulti multi-test print-and-get-class)
(defmethod multi-test
  (class [])
  [arg]
  (count arg))

(defn authorizer-type
  [{patient :patient
    situation :situation}]
  (let [urgent? (= :urgent situation)]
    (if urgent?
      :always-authorize
      (class patient))))

(defmulti needs-authorization-for-request? authorizer-type)

(defmethod needs-authorization-for-request?
  PrivatePatient
  [{cost :cost}]
  (>= cost 50))

(defmethod needs-authorization-for-request?
  InsuredPatient
  [{{health-insurance :health-insurance} :patient
    exam :exam}]
  (not-any? #(= % exam) health-insurance))

(defmethod needs-authorization-for-request?
  :always-authorize
  [request]
  false)