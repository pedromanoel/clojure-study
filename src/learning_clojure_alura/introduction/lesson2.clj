(ns learning-clojure-alura.introduction.lesson2)

(defn discounted-price
  "Return the original price with 10% discount when value greater than 100"
  [original-price]
  (if (> original-price 100)
    (let [discount-rate (/ 10 100)
          discount (* original-price discount-rate)]
      (- original-price discount))
    original-price))
