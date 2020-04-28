(ns brave-clojure.chapter-4-core-functions)

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
