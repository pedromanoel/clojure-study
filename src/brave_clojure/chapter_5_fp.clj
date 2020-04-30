(ns brave-clojure.chapter-5-fp)

(defn sum
  ([vals] (sum vals 0))
  ([[first & rest] accumulating-sum]
   (if (nil? first)
     accumulating-sum
     (recur rest (+ accumulating-sum first)))))
