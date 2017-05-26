(ns metrics.core
  (:gen-class :name metrics.core)
  (:use compojure.core)
  (:require
    [metrics.routefns :as rtfns]
    [ring.adapter.jetty :as ring]
    [compojure.core :as compojure]
    [compojure.route :as route]
    [compojure.handler :as handler]
    [clojure.data.json :as json]
    ))

;; ROUTE DEFINITIONS
(defroutes main-routes
  (GET "/metrics" [ & params ]    (json/write-str (rtfns/get_metric params )))
  (POST "/metrics" [ & params ]   (json/write-str (rtfns/insert_metric params )))
)

(defn -main []
  (ring/run-jetty (handler/site main-routes) {:port 9000}))
