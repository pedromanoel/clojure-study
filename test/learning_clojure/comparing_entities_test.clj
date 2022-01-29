(ns learning-clojure.comparing-entities-test
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer [match?]]
            [matcher-combinators.matchers :as m]
            [learning-clojure.comparing-entities :as c]))

(deftest bla
  (let [pikachu-lv1  {:pokemon/id      10
                      :pokemon/name    "Pikachu"
                      :pokemon/level   1
                      :pokemon/moveset [:thundershock :growl]}
        squirtle-lv1 {:pokemon/id      7
                      :pokemon/name    "Squirtle"
                      :pokemon/level   1
                      :pokemon/moveset [:tackle :tailwhip]}
        pedro        {:person/id   1000
                      :person/name "Pedro"
                      :person/age  37}]

    (testing "return empty when before and after are the same"
      (is (match? {:pokemon/id {:added     []
                                :retracted []}}
                  (c/entities-delta [:pokemon/id]
                                    [pikachu-lv1 pedro]
                                    [pikachu-lv1 pedro]))))

    (testing "return new entity when before is empty"
      (is (match? {:pokemon/id {:added     [(m/equals pikachu-lv1)]
                                :retracted []}}
                  (c/entities-delta [:pokemon/id]
                                    []
                                    [pikachu-lv1]))))

    (testing "return new entity when before is not empty"
      (is (match? {:pokemon/id {:added     [(m/equals squirtle-lv1)]
                                :retracted []}
                   :person/id  {:added     []
                                :retracted []}}
                  (c/entities-delta [:pokemon/id :person/id]
                                    [pedro pikachu-lv1]
                                    [pedro pikachu-lv1 squirtle-lv1]))))

    (testing "do not return entities when id-attr not given"
      (is (match? {:pokemon/id m/absent}
                  (c/entities-delta [:person/id]
                                    []
                                    [pedro pikachu-lv1]))))

    (testing "return changed attributes"
      (is (match? {:pokemon/id
                   {:added     [(m/equals {:pokemon/id    10
                                           :pokemon/level 2})]
                    :retracted [(m/equals {:pokemon/id    10
                                           :pokemon/level 1})]}}
                  (c/entities-delta [:pokemon/id]
                                    [pikachu-lv1]
                                    [(assoc pikachu-lv1 :pokemon/level 2)]))))

    (testing "return added attributes"
      (is (match? {:pokemon/id
                   {:added     [(m/equals {:pokemon/id   10
                                           :pokemon/type [:electric]})]
                    :retracted []}}
                  (c/entities-delta [:pokemon/id]
                                    [pikachu-lv1]
                                    [(assoc pikachu-lv1 :pokemon/type [:electric])]))))

    (testing "return retracted attributes"
      (is (match? {:pokemon/id
                   {:added     []
                    :retracted [(m/equals {:pokemon/id   10
                                           :pokemon/name "Pikachu"})]}}
                  (c/entities-delta [:pokemon/id]
                                    [pikachu-lv1]
                                    [(dissoc pikachu-lv1 :pokemon/name)]))))))