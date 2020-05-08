(ns alura.records.logic
  (:require [alura.records.model :as h.model])
  (:import (java.time LocalDateTime)))


(defn this-moment
  [clock]
  (h.model/to-ms (LocalDateTime/now clock)))