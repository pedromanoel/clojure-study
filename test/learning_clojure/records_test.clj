(ns learning-clojure.records-test
  (:require [clojure.test :refer :all])
  (:import (clojure.lang PersistentArrayMap)))

(defrecord Color [r g b])

(let [color (map->Color {:r 1 :g 0.5 :b 0.5})]

  (deftest assoc-record-adds-element
    (is (= Color (type (assoc color :r 10)))))

  (deftest dissoc-record-returns-map
    (is (= PersistentArrayMap (type (dissoc color :r))))))