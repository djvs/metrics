# metrics

Metrics store API layer

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
