(ns scramblies.template
  (:require [hiccup.page :as page]))


(defn main-page []
  (page/html5 {:lang "en"}
              [:head
               [:title "Scramblies Page"]
               (page/include-css "/css/simple.min.css" "/css/custom.css")
               [:body
                [:div#app-container]
                (page/include-js "/js/compiled/main.js")
                [:script {:type "text/javascript"} "scramblies.core.init();"]]]))
