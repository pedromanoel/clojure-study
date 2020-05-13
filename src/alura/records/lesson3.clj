(ns alura.records.lesson3
  (:require [alura.records.logic :as m.logic]))

(defn download-patient
  [id clock]
  (print (str "Downloading patient with id " id))
  {:id id :downloaded-at (m.logic/this-moment clock)})

(defn download-if-not-present
  ([cache id downloader]
   (if (contains? cache id)
     (get cache id)
     (downloader id))))