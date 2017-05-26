(ns lobos.migrations
  (:refer-clojure :exclude [alter drop bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema config helpers)))

(defmigration add-metrics-table
  (up [] (create 
           (tbl :metrics
                (varchar :name 256)
                (text :value))))
  (down [] (drop (table :metrics))))
