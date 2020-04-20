(ns learning-clojure.functions
  (:gen-class)
  (:import (java.net URL)))

; 1
(defn greet-defn [] "Hello using defn")

; 2
(def greet-def-fn (fn [] "Hello using def + fn"))

(def greet-short-fn #(str "Hello using def + #()"))

; 3
(defn greeting
  ([] (greeting "Hello" "World"))
  ([name] (greeting "Hello" name))
  ([greeting, name] (str greeting ", " name "!")))

; 4
(defn do-nothing [x] x)

; 5
(defn always-thing [x] 100)

; 6
(defn make-thingy [x] (fn [& args] x))

; 7
(defn triplicate [f] (f) (f) (f))

; 9
(defn triplicate2 [f & args]
  (triplicate #(apply f args)))

; 11
(defn http-get [url]
  (slurp (.openStream (URL. url))))
(defn http-get [url]
  (slurp url))
; 12
(defn one-less-arg [f x]
  (fn [& args] (apply f x args)))

; 13
(defn two-fns [f g]
  (fn [arg] (f (g arg))))