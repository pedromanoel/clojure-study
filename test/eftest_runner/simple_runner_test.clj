(ns eftest-runner.simple-runner-test
  (:require [clojure.test :refer [deftest testing is]])
  (:require [eftest-runner.simple-runner :refer :all]))

(deftest drop-first-test
  (testing "Remove first element from list"
    (is (empty? (drop-first ["first"])))
    (is (= ["dois"] (drop-first ["um" "dois"])))
    (is (= ["dois" "tres"] (drop-first ["um" "dois" "tres"])))))

(deftest normalize-to-test-file-test
  (testing "Normalizes file path to its test equivalent"
    (is (= "test/package/namespace_test.clj" (normalize-to-test-file "test/package/namespace_test.clj")))
    (is (= "test/package/namespace_test.clj" (normalize-to-test-file "src/package/namespace.clj")))
    ))
