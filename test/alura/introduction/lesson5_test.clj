(ns alura.introduction.lesson5-test
  (:require [clojure.test :refer :all]
            [alura.introduction.lesson5 :refer :all]))

(deftest map-test
  (testing "accessing values"
    (testing "as a function"
      (is (= 10 (storage :backpack)))
      (is (nil? (storage :do-not-exist))))
    (testing "with get"
      (is (= 5 (get storage :shirt)))
      (is (nil? (get storage :do-not-exist)))
      (is (nil? (get nil :any)))
      (testing "and default value"
        (is (= 0 (get storage :do-not-exist 0)))
        (is (= 0 (get nil :any 0)))))
    (testing "with keyword"
      (is (= 5 (:shirt storage)))
      (is (nil? (:do-not-exist storage)))
      (is (nil? (:do-not-exist nil)))
      (testing "and default value"
        (is (= 0 (:do-not-exist storage 0)))
        (is (= 0 (:do-not-exist nil 0))))))

  (testing "key types"
    (is (every? string? (keys storage-string-keys)))
    (is (every? keyword? (keys storage))))

  (testing "keys and vals"
    (is (= [10 5] (vals storage-string-keys)))
    (is (= [:backpack :shirt] (keys storage))))

  (testing "seq methods"
    (is (= 2 (count storage-string-keys))))

  (testing "assoc"
    (is (= {:backpack 10
            :shirt    5
            :chair    3}
           (assoc storage :chair 3)))
    (is (= {:backpack 1
            :shirt    5}
           (assoc storage :backpack 1))))

  (testing "dissoc"
    (is (= {:shirt 5}
           (dissoc storage :backpack))))

  (testing "update value"
    (is (= {:backpack 11
            :shirt    5}
           (update storage :backpack inc)))
    (is (= {:backpack 7
            :shirt    5}
           (update storage :backpack #(- % 3))))
    (is (= 8
           (:quantity (:backpack (update-in order [:backpack :quantity] inc))))))

  (testing "threading"
    (is (= 7 (-> order
                 :backpack
                 :quantity))))

  (testing "nested maps"
    (let [keychain-order {:quantity 1 :price 10}
          order (assoc order :keychain keychain-order)]
      (is (= keychain-order (:keychain order))))))