(ns lobos.config
  (:use lobos.connectivity))

(defn h_pkey [table]
  (integer table :id :auto-inc :primary-key))

(defn h_timestamp [table]
  (-> table
      (timestamp :timestamp (default (now)))))

;; basically copied from https://github.com/budu/lobos
(defmacro tbl [name & elements]
  (-> (table ~name)
      (h_timestamp)
      ~@(reverse elements)
      (h_pkey)))
