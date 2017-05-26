(ns metrics.core
  (:gen-class :name metrics.core)
  (:use compojure.core metrics.helpers korma.core korma.db)
  (:require
    [clojure.string :as str]
    [ring.adapter.jetty :as ring]
    [compojure.core :as compojure]
    [compojure.route :as route]
    [compojure.handler :as handler]))


;; korma + sqlite
(defdb mydb {:classname "org.sqlite.JDBC"
             :subprotocol "sqlite"
             :subname "db/dev.sqlite3"})

;; db entities
(declare metrics)
(defentity metrics
  ;; default select fields
  (entity-fields :name :value :timestamp)
)

(defn get_metric_average_in_range [begin_time end_time]
  (
    (select metrics
      (aggregate (avg (:value)))
      (where (>= :timestamp begin_time))
      (where (<= :timestamp end_time))
      )))

(defn get_metric_by_time [metric time]
  (
    (select metrics
      (where {
        :timestamp time
        :name metric
        }))))

(defn get_all_metrics []
  (
    (select metrics)
    ))

(defn get_metric [params] (
  (let [
    {
      begin_time :begin_time
      end_time :end_time
      metric :metrics
      time :time
    } params]
    (cond
      (and begin_time end_time) (get_metric_average_in_range begin_time end_time)
      (and metric time) (get_metric_by_time metric time)
      :else (get_all_metrics)
    )
  )
))

(defn set_metric [params] (
  (let [{
      name :name
      value :value
    } params]
    (insert metrics
      (values {:name name :value value})
    )
  )
)

(defroutes main-routes
  (GET "/metrics" [ & params ]    (get_metric params ))
  (POST "/metrics" [ & params ]   (insert_metric params ))
)

(defn -main []
  (ring/run-jetty (handler/site main-routes) {:port 9000}))
