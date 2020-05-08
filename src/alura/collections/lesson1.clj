(ns alura.collections.lesson1)

(def a-vector ["Daniel" "William" "Carl" "Paul" "Lucy" "Anna"])

(def a-map {"Willaim" 37 "Paul" 39})

(def a-linked-list '(1 2 3 4 5))

(def a-set #{1 2 3 4 5})

(defn my-map
  ([function sequence]
   (my-map function sequence []))
  ([function sequence result]
   (let [first-element (first sequence)]
     (if (empty? sequence)
       result
       (my-map function (rest sequence) (conj result (function first-element)))))))