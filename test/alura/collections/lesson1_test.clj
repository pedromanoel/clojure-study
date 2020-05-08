(ns alura.collections.lesson1-test
  (:require [clojure.test :refer :all])
  (:require [alura.collections.lesson1 :refer :all]))


(deftest seq-test
  (is (nil? (seq [])))
  (is (= [1 2 3] (seq [1 2 3]))))

(deftest rest-test
  (is (= [] (rest []))))

(deftest next-test
  (is (nil? (next []))))

(deftest my-map-test
  (is (= [] (my-map str nil)) "do not map over nil")
  (is (= [] (my-map str [])) "do not map over empty list")
  (is (= ["1" "2" "3"] (my-map str [1 2 3])) "map over list")
  (is (= ["1" "false" "3"] (my-map str [1 false 3])) "map false values")
  (is (= ["1" "" "3"] (my-map str [1 nil 3])) "map nil values")
  )