(ns learning-clojure.functions-test
  (:require [clojure.test :refer :all]
            [learning-clojure.functions :refer :all]))

(deftest greet1-test
  (testing "greet using defn"
    (assert (= "Hello using defn" (greet1)))))

(deftest greet2-test
  (testing "greet using fn"
    (assert (= "Hello using def + fn" (greet2)))))
(deftest greet3-test
  (testing "greet using fn"
    (assert (= "Hello using def + #()" (greet3)))))

(deftest greeting-test
  (testing "Greeting no arguments"
    (assert (= "Hello, World" (greeting))))
  (testing "Greeting one argument"
    (assert (= "Hello, Nome", (greeting "Nome"))))
  (testing "Greeting two argument"
    (assert (= "Olá, Nome", (greeting "Olá" "Nome")))))

(deftest do-nothing-test
  (testing "do-nothing returns argument"
    (assert (= 10 (do-nothing 10)))))

(deftest always-thing-test
  (testing "always return 100"
    (assert (= 100 (always-thing "something")))
    (assert (= 100 (always-thing "other")))
    (assert (= 100 (always-thing 123)))))

(deftest make-thingy-test
  (testing "create function that always returns same value"
    (let [n (rand-int Integer/MAX_VALUE)
          f (make-thingy n)]
      (assert (= n (f)))
      (assert (= n (f 123)))
      (assert (= n (apply f 123 (range)))))))
