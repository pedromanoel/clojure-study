(ns learning-clojure.lists-and-vectors-test
  (:require [clojure.test :refer :all]))

(deftest vector-get-return-element-test
  (testing "access vector element with get"
    (is (= 2 (get [1 2 3] 1)))))

(deftest vector-get-return-nil-when-out-of-bounds-test
  (testing "return nil when index out of bounds"
    (is (nil? (get [1 2 3] 4)))))

(deftest vector-access-using-call
  (testing "access vector element with call"
    (is (= 1 ([1 2 3] 0)))))

(deftest vector-access-using-call
  (testing "access vector element with call"
    (is (thrown? IndexOutOfBoundsException ([1] 1)))))
