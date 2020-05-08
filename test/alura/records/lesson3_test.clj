(ns alura.records.lesson3-test
  (:require [clojure.test :refer :all]
            [alura.records.lesson3 :refer :all])
  (:import (java.time ZoneId Instant Clock)))

(let [zone (ZoneId/of "-03:00")
      instant (Instant/ofEpochMilli 3600000)
      clock (Clock/fixed instant zone)]
  (deftest download-patient-test
    (testing "log download at every call"
      (let [log (StringBuilder.)]
        (is (= {:id 1 :downloaded-at 3600000}
               (download-patient 1 log clock)))
        (is (= {:id 1 :downloaded-at 3600000}
               (download-patient 1 log clock)))
        (is (= (str "Downloading patient with id 1"
                    "Downloading patient with id 1")
               (.toString log))))))

  (deftest download-if-not-present-test
    (testing "call download when patient not in cache"
      (let [log (StringBuilder.)]
        (is (= {:id 1 :downloaded-at 3600000}
               (download-if-not-present {} 1 log clock)))
        (is (= "Downloading patient with id 1"
               (.toString log)))))
    (testing "do not call when patient in cache"
      (let [log (StringBuilder.)]
        (is (= {:id 1 :cached true}
               (download-if-not-present {1 {:id 1 :cached true}} 1 log clock)))
        (is (empty? (.toString log)))))))
