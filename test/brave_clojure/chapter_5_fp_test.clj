(ns brave-clojure.chapter-5-fp-test
  (:require [clojure.test :refer :all])
  (:require [brave-clojure.chapter-5-fp :refer [sum]]))

(deftest sum-test
  (is (= 10 (sum [1 2 3 4]))))
