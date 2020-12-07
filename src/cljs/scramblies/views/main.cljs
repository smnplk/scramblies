(ns scramblies.views.main
  (:require [scramblies.views.components :as c]
            [re-frame.core :as rf]))


(defn input-form []
  [c/form
   [c/form-title "Scramblies"]
   [c/form-detail "Checks if you can genereta a word from letters"]
   [c/form-field
    [c/label "Letters"]
    [c/text-input {:on-change #(rf/dispatch [:update-form :letters (.. % -target -value)])}]]
   [c/form-field
    [c/label "Word"]
    [c/text-input {:on-change #(rf/dispatch [:update-form :word (.. % -target -value)])}]]
   [c/form-button {:on-click  #(rf/dispatch [:post-form!])} "Check!"]])


(defn server-response-success [scramblie?]
  (if scramblie?
    [c/alert-success "YES!"]
    [c/alert-warning "NOPE!"]))


(defn server-response-msg []
  (let [scramblie? (rf/subscribe [:scramblie?])
        error-msg  (rf/subscribe [:error-msg])]
    (if @error-msg
      [c/alert-error @error-msg]
      [server-response-success @scramblie?])))


(defn main-view []
  (let [form-dirty? (rf/subscribe [:form-dirty?])]
    [:div.center-div
      [:div {:style {:max-width "50%" :flex "1"}}
        [input-form]
        (when @form-dirty?
          [server-response-msg])]]))
