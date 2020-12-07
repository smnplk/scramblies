(ns scramblies.subs
  (:require [re-frame.core :as rf])
  (:require-macros [reagent.ratom :refer [reaction]]))


(rf/reg-sub-raw
   :scramblie?
   (fn [db _]
     (reaction (-> @db :scramblie?))))


(rf/reg-sub-raw
 :error-msg
 (fn [db _]
   (reaction (-> @db :error-msg))))


(rf/reg-sub-raw
 :form-dirty?
 (fn [db _]
   (reaction (-> @db :form-dirty?))))
