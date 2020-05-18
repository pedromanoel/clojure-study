(ns test.custom-asserts
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

(defn function-with-arities
  ([f n]
   (= #{n} (arity f)))
  ([f n & others]
   (= (set (list* n others)) (arity f))))
