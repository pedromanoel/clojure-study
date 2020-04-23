(ns learning-clojure.control-flow-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.control-flow :refer [is-truthy?]]))

(deftest is-truthy?-test
  (testing "truthy values"
    (is (= "\"\" is truthy" (is-truthy? "")))
    (is (= "1 is truthy" (is-truthy? 1)))
    (is (= "1.0 is truthy" (is-truthy? 1.0)))
    (is (= "1/2 is truthy" (is-truthy? 1/2)))
    (is (= "[] is truthy" (is-truthy? [])))
    (is (= "[1 2 3] is truthy" (is-truthy? [1 2 3])))
    (is (= "() is truthy" (is-truthy? '())))
    (is (= "(1 2 3) is truthy" (is-truthy? '(1 2 3))))
    (is (= "{} is truthy" (is-truthy? {})))
    (is (= "{:name \"Peter\"} is truthy" (is-truthy? {:name "Peter"}))))
  (testing "falsy values"
    (is (= "nil is falsy" (is-truthy? nil)))
    (is (= "false is falsy" (is-truthy? false)))))

(deftest if-test
  (testing "if does not need else branch"
    (is (= "true branch" (if true "true branch")))
    (is (nil? (if false "true branch")))))

(deftest when-test
  (testing "when is like if, but can take several forms"
    (is (= "when true" (when true
                         (+ 1 2)
                         (str "um" "dois")
                         "when true"))))
  (testing "return nil when false"
    (is (nil? (when false "when true")))))

(deftest do-test
  (testing "do allows calling several forms in one call"
    (let [stringBuilder (StringBuilder.)]
      (do
        (.append stringBuilder "um")
        (.append stringBuilder ", ")
        (.append stringBuilder "dois")
        (.append stringBuilder ", ")
        (.append stringBuilder "tres"))
      (is (= "um, dois, tres" (.toString stringBuilder))))))
