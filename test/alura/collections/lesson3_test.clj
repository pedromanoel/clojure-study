(ns alura.collections.lesson3-test
  (:require [clojure.test :refer :all]
            [alura.collections.lesson3 :refer :all]
            ))

(let [order1 {:user 1 :items {:backpack {:id :backpack :quantity 2 :price-per-unit 80}}}
      order2 {:user 2 :items {:shirt {:id :shirt :quantity 3 :price-per-unit 20}}}
      order3 {:user 1 :items {:backpack {:id :backpack :quantity 1 :price-per-unit 80}}}
      order4 {:user 1 :items {:shoe {:id :shoe :quantity 1}}}
      order5 {:user 2 :items {:backpack {:id :backpack :quantity 1 :price-per-unit 80}}}
      orders [order1 order2 order3 order4 order5]]

  (deftest orders-summary-test
    (testing "totalizes order price"
      (is (= [{:user-id 1 :total-of-orders 3 :total-price 240}
              {:user-id 2 :total-of-orders 2 :total-price 140}]
             (orders-summary orders)))))
  )
