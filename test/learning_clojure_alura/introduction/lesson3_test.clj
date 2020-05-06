(ns learning-clojure-alura.introduction.lesson3-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure-alura.introduction.lesson3 :refer :all]))

(deftest discounted-price-test
  (testing "Apply discount with declared function"
    (is (= 900 (discounted-price more-expensive-than-100? 1000)))
    (is (= 100 (discounted-price more-expensive-than-100? 100))))
  (testing "Apply discount with lambda functions"
    (is (= 45 (discounted-price (fn [original-price] (>= original-price 50)) 50)))
    (is (= 45 (discounted-price #(>= % 50) 50)))))
