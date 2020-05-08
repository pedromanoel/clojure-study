(ns alura.introduction.lesson6-test
  (:require [clojure.test :refer :all]
            [alura.introduction.lesson6 :refer :all])
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

(deftest filter-test
  (testing "filter applied to map"
    (is (= [[:keychain {:quantity 1}]]
           (filter for-free? order)))
    (is (= [[:keychain {:quantity 1}]]
           (filter #(= 0 (:price (second %) 0)) order)))))

(deftest total-price-per-product-test
  (is (= [560 120 0] (map total-price-per-product order))))

(deftest order-total-price-test
  (is (= 680 (order-total-price order))))

(deftest for-free?-test
  (is (true? (for-free? [:order-key  {:quantity 3 :price 0}])))
  (is (true? (for-free? [:order-key {:quantity 3}])))
  (is (false? (for-free? [:order-key {:quantity 3 :price 1}]))))

(deftest paid?-test
  (is (false? (paid? [:order-key  {:quantity 3 :price 0}])))
  (is (false? (paid? [:order-key {:quantity 3}])))
  (is (true? (paid? [:order-key {:quantity 3 :price 1}]))))

(deftest map-reduce-test
  (let [clients [{:name         "William"
                  :certificates ["Clojure" "Java" "Machine Learning"]}
                 {:name "Paul"
                  :certificates ["Java" "Computer Science"]}
                 {:name "Daniela"
                  :certificates ["Architecture" "Cooking"]}]]
    (is (= 7 (->> clients
                  (map :certificates)
                  (map count)
                  (reduce +))))))
