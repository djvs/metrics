(ns lobos.config
  (:use lobos.connectivity))

(def db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "./db/mydb.sqlite3"})

(open-global db)
