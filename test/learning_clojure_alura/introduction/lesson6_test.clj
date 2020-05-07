(ns learning-clojure-alura.introduction.lesson6-test
  (:require [clojure.test :refer :all]
            (learning-clojure-alura.introduction
              [lesson5 :refer [order]]
              [lesson6 :refer :all]))
  (:import (clojure.lang MapEntry)))

(deftest map-test
  (testing "map entry"
    (testing "Is type entry"
      (is (instance? MapEntry (first order))))
    (testing "Can be destructured"
      (let [[key value] (first order)]
        (is (= :backpack key))
        (is (= {:price 80 :quantity 7} value))
        (testing "Is comparable to vector"
          (is (= [key value] (first order))))))))

(deftest total-price-per-product-test
  (is (= [560 120] (map total-price-per-product order))))

(deftest order-total-price-test
  (is (= 680 (order-total-price order))))
