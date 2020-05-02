(ns brave-clojure.chapter-5.peg-thing)
(defn tri*
  ([] (tri* 0 1))
  ([current-triangle-number count]
   (lazy-seq
     (let [next-triangle-number (+ current-triangle-number count)]
       (cons next-triangle-number (tri* next-triangle-number (inc count)))))))