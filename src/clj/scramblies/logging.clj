(ns scramblies.logging
  (:require [taoensso.timbre :as timbre]))


(timbre/merge-config!
 {:appenders {:spit (timbre/spit-appender {:fname "server.log"})}})
