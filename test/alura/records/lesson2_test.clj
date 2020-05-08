(ns alura.records.lesson2-test
  (:require [clojure.test :refer :all])
  (:require [alura.records.lesson2 :refer :all])
  (:import (java.time LocalDateTime)))

(deftest to-ms-test
  (testing "extending numbers"
    (is (= 123 (to-ms 123)))
    (is (= 0123 (to-ms 0123))))
  (testing "extending LocalDateTime"
    (is (= 1577890800000 (to-ms (LocalDateTime/of 2020 1 1 12 0 0)))))
  (testing "extending String"
    (is (= 1577890800000 (to-ms "2020-01-01T12:00:00")))))

(deftest needs-authorization?-test
  (testing "PrivatePatient"
    (let [patient (map->PrivatePatient {})]
      (testing "need authorization when cost equal or larger than 50"
        (is (true? (needs-authorization? patient :blood-type 50)))
        (is (false? (needs-authorization? patient :blood-type 49)))
        )
      (testing "needs authorization using protocol"
        (is (true? (patient-needs-authorization? patient :x-ray 50)))
        (is (false? (patient-needs-authorization? patient :x-ray 49))))))

  (testing "InsuredPatient"
    (let [patient (map->InsuredPatient {:health-insurance [:blood-type :x-ray]})]
      (testing "do not need authorization when exam is covered"
        (is (false? (needs-authorization? patient :blood-type 50)))
        (is (false? (needs-authorization? patient :x-ray 49)))
        (is (true? (needs-authorization? patient :mri 49))))
      (testing "do not need authorization using protocol"
        (is (false? (patient-needs-authorization? patient :blood-type 50)))
        (is (false? (patient-needs-authorization? patient :x-ray 49)))
        (is (true? (patient-needs-authorization? patient :mri 49))))))

  (testing "Other patients"
    (let [patient (map->Patient {})]
      (testing "always need authorization"
        (is (true? (needs-authorization? patient :blood-type 50)))
        (is (true? (needs-authorization? patient :blood-type 49))))
      (testing "using protocol"
        (is (true? (patient-needs-authorization? patient :blood-type 50)))
        (is (true? (patient-needs-authorization? patient :blood-type 49)))))))
