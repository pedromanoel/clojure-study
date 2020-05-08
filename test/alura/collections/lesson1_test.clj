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
  (is (= ["1" "2" "3"] (my-map str [1 2 3])) "iterate over list")
  (is (= ["1" "false" "3"] (my-map str [1 false 3])) "iterate false values")
  )