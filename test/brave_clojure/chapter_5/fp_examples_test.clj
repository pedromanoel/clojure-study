(ns brave-clojure.chapter-5.fp-examples-test
  (:require [clojure.test :refer :all])
  (:require [brave-clojure.chapter-5.fp-examples :refer :all])
  (:import (java.io StringWriter)))

(defn capture-out [f]
  (binding [*out* (StringWriter.)]
    (f)))

(use-fixtures :each capture-out)

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
  (testing "An implementation of comp function"
    (is (= -8 ((my-comp inc -) 9)))
    (is (= -9 ((my-comp -) 9)))
    (is (= -11 ((my-comp dec - inc) 9)))
    (is (= -6 ((my-comp inc - dec - inc -) 9)))))

(deftest memoize-test
  (testing "Memoized function don't call when parameters are the same"
    (let [memoized-to-uppercase (memoize to-uppercase-with-side-effect)]
      (is (= "A" (to-uppercase-with-side-effect print "a")))
      (is (= "A" (to-uppercase-with-side-effect print "a")))
      (is (= "A" (to-uppercase-with-side-effect print "a")))
      (is (= "B" (memoized-to-uppercase print "b")))
      (is (= "B" (memoized-to-uppercase print "b")))
      (is (= "B" (memoized-to-uppercase print "b")))
      (is (= "aaab" (str *out*))))))
