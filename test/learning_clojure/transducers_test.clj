(ns learning-clojure.transducers-test
  (:require [clojure.test :refer :all]
            [test.custom-asserts :as t])
  (:import (clojure.lang ArityException)))

; A transducer is a function that wraps a reducing function with some processing.
;
; Ex.:
; A map transducer, when called with a reducing function, will produce a new reducing function
; that applies the mapping to the item before calling the reducing function.
;
; (def inc-transducer (map inc))
;
; (def inc-then-conj-reducer-function (map-transducer conj))
; (= [2 3 4] (reduce map-then-conj-reducer-function [] [1 2 3]))
;
; (def inc-then-sum-reducer-function (map-transducer +))
; (= 8 (reduce map-then-conj-reducer-function [1 2 3]))
;; (+ 1 (inc 2))
;; (+ 4 (inc 3))

(defn- reducing-function
  ([] :init)
  ([result] {:completed result})
  ([result item] {:new-result (conj result item)}))

(def ^:private mapping-inc (map inc))
(def ^:private mapping-inc-reducing-function (mapping-inc reducing-function))

(def ^:private core-transducers [(map identity)
                                 cat
                                 ;(mapcat identity) - because it uses comp, it actually has more arities
                                 (filter any?)
                                 (remove any?)
                                 (take 1)
                                 (take-while any?)
                                 (take-nth 3)
                                 (drop 1)
                                 (drop-while any?)
                                 (replace {:one 1})
                                 (partition-by #(< % 4))
                                 (partition-all 3)
                                 (keep print)
                                 (keep-indexed print)
                                 (map-indexed print)
                                 (distinct)
                                 (interpose ",")
                                 (dedupe)
                                 (random-sample 0.05)])

(deftest completing-test
  (let [bad-reducing-function #(+ %1 %2)
        completing-reducing-function (completing bad-reducing-function)]
    (is (thrown? ArityException (bad-reducing-function 10)))
    (is (= 10 (completing-reducing-function 10)))))

(deftest core-transducers-test
  (testing "a transducer is a function with arity 1 (it receives a reducing function)"
    (is (every? function? core-transducers)
        "the transducers should be functions")
    (is (every? #(= #{1} %) (map t/arity core-transducers))
        "the transducers should only receive a reducing function")
    (is (every? #(= #{0 1 2} %) (map #(t/arity (% str)) (conj core-transducers (mapcat identity))))
        "calling transducers should return a reducing function with arity 3")))

(deftest map-transduced-function-arity-test
  (testing "the reducing function wrapped by the transducer has three arities 0 1 and 2"
    (is (t/function-with-arities mapping-inc-reducing-function 0 1 2))))

(deftest map-transduced-function-init-arity-test
  (testing "arity 0 is the init form, which calls the reducing function with no args"
    (is (= :init (mapping-inc-reducing-function)))))

(deftest map-transduced-function-completion-arity-test
  (testing "arity 1 is the completion form, which calls the reducing function with the result"
    (is (= {:completed [1]} (mapping-inc-reducing-function [1])))))

(deftest map-transduced-function-reducing-arity-test
  (testing "arity 2 is the wrapped reduction, which calls the map function then calls the reducing function"
    (is (= {:new-result [1 2]} (mapping-inc-reducing-function [1] 1)))))
