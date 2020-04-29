(ns brave-clojure.chapter-4-fwpd
  (:require [clojure.string :as str]))
(def filename "suspects.csv")

(def vamp-keys [:name :glitter-index])

(defn str->int [^String str] (Integer/valueOf str))

(def conversions {:name identity :glitter-index str->int})

(defn convert [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert CSV into rows of columns"
  [string]
  (map #(str/split % #",") (str/split-lines string)))

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
  (filter #(>= (:glitter-index %) minimum-glitter) records))