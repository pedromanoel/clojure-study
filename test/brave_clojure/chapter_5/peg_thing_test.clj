(ns brave-clojure.chapter-5.peg-thing-test
  (:require [clojure.test :refer :all])
  (:require [brave-clojure.chapter-5.peg-thing :refer :all]))

(deftest tri*-test
  (testing "lazily create triangle numbers"
    (let [lazy-tri (tri*)]
      (is (false? (realized? lazy-tri)))
      (is (= [1] (take 1 lazy-tri)))
      (is (true? (realized? lazy-tri)))
      (is (= [1 3 6] (take 3 lazy-tri)))
      (is (= [1 3 6 10 15] (take 5 lazy-tri))))))