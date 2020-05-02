(ns brave-clojure.chapter-4.fwpd-examples
  (:require [clojure.string :as s]))

(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int [^String str] (Integer/valueOf str))

(def conversions {:name identity :glitter-index str->int})

(defn convert [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert CSV into rows of columns"
  [string]
  (map #(s/split % #",") (s/split-lines string)))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [[first second]]
         {:name first :glitter-index (str->int second)})
       rows))

(defn mapify-2
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (let [
               vamp-key-value-pairs (map vector vamp-keys unmapped-row)
               accumulate-entry (fn [row-map [vamp-key value]]
                                  (assoc row-map vamp-key (convert vamp-key value)))]
           (reduce accumulate-entry {} vamp-key-value-pairs)))
       rows))

(defn glitter-filter
  [minimum-glitter records]
  (map #(:name %) (filter #(>= (:glitter-index %) minimum-glitter) records)))

(def validations {:glitter-index number? :name #(and (string? %) (not (s/blank? %)))})

(defn validate [validations record]
  (reduce (fn
            [is-valid [key validation]]
            (and is-valid (contains? record key) (validation (key record))))
          true
          validations))

(defn append
  [suspects new-suspect]
  (if (validate validations new-suspect)
    (conj suspects new-suspect)
    suspects))

(defn map->csv
  [suspects]
  (->> suspects
       (map #(s/join "," (vals %)))
       (s/join "\n"))
  )
