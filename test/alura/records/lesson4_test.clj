(ns alura.records.lesson4-test
  (:require [clojure.test :refer :all])
  (:require (alura.records
              [lesson2 :as r.lesson2]
              [lesson4 :refer :all])))

(deftest needs-authorization-multi?-test
  (let [insured-patient (r.lesson2/map->InsuredPatient {})
        private-patient (r.lesson2/map->PrivatePatient {})]
    (testing "multimethod with class function"
      (is (true? (needs-authorization-multi? private-patient)))
      (is (false? (needs-authorization-multi? insured-patient))))))

(deftest multi-test-test
  (testing "when no multimethod is defined"
    (is (thrown-with-msg? IllegalArgumentException #"No method in multimethod"
                          (multi-test "String"))))
  (testing "when multimethod is defined"
    (is (= 5 (multi-test [1 2 3 4 5])))))

(deftest needs-authorization-for-request?-test
  (testing "always authorize when request is urgent"
    (is (false? (needs-authorization-for-request? {:situation :urgent}))))
  (let [private-patient (r.lesson2/map->PrivatePatient {})
        insured-patient (r.lesson2/map->InsuredPatient {:health-insurance [:blood-type :x-ray]})]
    (testing "PrivatePatient"
      (testing "need authorization when cost equal or larger than 50"
        (is (true? (needs-authorization-for-request? {:patient private-patient :exam :blood-type :cost 50})))
        (is (false? (needs-authorization-for-request? {:patient private-patient :exam :blood-type :cost 49})))))
    (testing "InsuredPatient"
      (testing "do not need authorization when exam is covered"
        (is (false? (needs-authorization-for-request? {:patient insured-patient :exam :blood-type :cost 50})))
        (is (false? (needs-authorization-for-request? {:patient insured-patient :exam :x-ray :cost 49})))
        (is (true? (needs-authorization-for-request? {:patient insured-patient :exam :mri :cost 49})))))))