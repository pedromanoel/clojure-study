(ns learning-clojure.trampoline)

(defn processed-state-machine
  "Process the list of commands in the state machine, returning
  the last processed status.

  Created with scoped functions and trampoline. Cannot use let
  instead of letfn because the functions cannot be referenced
  before being declared. With letfn it is possible to do so."
  [commands]
  (letfn [(state-a [[command & remaining-commands]]
            #(case command
               :a->b (state-b remaining-commands)
               :a->c (state-c remaining-commands)
               :a))
          (state-b [[command & remaining-commands]]
            #(case command
               :b->a (state-a remaining-commands)
               :b->c (state-c remaining-commands)
               :b))
          (state-c [[command & remaining-commands]]
            #(case command
               :c->a (state-a remaining-commands)
               :c->b (state-b remaining-commands)
               :final :final
               :c))]
    (trampoline state-a commands)))

(defn processed-state-machine
  "Process the list of commands in the state machine, returning
  the last processed status.

  Extract the case form from the returned lambdas"
  [commands]
  (letfn [(state-a [[command & remaining-commands]]
            (case command
              :a->b #(state-b remaining-commands)
              :a->c #(state-c remaining-commands)
              :a))
          (state-b [[command & remaining-commands]]
            (case command
              :b->a #(state-a remaining-commands)
              :b->c #(state-c remaining-commands)
              :b))
          (state-c [[command & remaining-commands]]
            (case command
              :c->a #(state-a remaining-commands)
              :c->b #(state-b remaining-commands)
              :final :final
              :c))]
    (trampoline state-a commands)))

(defn processed-state-machine
  "Process the list of commands in the state machine, returning
  the last processed status.

  Extract the case form from the returned lambdas
  and don't use destructuring assignment"
  [commands]
  (letfn [(state-a [commands]
            (case (first commands)
              :a->b #(state-b (rest commands))
              :a->c #(state-c (rest commands))
              :a))
          (state-b [commands]
            (case (first commands)
              :b->a #(state-a (rest commands))
              :b->c #(state-c (rest commands))
              :b))
          (state-c [commands]
            (case (first commands)
              :c->a #(state-a (rest commands))
              :c->b #(state-b (rest commands))
              :final :final
              :c))]
    (trampoline state-a commands)))