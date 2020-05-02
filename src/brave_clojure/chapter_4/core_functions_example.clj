(ns brave-clojure.chapter-4.core-functions-example)

(defn- sum [numbers] (reduce + numbers))
(defn- average [numbers] (/ (sum numbers) (count numbers)))

(defn- keyed-sum [numbers] [:sum (sum numbers)])
(defn- keyed-average [numbers] [:avg (average numbers)])
(defn- keyed-count [numbers] [:count (count numbers)])

(defn- apply-fun-to [numbers] (fn [fun] (fun numbers)))

(defn stats [numbers]
  (into {} (map (apply-fun-to numbers) [keyed-sum keyed-average keyed-count])))

(defn- inc-val [entry]
  (let [[key val] entry]
    [key (inc val)]))

(defn inc-map-with-reduce [map]
  (reduce #(conj %1 (inc-val %2)) {} map))

(defn- filter-below [threshold]
  (fn [acc entry]
    (let [val (second entry)]
      (if (> val threshold)
        (conj acc entry)
        acc))))
(defn filter-entries-with-reduce [threshold map-to-be-filtered]
  (reduce (filter-below threshold) {} map-to-be-filtered))

(defn some-reduce [f coll]
  (let [none-found false
        truthy-element (fn [has-some element] (or has-some (f element)))]
    (reduce truthy-element none-found coll)))

;(defn filter-reduce [f coll]
;  (reduce (fn [acc el]
;            (if (f el)
;              (conj el acc)
;              acc)) [] coll))