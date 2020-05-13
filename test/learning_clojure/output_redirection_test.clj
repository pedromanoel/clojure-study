(ns learning-clojure.output-redirection-test
  (:require [clojure.test :refer :all]))

(deftest with-out-str-test
  (let [out-str (with-out-str
                  (println "Line 1")
                  (println "Line 2")
                  (println "Line 3"))]
    (is (= "Line 1\nLine 2\nLine 3\n" out-str))))
