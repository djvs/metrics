# metrics

Metrics store API layer.

This is just about the first thing I ever wrote in Clojure (though not LISP/Scheme/Haskell), so it's mostly about feeling about the library space, the style of doing things, etc., though also built to be at least mostly production-ready.  Ragtime is used for migrations, http-kit plus compojure for routing and serving, and sqlite3 is used for the database.

Reads in HTTP parameters (more or less RESTful) and returns the raw JSON of the result (via org.clojure/data.json).

## Methods
```
POST /metrics   # params 'name', 'value'.  Adds a timestamped metric to the DB with name and value.  Value assumed to be numeric (bigint).
GET /metrics?begin_time=2017-05-20%2000:00:00&end_time=2017-05-31%2000:00:00&name=uptime    # Returns average of metric 'uptime' between May 20th and May 31st.
GET /metrics?time=2017-05-20%2000:00:00&name=uptime # Returns latest value of metric 'uptime' at the beginning of May 20th.
GET /metrics?name=uptime # Returns latest value of metric 'uptime' at present.
```

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
