(ns learning-clojure.comparing-entities-test
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer [match?]]
            [matcher-combinators.matchers :as m]
            [learning-clojure.comparing-entities :as c]))

(def pikachu-lv1 {:pokemon/id      10
                  :pokemon/name    "Pikachu"
                  :pokemon/level   1
                  :pokemon/moveset [:thundershock :growl]})
(def squirtle-lv1 {:pokemon/id      7
                   :pokemon/name    "Squirtle"
                   :pokemon/level   1
                   :pokemon/moveset [:tackle :tailwhip]})
(def pedro {:person/id   1000
            :person/name "Pedro"
            :person/age  37})

(deftest entities-delta-empty-before-and-after-test
  (testing "when before and after are empty"
    (is (match? (m/equals {:pokemon/id (m/equals {})
                           :person/id  (m/equals {})})
                (c/entities-delta []
                                  []
                                  [:pokemon/id :person/id])))))

(deftest entities-delta-before-and-after-equal-test
  (testing "return empty when before and after are the same"
    (is (match? {:pokemon/id
                 {10 {:added     empty?
                      :retracted empty?}}}
                (c/entities-delta [pikachu-lv1 pedro]
                                  [pikachu-lv1 pedro]
                                  [:pokemon/id])))))

(deftest entities-delta-empty-before-new-after-test
  (testing "when new entity when before is empty"
    (is (match? {:pokemon/id
                 {10 {:added     (m/equals pikachu-lv1)
                      :retracted empty?}}}
                (c/entities-delta []
                                  [pikachu-lv1]
                                  [:pokemon/id])))))

(deftest entities-delta-before-not-empty-and-new-after
  (testing "return new entity when before is not empty"
    (is (match? {:pokemon/id
                 {10 {:added     empty?
                      :retracted empty?}
                  7  {:added     (m/equals squirtle-lv1)
                      :retracted empty?}}
                 :person/id
                 {1000 {:added     empty?
                        :retracted empty?}}}
                (c/entities-delta [pedro pikachu-lv1]
                                  [pedro pikachu-lv1 squirtle-lv1]
                                  [:pokemon/id :person/id])))))

(deftest entities-delta-id-attr-not-found-test
  (testing "do not return entities when id-attr not given"
    (is (match? {:person/id empty?}
                (c/entities-delta []
                                  [pikachu-lv1]
                                  [:person/id])))))

(deftest entities-delta-attribute-changed-test
  (testing "return changed attributes"
    (is (match? {:pokemon/id
                 {10 {:added     (m/equals {:pokemon/level 2})
                      :retracted (m/equals {:pokemon/level 1})}}}
                (c/entities-delta [pikachu-lv1]
                                  [(assoc pikachu-lv1 :pokemon/level 2)]
                                  [:pokemon/id])))))

(deftest entities-delta-attribute-added-test
  (testing "return added attributes"
    (is (match? {:pokemon/id
                 {10 {:added     (m/equals {:pokemon/type [:electric]})
                      :retracted empty?}}}
                (c/entities-delta [pikachu-lv1]
                                  [(assoc pikachu-lv1 :pokemon/type [:electric])]
                                  [:pokemon/id])))))

(deftest entities-delta-attribute-retracted-test
  (testing "return retracted attributes"
    (is (match? {:pokemon/id
                 {10 {:added     empty?
                      :retracted (m/equals {:pokemon/name "Pikachu"})}}}
                (c/entities-delta [pikachu-lv1]
                                  [(dissoc pikachu-lv1 :pokemon/name)]
                                  [:pokemon/id])))))