(ns learning-clojure.functions-test
  (:require [clojure.test :refer :all]
            [learning-clojure.functions :refer :all]))

(deftest greet-defn-test
  (testing "exercise 1"
    (is (= "Hello using defn" (greet-defn)))))

(deftest greet-def-fn-test
  (testing "exercise 2"
    (is (= "Hello using def + fn" (greet-def-fn)))))

(deftest greet-short-fn-test
  (testing "exercise 2"
    (is (= "Hello using def + #()" (greet-short-fn)))))

(deftest greeting-test
  (testing "Greeting no arguments"
    (is (= "Hello, World!" (greeting))))
  (testing "Greeting one argument"
    (is (= "Hello, Nome!", (greeting "Nome"))))
  (testing "Greeting two argument"
    (is (= "Olá, Nome!", (greeting "Olá" "Nome")))))

(deftest do-nothing-test
  (testing "do-nothing returns argument"
    (is (= 10 (do-nothing 10)))))

(deftest always-thing-test
  (testing "always return 100"
    (is (= 100 (always-thing "something")))
    (is (= 100 (always-thing "other")))
    (is (= 100 (always-thing 123)))))

(deftest make-thingy-test
  (testing "create function that always returns same value"
    (let [n (rand-int Integer/MAX_VALUE)
          f (make-thingy n)]
      (is (= n (f)))
      (is (= n (f 123)))
      (is (= n (apply f 123 (range)))))))

(deftest triplicate-test
  (testing "exercise 7 - call function three times"
    (let [strBuilder (StringBuilder.)
          appendStr (fn [] (.append strBuilder "oi"))]
      (triplicate appendStr)
      (is (= "oioioi" (.toString strBuilder))))))

(deftest triplicate2-test
  (testing "exercise 9 - triplicate function call with args"
    (let [strBuilder (StringBuilder.),
          appendStr (fn [& args]
                      (let [fullString (reduce str args)]
                        (.append strBuilder fullString)))]
      (triplicate2 appendStr "1" "2" "3")
      (is (= "123123123" (.toString strBuilder)))))
  )

(deftest math-test
  (testing "exercise 10 - mathematical axioms",
    (is (= -1.0 (Math/cos Math/PI)))
    (is (= 1.0 (+
                 (Math/pow (Math/sin 0.5) 2)
                 (Math/pow (Math/cos 0.5) 2))))))

(deftest http-get-test
  (testing "exercise 11 - fetch content from url"
    (is (.contains (http-get "https://www.w3.org") "html"))))

(deftest one-less-arg-test
  (testing "exercise 12 - partial"
    (let [times-2 (one-less-arg * 2)]
      (is (= 4 (times-2 2))))))

(deftest two-fns-test
  (testing "exercise 13 - compose two functions f(g(x))"
    (let [times-2 #(* 2 %)
          toLong #(Long/parseLong %)]
      (is (= 20 ((two-fns times-2 toLong) "10"))))))
