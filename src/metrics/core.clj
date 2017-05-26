(ns metrics.core
  (:gen-class :name metrics.core)
  (:use compojure.core 
        org.httpkit.server
        [compojure.handler :only [site]]
        )
  (:require
    [metrics.routefns :as rtfns]
    [ring.adapter.jetty :as ring]
    [clojure.data.json :as json]
    ))

;; ROUTE DEFINITIONS
(defroutes main-routes
  (GET "/metrics" [ & params ]    (json/write-str (rtfns/get_metric params )))
  (POST "/metrics" [ & params ]   (json/write-str (rtfns/insert_metric params )))
)

(defn -main []
  (run-server (site #'main-routes) {:port 9000}))
