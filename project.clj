(defproject metrics "0.1.0-SNAPSHOT"
  :description "Metrics for the post-apocalypse scenario"
  :url "http://www.github.com/djvs/metrics"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                  [org.clojure/clojure "1.8.0"]
                  [compojure "1.6.0"]
                  [korma "0.4.0"]
                  [lobos "1.0.0-beta3"]
                  [org.xerial/sqlite-jdbc "3.7.15-M1"]
                  [org.clojure/data.json "0.2.6"]
                  [ring "1.6.1"]
                  ;; [ring/ring-core "1.6.1"]
                ]
  :main ^:skip-aot metrics.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
