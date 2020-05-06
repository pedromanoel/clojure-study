(ns learning-clojure-alura.introduction.lesson3)

(defn more-expensive-than-100?
  [original-price]
  (> original-price 100))

(defn discounted-price
  "Return the original price with 10% discount when value greater than 100"
  [will-apply? original-price]
  (if (will-apply? original-price)
    (let [discount-rate (/ 10 100)
          discount (* original-price discount-rate)]
      (- original-price discount))
    original-price))