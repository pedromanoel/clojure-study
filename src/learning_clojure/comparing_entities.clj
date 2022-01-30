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
    {id {:retracted retracted
         :added     added}}))

(defn entity-deltas-by-id
  [entities-by-id]
  (into {}
        (map (partial entity-delta entities-by-id))
        (all-ids entities-by-id)))

(defn map-vals [f m]
  (into {}
        (map (fn [[k v]] [k (f v)]))
        m))

(defn grouped-by-id
  [entities id-attr]
  (->> entities
       (group-by id-attr)
       (map-vals first)))

(defn entity-deltas-for-id-attr
  [entities-before entities-after id-attr]
  (let [entities-by-id {:before-by-id (grouped-by-id entities-before id-attr)
                        :after-by-id  (grouped-by-id entities-after id-attr)}]
    {id-attr (entity-deltas-by-id entities-by-id)}))

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
