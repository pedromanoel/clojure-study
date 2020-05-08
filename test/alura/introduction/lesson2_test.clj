(ns alura.introduction.lesson2-test
  (:require [clojure.test :refer :all])
  (:require [alura.introduction.lesson2 :refer [discounted-price]]))

(deftest discounted-price-test
  (testing "Apply discount when larger than 100"
    (is (= 900 (discounted-price 1000)))
    (is (= 100 (discounted-price 100)))))
