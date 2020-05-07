(ns learning-clojure-alura.introduction.lesson4-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure-alura.introduction.lesson4 :refer :all]))

(deftest collection-functions-test
  (testing "map function"
    (is (= [30 700 1000] prices))
    (is (= [30 630 900] discounted-prices)))
  (testing "filter function"
    (is (= [0 2 4 6 8] even-numbers))
    (is (= [700 1000] prices-to-discount)))
  (testing "filter then map"
    (is (= [630 900] discounted-prices-only)))
  (testing "reduce"
    (is (= 1730 total-prices))))

(deftest collections-test
  (testing "vector collection"
    (testing "access with call"
      (is (= 30 (prices 0)))
      (is (= 1000 (prices 2)))
      (is (thrown? IndexOutOfBoundsException (prices 3))))
    (testing "access with get"
      (is (= 30 (get prices 0)))
      (is (= 700 (get prices 1)))
      (is (nil? (get prices 10)))
      (is (= 0 (get prices 10 0))))
    (testing "append with conj"
      (is (= 5000 (last (conj prices 5000)))))
    (testing "update value"
      (is (= 31 (first (update prices 0 inc))))
      (is (= 31 (first (update prices 0 inc))))))
  (testing "map collection"
    (let [pet {:name "Ginger" :age 5}]
      (testing "update value"
        (is (= 6 (:age (update pet :age inc))))))))
