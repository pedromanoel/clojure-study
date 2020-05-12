(ns alura.collections.lesson4-test
  (:require [clojure.test :refer :all]
            (alura.collections
              [lesson4 :as c.lesson4]
              [db :as c.db]
              [logic :as c.logic])))

(deftest sorting-test
  (let [summary (c.logic/orders-summary c.db/orders)]
    (is (= [1 2 3]
           (map :user-id (sort-by :user-id summary))))
    (is (= [3 1 2]
           (map :user-id (sort-by :total-price summary))))
    (is (= [3 2 1]
           (map :user-id (reverse (sort-by :user-id summary)))))))

(deftest get-in-test
  (is (= 80 (get-in c.db/orders [0 :items :backpack :price-per-unit]))))

(deftest orders-summary-sorted-by-price-test
  (let [summary (c.lesson4/orders-summary-sorted-by-price c.db/orders)]
    (is (= [80 160 180]
           (map :total-price summary)))))
