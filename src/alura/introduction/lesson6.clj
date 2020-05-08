(ns alura.introduction.lesson6
  (:require [alura.introduction.lesson5 :as lesson5]))

(def order (assoc lesson5/order :keychain {:quantity 1}))

(defn product-total-price
  [order]
  (* (:quantity order) (:price order 0)))

(defn total-price-per-product
  [[_product-key order]]
  (product-total-price order))

(defn order-total-price
  [order]
  (->> order
       vals
       (map product-total-price)
       (reduce +)))

(defn for-free?
  [[_order-key order]]
  (= 0 (:price order 0)))

(defn paid?
  [order-entry]
  (not (for-free? order-entry)))

(def paid? (comp not for-free?))