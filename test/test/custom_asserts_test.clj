(ns test.custom-asserts-test
  (:require [clojure.test :refer :all])
  (:require [test.custom-asserts :refer [function-with-arities]]))

(deftest has-arity-test
  (testing "check arity of simple function"
    (is (function-with-arities (fn []) 0))
    (is (function-with-arities (fn [first]) 1)))
  (testing "check arity with anonymous functions"
    (is (function-with-arities #(identity %) 1))
    (is (function-with-arities #(str %1 %2) 2)))
  (testing "check arity of multi arity function"
    (is (function-with-arities (fn
                     ([])
                     ([one two]))
                               0 2))))
