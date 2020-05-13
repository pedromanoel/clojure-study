(ns brave-clojure.chapter-5.fp-examples
  (:require [clojure.string :as s]))

(defn sum
  "Example using recursion for looping in a sequence"
  ([vals] (sum vals 0))
  ([[first & rest] accumulating-sum]
   (if (nil? first)
     accumulating-sum
     (recur rest (+ accumulating-sum first)))))

(defn clean
  "Example using function composition f(g(x))"
  [text]
  (s/trim (s/replace text #"lol" "LOL")))

(defn clean-thread
  "Example using function composition with thread call"
  [text]
  (-> text
      (s/replace,,, #"lol" "LOL")
      (s/trim)))

(defn clean-comp
  "Example of function composition using (comp f g)"
  [text]
  ((comp s/trim s/replace) text #"lol" "LOL"))

; Examples using comp to retrieve deep values from data
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(defn spell-slots
  "calculate spell slots with formula floor(1 + intelligence/2"
  [char]
  (int (inc (/ (c-int char) 2))))

(defn spell-slots-comp
  "calculate spell slots using comp"
  [char]
  ((comp int inc #(/ % 2) c-int) char))

(defn my-comp
  "implement a function composition"
  ([f] f)
  ([f g] (fn [& args] (f (apply g args))))
  ([f g & fs]
   (reduce my-comp (list* f g fs))))

(defn to-uppercase-with-side-effect
  [f string]
  (do
    (f string)
    (s/upper-case string)))
