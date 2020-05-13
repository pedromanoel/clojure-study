(ns alura.records.lesson4
  (:import (alura.records.lesson2 PrivatePatient InsuredPatient)))

(defmulti needs-authorization-multi? class)

(defmethod needs-authorization-multi?
  PrivatePatient
  [patient]
  true)

(defmethod needs-authorization-multi?
  InsuredPatient
  [patient]
  false)

(defn print-and-get-class
  [arg]
  (print arg)
  (class arg))

(defmulti multi-test print-and-get-class)
(defmethod multi-test
  (class [])
  [arg]
  (count arg))