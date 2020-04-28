(ns brave-clojure.chapter-4-core-functions-test
  (:require [clojure.test :refer :all])
  (:require [brave-clojure.chapter-4-core-functions :refer [stats]]))

(deftest seq-test
  (testing "first, rest & cons"
    (testing "for vector"
      (let [subject [1 2 3]]
        (is (= 1 (first subject)))
        (is (= [2 3] (rest subject)))
        (is (= [0 1 2 3] (cons 0 subject)))))
    (testing "for list"
      (let [subject '(1 2 3)]
        (is (= 1 (first subject)))
        (is (= [2 3] (rest subject)))
        (is (= [0 1 2 3] (cons 0 subject)))))
    (testing "for map"
      (let [subject {"first" 1 "second" 2 "third" 3}]
        (is (= ["first" 1] (first subject)))
        (is (= [["second" 2] ["third" 3]] (rest subject)))
        (is (=
              [["zero" 0] ["first" 1] ["second" 2] ["third" 3]]
              (cons ["zero" 0] subject)))))))

(deftest stats-test
  (testing "stats return average"
    (is (= {:avg 2 :count 3 :sum 6} (stats [1 2 3])))))