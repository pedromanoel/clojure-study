(ns learning-clojure-alura.lesson1-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure-alura.lesson1 :refer [add-patient]])
  (:import (clojure.lang ExceptionInfo)))

(deftest add-patient-test
  (let [william {:id 15 :name "Patient 15"}
        unidentified {:name "Unidentified Patient"}
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
