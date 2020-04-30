(ns brave-clojure.chapter-5-fp-test
  (:require [clojure.test :refer :all])
  (:require [brave-clojure.chapter-5-fp :refer :all]))

(deftest sum-test
  (is (= 10 (sum [1 2 3 4]))))

(deftest clean-test
  (is (= "LOL" (clean "  lol  ")))
  (is (= "LOL" (clean-thread "  lol  ")))
  (is (= "LOL" (clean-comp "  lol  "))))

(deftest comp-test
  (let [character {:name       "Smooches McCutes"
                   :attributes {
                                :intelligence 10
                                :strength     4
                                :dexterity    5}}]
    (is (= 10 (c-int character)))
    (is (= 4 (c-str character)))
    (is (= 5 (c-dex character)))
    (is (= 6 (spell-slots character)))
    (is (= 6 (spell-slots-comp character)))))


(deftest my-comp-test
  (is (= -8 ((my-comp inc -) 9))))
