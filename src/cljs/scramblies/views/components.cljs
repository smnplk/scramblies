(ns scramblies.views.components
  (:require [reagent.core :as reagent]))


(defn form []
  (into
   [:div.siimple-form]
   (reagent/children (reagent/current-component))))


(defn form-title [title]
  [:div.siimple-form-title title])


(defn form-detail [detail]
  [:div.siimple-form-detail detail])


(defn label [text]
  [:div.siimple-form-field-label text])


(defn text-input [props]
  [:input.siimple-input.siimple-input--fluid (merge {:type "text"} props)])


(defn form-field []
  (into [:div.siimple-form-field]
        (reagent/children (reagent/current-component))))


(defn form-button [props text]
  [:div.siimple-btn.siimple-btn--blue props text])


(defn alert-success [msg]
  [:div.siimple-alert.siimple-alert--success.siimple--text-center msg])


(defn alert-warning [msg]
  [:div.siimple-alert.siimple-alert--warning.siimple--text-center msg])


(defn alert-error [msg]
  [:div.siimple-alert.siimple-alert--error.siimple--text-center msg])
