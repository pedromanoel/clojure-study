(ns alura.records.lesson3-test
  (:require [clojure.test :refer :all])
  (:require [alura.records.lesson3 :refer [to-ms]])
  (:import (java.time LocalDateTime)))

(deftest to-ms-test
  (testing "extending numbers"
    (is (= 123 (to-ms 123)))
    (is (= 0123 (to-ms 0123))))
  (testing "extending LocalDateTime"
    (is (= 1577890800000 (to-ms (LocalDateTime/of 2020 1 1 12 0 0)))))
  (testing "extending String"
    (is (= 1577890800000 (to-ms "2020-01-01T12:00:00")))))
