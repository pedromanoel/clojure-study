(defproject learning-clojure "0.1.0-SNAPSHOT"
  :description "My Clojure learning repository"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [org.clojure/clojure "1.10.1"]
                 [eftest "0.5.9"]
                 [org.clojure/test.check "1.1.1"]]
  :plugins [
            [lein-cloverage "1.1.2"]
            [lein-exec "0.3.7"]]
  :main ^:skip-aot learning-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :aliases {"run-tests" ["exec" "-p" "src/eftest_runner/main.clj"]})
