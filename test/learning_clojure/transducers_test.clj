(ns learning-clojure.transducers-test
  (:require [clojure.test :refer :all]))

(defn- invoke-method? [method]
  (->> method
       (.getName)
       (= "invoke")))

(defn- invoke-methods [f]
  (->> f
       (class)
       (.getDeclaredMethods)
       (filter invoke-method?)))

(defn- method-arity [method] (.getParameterCount method))

(defn arity
  [f]
  (->> f
       invoke-methods
       (map method-arity)
       set))

(defn has-arity
  ([f n]
   (= #{n} (arity f)))
  ([f n & others]
   (= (set (list* n others)) (arity f))))

; A transducer is a function that wraps the processing function it receives as an argument
; and changes the value before processing it.
;
; An example of a processing function is the conj function, which takes a list and a value
; and returns a new list with the element added:
;
;    (= [1 2 3] (conj [1 2] 3))
;
; A map transducer will map the value before passing the value to the processing function.
; A filter transducer will choose if the processing should occur based on a predicate.
; a processing function takes a result and an input, process both and return a new result
(deftest map-transducer-test
  (let [mapping-inc (map inc)]
    (testing "a transducer is a function with arity 1"
      (is (function? mapping-inc))
      (is (has-arity mapping-inc 1)))
    (testing "a transducer returns another function with arities 0 1 and 2"
      (is (has-arity (mapping-inc 1) 0 1 2)))
    (testing "the function returned by a transducer"
      (let [mapping-inc-with-str (mapping-inc str)]
        (testing "calls the passed function when called with no arguments"
          (is (= "" (mapping-inc-with-str))))
        (testing "calls the passed function on its first argument"
          (is (= "[]" (mapping-inc-with-str []))))
        (testing "calls the mapping function on the second argument and then call the passed function to first and second"
          (is (= "[]2" (mapping-inc-with-str [] 1))))))))
