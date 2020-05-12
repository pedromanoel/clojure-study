(ns alura.collections.lesson3-test
  (:require [clojure.test :refer :all]
            (alura.collections
              [lesson3 :as c.lesson3]
              [db :as c.db])))

(deftest orders-summary-test
  (testing "totalizes order price"
    (is (= [{:user-id 3 :total-of-orders 1 :total-price 80}
            {:user-id 1 :total-of-orders 2 :total-price 160}
            {:user-id 2 :total-of-orders 2 :total-price 180}]
           (c.lesson3/orders-summary c.db/orders)))))
