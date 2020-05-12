(ns alura.collections.lesson5-test
  (:require [clojure.test :refer :all])
  (:require (alura.collections
              [db :as c.db]
              [lesson3 :as c.lesson3]
              [lesson5 :as c.lesson5])))

(deftest filter-test
  (testing "filters when predicate return true"
    (is (= [0 2 4]
           (filter even? (range 5))))))

(deftest keep-test
  (testing "return collection with return of predicate"
    (is (= [true false true false true]
           (keep even? (range 5))))
    (is (= [0 2 4]
           (keep #(if (even? %) %) (range 5))))))
