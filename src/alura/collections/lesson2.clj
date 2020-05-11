(ns alura.collections.lesson2)
;
;(defn my-count-using-loop
;  "Same as my count with overloading, but does not expose
;  signature with total-so-far"
;  ([elements]
;   (loop [total-so-far 0
;          remaining-elements elements]
;     (if (seq remaining-elements)
;       (recur (inc total-so-far) (rest remaining-elements))
;       total-so-far))))

(defn my-count
  ([elements] (my-count 0 elements))
  ([total-so-far elements]
   (if (seq elements)
     (recur (inc total-so-far) (rest elements))
     total-so-far)))

(defn my-reduce
  [function initial-value sequence])