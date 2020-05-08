(ns alura.lesson1-test
  (:require [clojure.test :refer :all])
  (:require [alura.lesson1 :refer :all])
  (:import (clojure.lang ExceptionInfo)
           (alura.lesson1 Patient)))

(def ID 1)
(def NAME "Name")
(def BIRTH-DATE "01/01/1950")
(def DOG-NAME "Fluffy")

(deftest record-test
  (testing "Records"
    (testing "with positional construction"
      (let [patient (Patient. ID NAME BIRTH-DATE)]
        (is (= ID (:id patient)))
        (is (= NAME (:name patient)))
        (is (= BIRTH-DATE (:birth-date patient)))))
    (testing "with map construction"
      (testing "passing extra attributes"
        (let [patient (map->Patient
                        {:id         ID
                         :name       NAME
                         :birth-date BIRTH-DATE
                         :dog-name   DOG-NAME})]
          (is (= DOG-NAME (:dog-name patient)))))
      (testing "leaving attributes out"
        (let [patient (map->Patient {:birth-date BIRTH-DATE})]
          (is (nil? (:id patient)))
          (is (nil? (:name patient))))))
    (testing "check if record"
      (is (record? (map->Patient {}))))
    (let [patient (map->Patient
                    {:id         ID
                     :name       NAME
                     :birth-date BIRTH-DATE})]
      (testing "Record acts like a map"
        (testing "access attributes with call"
          (is (= ID (:id patient))))
        (testing "access attributes with get"
          (is (= NAME (get patient :name))))
        (testing "when used with seq"
          (is (= [[:id ID]
                  [:name NAME]
                  [:birth-date BIRTH-DATE]]
                 (seq patient))))
        (testing "when assoc new attribute"
          (is (= DOG-NAME (:dog-name (assoc patient :dog-name DOG-NAME)))))
        (testing "when assoc existing attribute"
          (is (= "Someone Else" (:name (assoc patient :name "Someone Else")))))
        (testing "when dissoc attribute"
          (is (nil? (:id (dissoc patient :id))))))
      (testing "Record can act as a java class"
        (is (= BIRTH-DATE (.birth_date patient)))))))

(deftest add-patient-test
  (let [william (->Patient 15 "Patient 15" "01/01/1984")
        unidentified (map->Patient {:name "Unidentified Patient"})
        patients {}]
    (testing "Add patient to patients collection"
      (is (= {15 william}
             (add-patient william patients))))
    (testing "Add patient with no id"
      (try
        (add-patient unidentified patients)
        (catch ExceptionInfo e
          (is (re-find #"Patient missing ID" (.getMessage e)))
          (is (= {:patient unidentified} (.getData e))))))))
