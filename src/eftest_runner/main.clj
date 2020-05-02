(ns eftest-runner.main
  (:require [eftest-runner.simple-runner :refer :all]))

(apply run-tests (drop-first *command-line-args*))
