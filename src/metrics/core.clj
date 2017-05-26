(ns metrics.core
  ;; (:gen-class :name metrics.core)
  (:use compojure.core metrics.helpers korma.core korma.db)
  (:require
    [clojure.string :as str]
    [clojure.data.json :as json]
    [ring.adapter.jetty :as ring]
    [compojure.core :as compojure]
    [compojure.route :as route]
    [compojure.handler :as handler]))


;; korma + sqlite
(defdb mydb {:classname "org.sqlite.JDBC"
             :subprotocol "sqlite"
             :subname "db/mydb.sqlite3"})

;; db entities
(defentity metrics
  ;; default select fields - commented out so that methods will specify directly
  ;; (entity-fields :name :value :timestamp)
)

(defn get_metric_average_in_range [begin_time end_time name]
  (select metrics
    (fields)
    (aggregate (avg :value) :average)
    (where (= :name name))
    (where (>= :timestamp begin_time))
    (where (<= :timestamp end_time))
    ))

(defn get_metric_by_time [name time]
  (select metrics
    (fields :name :value :timestamp)
    (where {
      :timestamp time
      :name name
      })))

(defn get_all_metrics [] (select metrics
    (fields :name :value :timestamp)
  ))

(defn get_metric [params]
  (let
    [{:keys [begin_time end_time name time]} params]
    (cond
      (and begin_time end_time name) (get_metric_average_in_range begin_time end_time name)
      (and name time) (get_metric_by_time name time)
      :else (get_all_metrics))))

(defn insert_metric [params]
  (let [{:keys [name value]} params]
    (insert metrics
      (values {:name name :value value})
    )
  ))

(defroutes main-routes
  (GET "/metrics" [ & params ]    (json/write-str (get_metric params )))
  (POST "/metrics" [ & params ]   (json/write-str (insert_metric params )))
)

(defn -main []
  (ring/run-jetty (handler/site main-routes) {:port 9000}))
