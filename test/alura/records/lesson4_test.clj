(ns alura.records.lesson4-test
  (:require [clojure.test :refer :all])
  (:require (alura.records
              [lesson2 :as r.lesson2]
              [lesson4 :refer :all])))

(deftest needs-authorization-multi?-test
  (let [insured-patient (r.lesson2/map->InsuredPatient {})
        private-patient (r.lesson2/map->PrivatePatient {})]
    (is (true? (needs-authorization-multi? private-patient)))
    (is (false? (needs-authorization-multi? insured-patient)))))

(deftest multi-test-test
  (testing "when no multimethod is defined"
    (is (thrown-with-msg? IllegalArgumentException #"No method in multimethod"
                          (multi-test "String"))))
  (testing "when multimethod is defined"
    (is (= 5 (multi-test [1 2 3 4 5])))))