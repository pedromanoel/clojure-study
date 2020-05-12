(ns alura.collections.lesson4
  (:require [alura.collections.lesson3 :as c.lesson3]))

(defn orders-summary-sorted-by-price
  [orders]
  (->> orders
       c.lesson3/orders-summary
       (sort-by :total-price)))