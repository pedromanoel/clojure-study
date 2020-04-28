(ns brave-clojure.chapter-4-core-functions-test
  (:require [clojure.test :refer :all])
  (:require [brave-clojure.chapter-4-core-functions :refer :all]))

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

(conj {:one 1} [:two 2])

(deftest inc-map-with-reduce-test
  (is (= {:bla 20 :blu 30} (inc-map-with-reduce {:bla 19 :blu 29}))))

(deftest filter-entries-with-reduce-test
  (is (= {"Apple" 10 "Banana" 12}
         (filter-entries-with-reduce 9 {"Apple" 10 "Banana" 12 "Peach" 3}))))

(deftest reduce-test
  (is (true? (some-reduce odd? [2 4 6 7])))
  (is (false? (some-reduce odd? [2 4 6])))
  (is (= 3 (some-reduce #(and (odd? %) %) [2 4 3 6]))))