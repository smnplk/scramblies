(ns develop
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [scramblies.handlers :refer [app]]
            [clojure.tools.namespace.repl :refer [refresh]]))


(defonce server (atom nil))

(defn start []
  (reset! server
          (run-jetty #'app
                     {:port 3000 :join? false}))
  (pr "Started web server on port 3000...")
  :started)


(defn stop []
  (when @server
    (pr "Stopping web server...")
    (.stop @server)
    (reset! server nil)
    :stopped))


(defn reset []
  (stop)
  (refresh :after 'develop/start))
