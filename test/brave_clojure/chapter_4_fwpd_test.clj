(ns brave-clojure.chapter-4-fwpd-test
  (:require [clojure.test :refer :all])
  (:require [brave-clojure.chapter-4-fwpd :refer :all]))

(deftest convert-test
  (is (= 3 (convert :glitter-index "3"))))

(deftest parse-test
  (is (= [["1-1" "1-2"]
          ["2-1" "2-2"]]
         (parse "1-1,1-2\n2-1,2-2"))))

(deftest mapify-test
  (is (= [{:name "One" :glitter-index 1}
          {:name "Another" :glitter-index 2}]
         (mapify [["One" "1"] ["Another" "2"]]))))

(deftest mapify-2-test
  (is (= [{:name "One" :glitter-index 1}
          {:name "Another" :glitter-index 2}]
         (mapify-2 [["One" "1"] ["Another" "2"]]))))

(deftest glitter-filter-test
  (let [records [{:name "One" :glitter-index 1}
                 {:name "Two" :glitter-index 2}
                 {:name "Three" :glitter-index 3}
                 {:name "Four" :glitter-index 4}]]
    (is (= [{:name "Three" :glitter-index 3}
            {:name "Four" :glitter-index 4}]
           (glitter-filter 3 records)))))
