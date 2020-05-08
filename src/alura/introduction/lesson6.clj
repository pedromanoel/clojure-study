(ns alura.introduction.lesson6
  (:require [alura.introduction.lesson5 :refer [order]]))

(defn product-total-price
  [order]
  (* (:quantity order) (:price order)))

(defn total-price-per-product
  [[_product-key order]]
  (product-total-price order))

(defn order-total-price
  [order]
  (->> order
       vals
      (map product-total-price)
      (reduce +)))
