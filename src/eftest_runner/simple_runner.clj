(ns eftest-runner.simple-runner
  (:require [clojure.string :as s])
  (:require [eftest.runner :as ef]))

(defn- in-test-folder [path]
  (s/replace path #"src" "test"))

(defn- as-test-file [path]
  (-> path
      (s/replace #"_test.clj" ".clj")
      (s/replace #".clj" "_test.clj")))

(defn drop-first
  [list] (rest list))

(defn normalize-to-test-file
  [file-path]
  (-> file-path
      in-test-folder
      as-test-file))

(defn run-tests
  ([] (ef/run-tests (ef/find-tests "test")))
  ([file-path] (ef/run-tests (ef/find-tests (normalize-to-test-file file-path)))))
