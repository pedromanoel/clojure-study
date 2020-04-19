(ns learning-clojure.functions
  (:gen-class)
  (:import (java.net URL)))

(defn hello [] (println "Hello World"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (hello))

; 1
(defn greet1 [] "Hello using defn")
; 2
(def greet2 (fn [] "Hello using def + fn"))
(def greet3 #(str "Hello using def + #()"))
; 3
(defn greeting
  ([] (greeting "Hello" "World"))
  ([name] (greeting "Hello" name))
  ([greeting, name] (str greeting ", " name))
  )
; 4
(defn do-nothing [x] x)
; 5
(defn always-thing [x] 100)
; 6
(defn make-thingy [x] (fn [& args] x))
; 7
(defn triplicate [f] (f) (f) (f))
(triplicate #(println "oi"))
; 11
(defn http-get [url]
  (slurp (.openStream (URL. url))))
(defn http-get [url]
  (slurp url))
(http-get "https://www.w3.org")
(assert (.contains (http-get "https://www.w3.org") "html"))
; 12
(defn one-less-arg [f x]
  (fn [& args] (apply f x args)))

(def plus-one
  (one-less-arg + 1))

(def plus-two
  (one-less-arg + 2))

(plus-one 5 6 7)
; 13
(defn two-fns [f g]
  (fn [arg] (f (g arg))))

((two-fns plus-one plus-two) 3)