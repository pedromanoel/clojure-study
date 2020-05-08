(ns alura.records.model
  (:import (java.time ZoneId LocalDateTime)))

(defprotocol Dateable
  (to-ms [this]))

(extend-type Number Dateable
  (to-ms [this] this))

(extend-type LocalDateTime Dateable
  (to-ms [this]
    (let [zone (ZoneId/systemDefault)]
      (-> this
          (.atZone zone)
          .toInstant
          .toEpochMilli))))

(extend-type String Dateable
  (to-ms [this]
    (-> this
        LocalDateTime/parse
        to-ms)))