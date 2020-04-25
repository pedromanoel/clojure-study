(ns learning-clojure.lists-test
  (:require [clojure.test :refer :all]))

(deftest list-test
  (let [list '(:a :b :c)]
    (testing "access elements from list"
      (is (= :a (first list)))
      (is (= '(:b :c) (rest list)))
      (is (= :c (last list))))
    (testing "count"
      (is (= 3 (count list))))
    (testing "conjoin"
      (is (= '(:f :e :d :a :b :c) (conj list :d :e :f)) "add to beginning"))
    (testing "stack access"
      (is (= :a (peek list)))
      (is (= '(:b :c) (pop list))))))
(deftest for-test
  (is (= '([1 :a] [1 :b] [2 :a] [2 :b])
         (for [num [1 2]
               letter [:a :b]]
           [num letter]))))