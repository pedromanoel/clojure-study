(ns learning-clojure.map-test
  (:require [clojure.test :refer :all]))

(deftest map-test
  (testing "creating a map"
    (is (=
          {:name "Pedro" :age 36}
          (hash-map :name "Pedro" :age 36))))
  (testing "adding/removing elements"
    (let [a-map {:one 1 :two 2}]
      (is (=
            {:one 1 :two 2 :three 3}
            (assoc a-map :three 3)))
      (is (=
            {:two 2}
            (dissoc a-map :one)))))
  (testing "accessing elements"
    (let [address {:street "Rua Maria Rissio Mendes"
                   :number 54
                   :cep "05324050"}]
      (is (= 54 (get address :number)) "using get")
      (is (= 54 (:number address)) "invoking key")
      (is (= 54 (address :number)) "invoking map"))
    ))