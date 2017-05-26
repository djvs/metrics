(ns metrics.routefns
  (:use korma.core korma.db)
  )

;; database setup - korma + sqlite
(defdb mydb {:classname "org.sqlite.JDBC"
             :subprotocol "sqlite"
             :subname "db/mydb.sqlite3"})


;; database entities (models)
(defentity metrics
  ;; default select fields - commented out so that methods will specify directly
  ;; (entity-fields :name :value :timestamp)
)


;; Basically just the "controller" functions
(defn get_metric_average_in_range [begin_time end_time name]
  (select metrics
    (fields)
    (aggregate (avg :value) :average)
    (where (= :name name))
    (where (>= :timestamp begin_time))
    (where (<= :timestamp end_time))
    ))

;; get one metric at a given time
(defn get_metric_by_time [name time]
  (select metrics
    (fields :name :value :timestamp)
    (where {:timestamp [<= time]})
    (where {
      :name name
      })
      (order :timestamp :DESC)
      (limit 1)
  ))

(defn get_latest_metric [name]
  (select metrics
    (fields :name :value :timestamp)
    (where {
      :name name
      })
      (order :timestamp :DESC)
      (limit 1)
  ))

(defn get_all_metrics [] (select metrics
    (fields :name :value :timestamp)
  ))

(defn get_metric [params]
  (let
    [{:keys [begin_time end_time name time]} params]
    (cond
      (and begin_time end_time name) (get_metric_average_in_range begin_time end_time name)
      (and name time) (get_metric_by_time name time)
      name (get_latest_metric name)
      :else (get_all_metrics))))

(defn insert_metric [params]
  (let [{:keys [name value]} params]
    (insert metrics
      (values {:name name :value value})
    )
  ))
