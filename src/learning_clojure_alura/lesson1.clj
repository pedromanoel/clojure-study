(ns learning-clojure-alura.lesson1)

; example of patients list
; { 15 { :id 15 :name "Patient 15" }, 23 { :id 23 :name "Patient 23" } }

(defn add-patient
  [patient patients]
  (if-let [id (:id patient)]
      (assoc patients id patient)
      (throw (ex-info "Patient missing ID" {:patient patient}))))
