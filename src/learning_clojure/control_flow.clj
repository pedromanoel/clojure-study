(ns learning-clojure.control-flow
  (:gen-class))

(defn is-truthy? [x]
  (let [x-as-string (pr-str x)]
    (if x
      (str x-as-string " is truthy")
      (str x-as-string " is falsy"))))
