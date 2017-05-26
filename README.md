# metrics

Metrics store API layer.

This is just about the first thing I ever wrote in Clojure (though not LISP/Scheme/Haskell), so it's mostly about feeling about the library space, the style of doing things, etc., though also built to be at least mostly production-ready.  Ragtime is used for migrations, http-kit plus compojure for routing and serving, and sqlite3 is used for the database.

Reads in HTTP parameters (more or less RESTful) and returns the raw JSON of the result (via org.clojure/data.json).

## Installation

You should just be able to delete db/mydb.sqlite3, and then run this in your `lein repl`:

```

(require '[ragtime.jdbc :as jdbc])
(require '[ragtime.repl :as repl])

(def config
     {:datastore  (jdbc/sql-database {:connection-uri "jdbc:sqlite:db/mydb.sqlite3"})
      :migrations (jdbc/load-resources "migrations")})

(repl/migrate config)
```

## Usage

Spin up a quick instance with lein:

`lein run`

## License

Copyright © 2017 Dustin van Schouwen
Distributed under GPLv3
