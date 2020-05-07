(ns learning-clojure-alura.introduction.lesson4
  (:require [learning-clojure-alura.introduction.lesson2
             :refer [must-apply-discount? discounted-price]]))

(def prices [30 700 1000])
(def discounted-prices (map discounted-price prices))
(def even-numbers (filter even? (range 10)))
(def prices-to-discount (filter must-apply-discount? prices))
(def discounted-prices-only (map discounted-price prices-to-discount))
(def total-prices (reduce + prices))