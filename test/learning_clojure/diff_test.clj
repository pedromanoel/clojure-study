(ns learning-clojure.diff-test
  (:require [clojure.test :refer :all]
            [clojure.data :as data]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]))

(defspec diff-same-value-always-returns-tuple-with-two-nils-and-value
  (prop/for-all [val gen/any]
    (= [nil nil val]
       (data/diff val val))))

(deftest diff-test
  (testing "diff vectors"
    (testing "when same elements in different order"
      (let [[only-in-a only-in-b in-both]
            (data/diff [:um :dois :tres] [:tres :um :dois])]
        (testing "all elements are different"
          (is (= [:um :dois :tres] only-in-a))
          (is (= [:tres :um :dois] only-in-b))
          (is (nil? in-both)))))

    (testing "when some elements in different order"
      (let [[only-in-a only-in-b in-both]
            (data/diff [1 2 3 4] [3 2 1 4 5])]

        (testing "different elements are nil, without trailing nils"
          (is (= [1 nil 3] only-in-a))
          (is (= [3 nil 1 nil 5] only-in-b)))

        (testing "same elements without trailing nils"
          (is (= [nil 2 nil 4] in-both)))))))