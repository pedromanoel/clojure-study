(ns alura.records.lesson5
  (:require [clojure.test :refer :all]))

(defn authorization-type
  [{{:keys [situation health-insurance]} :patient}]
  (cond
    (= :urgent situation) :always-authorize
    (seq health-insurance) :check-insurance
    :else :check-cost))

(defmulti needs-authorization-for-request? authorization-type)

(defmethod needs-authorization-for-request?
  :always-authorize
  [_]
  false)

(defmethod needs-authorization-for-request?
  :check-insurance
  [request]
  (let [{:keys                      [exam]
         {:keys [health-insurance]} :patient} request]
    (not-any? #(= % exam) health-insurance)))

(defmethod needs-authorization-for-request?
  :check-cost
  [request]
  (let [{:keys [cost]} request]
    (>= cost 50)))