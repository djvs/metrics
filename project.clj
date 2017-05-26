(defproject metrics "0.1.0-SNAPSHOT"
  :description "Metrics for the post-apocalypse scenario"
  :url "http://www.github.com/djvs/metrics"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                  [org.clojure/clojure "1.8.0"]

                  [compojure "1.6.0"]
                  [korma "0.4.3"]
                  [ring "1.6.0"]
                  [ragtime "0.7.1"]

                  [org.clojure/data.json "0.2.6"]
                  [org.xerial/sqlite-jdbc "3.18.0"]
                  [org.clojure/java.jdbc "0.7.0-alpha3"]

                  ;; [org.xerial/sqlite-jdbc "3.18.0"]

                  ;; [org.clojure/java.jdbc "0.6.1"]
                  ;; [org.xerial/sqlite-jdbc "3.8.7"]

                  ;; [org.xerial/sqlite-jdbc "3.8.11"]
                  ;; [org.clojure/java.jdbc "0.5.5"]
                  ;; [org.clojure/java.jdbc "0.7.0-alpha3"]

                  ;; [ring/ring-core "1.6.1"]
                  ;; [lobos "1.0.0-beta3"]
                ]
  :main metrics.core
  :aot [metrics.core]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
