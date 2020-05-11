(ns alura.collections.lesson3)

(defn total-item-price
  [[_item-id details]]
  (* (:quantity details 0)
     (:price-per-unit details 0)))

(defn total-order-price
  [order]
  (->> order
       :items
       (map total-item-price)
       (reduce +)))

(defn total-orders-price
  [orders]
  (->> orders
       (map total-order-price)
       (reduce +)))

(defn order-count-and-total
  [[user-id orders]]
  {:user-id         user-id
   :total-of-orders (count orders)
   :total-price     (total-orders-price orders)})

(defn orders-summary
  [orders]
  (->> orders
       (group-by :user)
       (map order-count-and-total)))