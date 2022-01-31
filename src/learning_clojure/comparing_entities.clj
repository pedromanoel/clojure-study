(ns learning-clojure.comparing-entities
  (:require [clojure.data :as data]))

(defn all-ids
  [{:keys [before-by-id after-by-id]}]
  (->> (merge before-by-id after-by-id)
       keys
       (remove nil?)))

(defn entity-delta
  [{:keys [before-by-id after-by-id]} id]
  (let [[retracted added] (data/diff (get before-by-id id {})
                                     (get after-by-id id {}))]
    (if (and (empty? retracted)
             (empty? added))
      {}
      {id {:retracted retracted
           :added     added}})))

(defn map-vals [f m]
  (let [map-val-xf (map (fn [[k v]] [k (f v)]))]
    (into {} map-val-xf m)))

(defn grouped-by-id-attr
  [entities id-attr]
  (->> entities
       (group-by id-attr)
       (map-vals first)))

(defn entity-deltas-for-id-attr
  [entities-before entities-after id-attr]
  (let [entities-by-id  {:before-by-id (grouped-by-id-attr entities-before id-attr)
                         :after-by-id  (grouped-by-id-attr entities-after id-attr)}
        entity-delta-xf (map (partial entity-delta entities-by-id))
        all-ids         (all-ids entities-by-id)]
    {id-attr (into {} entity-delta-xf all-ids)}))

(defn entities-delta
  "Return the added and retracted attributes of entities by comparing entities before and after.

  `id-attrs` - The id attributes to be compared. eg: [:person/id]
  `entities-before `  - A list of entities from before
  `entities-after`    - A list of entities from after

  Returns all added and retracted attributes in a single map grouped by id-attr, then id.
  Ex.:
     ```
     {:person/id
      {1234 {:added     {:person/name \"Pedro Manoel\"
             :retracted {:person/name \"Pedro\"}}}}}
     ```"
  [entities-before entities-after id-attrs]
  (into {}
        (map (partial entity-deltas-for-id-attr
                      entities-before
                      entities-after))
        id-attrs))
