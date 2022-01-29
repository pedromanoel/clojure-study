(ns learning-clojure.comparing-entities
  (:require [clojure.data :as data]))

(defn diff-first-and-second
  [id-attr entities-second]
  (fn [first-entity]
    (let [first-id              (id-attr first-entity)
          second-entities-by-id (group-by id-attr entities-second)
          maybe-second-entity   (get-in second-entities-by-id [first-id 0])]
      (when-some [in-first-only (first (data/diff first-entity maybe-second-entity))]
        (assoc in-first-only id-attr first-id)))))

(defn attrs-in-first-not-in-second
  [id-attr entities-first entities-second]
  (->> (filter id-attr entities-first)
       (map (diff-first-and-second id-attr entities-second))
       (remove nil?)))

(defn entity-delta
  [before after]
  (fn [id-attr]
    {id-attr
     {:added     (attrs-in-first-not-in-second id-attr after before)
      :retracted (attrs-in-first-not-in-second id-attr before after)}}))

(defn entities-delta
  "Return the added and retracted attributes of entities by comparing before and after.

  `id-attrs` - The attributes used to identify an entity.
               Must be namespaced keywords named `id`. eg: :person/id
  `before `  - A list of entities from before
  `after`    - A list of entities from after

  Entities with same id are compared and a map with :added and :retracted attributes is created.

  Returns all entities in a single map grouped by id-attr."
  [id-attrs before after]
  (->> id-attrs
       (map (entity-delta before after))
       (reduce merge)))
