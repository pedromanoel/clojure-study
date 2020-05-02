(ns eftest-runner.main
  (:require [eftest-runner.simple-runner :refer :all]))

(let [summary (apply run-tests (drop-first *command-line-args*))]
  (if (not= 0 (:fail summary)) (System/exit 1)))
