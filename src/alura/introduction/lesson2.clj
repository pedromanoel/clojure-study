(ns alura.introduction.lesson2)

(defn must-apply-discount?
  [original-price]
  (> original-price 100))

(defn discounted-price
  "Return the original price with 10% discount when value greater than 100"
  [original-price]
  (if (must-apply-discount? original-price)
    (let [discount-rate (/ 10 100)
          discount (* original-price discount-rate)]
      (- original-price discount))
    original-price))