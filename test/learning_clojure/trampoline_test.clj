(ns learning-clojure.trampoline-test
  (:require [clojure.test :refer :all])
  (:require [learning-clojure.trampoline :refer [processed-state-machine]]))

(deftest processed-state-machine-test
  (testing "start at :a"
    (is (= :a (processed-state-machine []))))
  (testing "transition from a"
    (testing "to :a when transition does not exist"
      (is (= :a (processed-state-machine [:not-a-command]))))
    (testing "to :b"
      (is (= :b (processed-state-machine [:a->b]))))
    (testing "to :c"
      (is (= :c (processed-state-machine [:a->c])))))
  (testing "transition from :b"
    (testing "to :b when transition does not exist"
      (is (= :b (processed-state-machine [:a->b :not-a-command]))))
    (testing "to :a"
      (is (= :a (processed-state-machine [:a->b :b->a]))))
    (testing "to :c"
      (is (= :c (processed-state-machine [:a->b :b->c])))))
  (testing "transition from :c"
    (testing "to :c when transition does not exist"
      (is (= :c (processed-state-machine [:a->c :not-a-command]))))
    (testing "to :a"
      (is (= :a (processed-state-machine [:a->c :c->a]))))
    (testing "to :b"
      (is (= :b (processed-state-machine [:a->c :c->b]))))
    (testing "to :final"
      (is (= :final (processed-state-machine [:a->c :final])))))
  (testing "several transactions"
    (is (= :final (processed-state-machine [:a->b
                                            :b->a
                                            :a->c
                                            :c->a
                                            :a->c
                                            :c->b
                                            :b->c
                                            :final]))))
  )
