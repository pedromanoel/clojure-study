(ns alura.records.lesson3-test
  (:require [clojure.test :refer :all]
            [alura.records.lesson3 :refer :all])
  (:import (java.time ZoneId Instant Clock)
           (java.io StringWriter)))

(defn capture-out [f]
  (binding [*out* (StringWriter.)]
    (f)))

(use-fixtures :each capture-out)

(let [zone (ZoneId/of "-03:00")
      instant (Instant/ofEpochMilli 3600000)
      clock (Clock/fixed instant zone)]
  (deftest download-patient-test
    (testing "log download at every call"
      (download-patient 1 clock)
      (download-patient 1 clock)
      (is (= (str "Downloading patient with id 1"
                  "Downloading patient with id 1")
             (str *out*)))))

  (deftest download-if-not-present-test
    (testing "using generic downloader"
      (let [downloader #(hash-map :id % :from-downloader true)]
        (is (= {:id 1 :from-downloader true}
               (download-if-not-present {} 1 downloader)))
        (is (= {:id 1 :from-cache true}
               (download-if-not-present {1 {:id 1 :from-cache true}} 1 downloader))))))

  (deftest download-if-not-present-not-in-cache-test
    (testing "call download when patient not in cache"
      (let [empty-cache {}
            downloader #(do (print "called") {:downloaded true :id %})
            actual (download-if-not-present empty-cache 1 downloader)]
        (is (= {:downloaded true :id 1} actual))
        (is (= "called" (str *out*))))))

  (deftest download-if-not-present-in-cache-test
    (testing "do not call when patient in cache"
      (let [downloader #(print "called")
            cache {10 {:id 10 :cached true}}
            actual (download-if-not-present cache 10 downloader)]
        (is (= {:id 10 :cached true} actual))
        (is (empty? (str *out*)))))))
