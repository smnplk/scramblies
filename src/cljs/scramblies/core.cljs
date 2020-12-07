(ns scramblies.core
  (:require [reagent.core :as reagent :refer [atom]]
            [reagent.dom :as rd]
            [re-frame.core :as rf]
            [scramblies.handlers]
            [scramblies.subs]
            [scramblies.views.main :refer [main-view]]))

(enable-console-print!)

(defn render []
  (rd/render [main-view] (js/document.getElementById "app-container")))


(defn ^:export init []
  (rf/dispatch-sync [:initialize-db])
  (render))
