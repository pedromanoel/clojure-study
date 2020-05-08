(ns alura.records.lesson3
  (:require [alura.records.logic :as m.logic]))

(defn download-patient
  [id log clock]
  (.append log (str "Downloading patient with id " id))
  {:id id :downloaded-at (m.logic/this-moment clock)})

(defn download-if-not-present
  [patients id log clock]
  (if (contains? patients id)
    (get patients id)
    (download-patient id log clock)))