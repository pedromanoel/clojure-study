(ns learning-clojure.vectors-test
  (:require [clojure.test :refer :all]))

(deftest vector-test
  (let [vec [:first :second :third]]
    (testing "access elements from vector"
      (testing "using get"
        (is (= :second (get vec 1)) "get existing element")
        (is (nil? (get vec 4)) "get out of bounds"))
      (testing "using call"
        (is (= :first (vec 0)) "get using call")
        (is (thrown? IndexOutOfBoundsException (vec 3)) "call out of bounds"))
      (testing "using position functions"
        (is (= :first (first vec)))
        (is (= :third (last vec)))
        (is (= [:second :third] (rest vec)))))

    (testing "count vector"
      (is (= 3 (count vec))))

    (testing "conjoin"
      (is (= [1 2 3] (conj [1] 2 3)) "add to end")
      (testing "immutability"
        (conj vec 4 5 6)
        (is (= [:first :second :third] vec) "vec does not change"))))

  (testing "construct vector"
    (is (= [:a :b :c] (vector :a :b :c)))))
