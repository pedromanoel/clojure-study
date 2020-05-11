(ns learning-clojure.trampoline)

(defn processed-state-machine [commands]
  ; define three scoped functions, state-a, state-b and state-c
  ; each function returns a lambda
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