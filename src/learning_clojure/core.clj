(ns learning-clojure.core
  (:gen-class)
  (:require [learning-clojure.functions :refer :all])
  (:require [brave-clojure.chapter-4.fwpd-examples :refer :all]))

(defn -main
  "I don't do a whole lot ... yet."
  [_]
  (println (first (mapify (parse (slurp filename))))))