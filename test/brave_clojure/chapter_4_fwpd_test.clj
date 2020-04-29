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

(let [suspects [{:name "One" :glitter-index 1}
                {:name "Two" :glitter-index 2}
                {:name "Three" :glitter-index 3}
                {:name "Four" :glitter-index 4}]]
  (deftest glitter-filter-test
    (is (= ["Three" "Four"]
           (glitter-filter 3 suspects))))

  (deftest append-test
    (testing "do not append if name not present"
      (is (= suspects (append suspects {:glitter-index 2}))))
    (testing "do not append if name is blank"
      (is (= suspects (append suspects {:name "" :glitter-index 2}))))
    (testing "do not append if glitter-index not present"
      (is (= suspects (append suspects {:name "Pedro"}))))
    (testing "do not append if glitter-index is not a number"
      (is (= suspects (append suspects {:name "Pedro" :glitter-index "2"}))))
    (testing "append suspects with name and glitter-index defined"
      (is (= {:name "New" :glitter-index 20}
             (last (append suspects {:name "New" :glitter-index 20}))))))

  (deftest map->csv-test
    (testing "generate csv from list of suspects"
      (is (=
            "One,1\nTwo,2\nThree,3\nFour,4"
            (map->csv suspects))))))

(deftest validate-test
  (let [record {:name "A Name" :glitter-index 3}]
    (testing "validate when key is present"
      (is (true? (validate {:name string?} record)))
      (is (false? (validate {:name number?} record)))
      (is (false? (validate {:new-key #(true)} record))))
    (testing "validate multiple keys"
      (is (true? (validate {:name string? :glitter-index number?} record))))))


