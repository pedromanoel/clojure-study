(ns alura.records.lesson5-test
  (:require [clojure.test :refer :all])
  (:require [alura.records.lesson5 :as r.lesson5]))

(def covered-exam :blood-type)
(def uncovered-exam :x-ray)
(def private-patient {})
(def insured-patient {:health-insurance [covered-exam]})

(defn urgent [patient] (assoc patient :situation :urgent))
(defn request
  ([patient]
   (request patient covered-exam 50))
  ([patient exam cost]
   {:patient patient :exam exam :cost cost}))

(deftest authorization-type-test
  (testing "always authorize urgent cases"
    (is (= :always-authorize (r.lesson5/authorization-type (request (urgent insured-patient)))))
    (is (= :always-authorize (r.lesson5/authorization-type (request (urgent private-patient))))))
  (testing "check insurance for insured patients"
    (is (= :check-insurance (r.lesson5/authorization-type (request insured-patient)))))
  (testing "check cost for private patients"
    (is (= :check-cost (r.lesson5/authorization-type (request private-patient))))))

(deftest needs-authorization-for-request?-test
  (testing "always authorize urgent cases"
    (is (false? (r.lesson5/needs-authorization-for-request? (request (urgent insured-patient)))))
    (is (false? (r.lesson5/needs-authorization-for-request? (request (urgent private-patient))))))
  (testing "check insured patients"
    (is (false? (r.lesson5/needs-authorization-for-request? (request insured-patient covered-exam 50))))
    (is (true? (r.lesson5/needs-authorization-for-request? (request insured-patient uncovered-exam 50)))))
  (testing "check cost for private patients"
    (is (false? (r.lesson5/needs-authorization-for-request? (request private-patient covered-exam 49))))
    (is (true? (r.lesson5/needs-authorization-for-request? (request private-patient uncovered-exam 50))))))
