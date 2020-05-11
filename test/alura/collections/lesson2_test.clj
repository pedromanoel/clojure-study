(ns alura.collections.lesson2-test
  (:require [clojure.test :refer :all])
  (:require [alura.collections.lesson2 :refer :all]))

(deftest my-count-test
  (is (= 6 (my-count ["Daniel" "William" "Carl" "Paul" "Lucy" "Anna"])))
  (is (= 0 (my-count []))))

;(deftest my-count-using-loop-test
;  (is (= 6 (my-count-using-loop ["Daniel" "William" "Carl" "Paul" "Lucy" "Anna"])))
;  (is (= 0 (my-count-using-loop []))))
