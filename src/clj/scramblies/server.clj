(ns scramblies.server
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [scramblies.handlers :refer [app]]))


(defn -main []
  (run-jetty app {:port 3000}))
