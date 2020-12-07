(ns scramblies.handlers
  (:require [re-frame.core :as rf]
            [ajax.core :as ajax]
            [day8.re-frame.http-fx]
            [scramblies.db :as db]))


(rf/reg-event-db
 :initialize-db
 (fn [_ _]
   db/default-db))


(rf/reg-event-db
 :update-form
 (fn [db [_ attribute new-val]]
   (assoc db attribute new-val)))


(rf/reg-event-fx
 :post-form!
 (fn [{:keys [db]} _]
   {:db db
    :http-xhrio {:method :post
                 :uri "/scramblie"
                 :timeout 8000
                 :format (ajax/url-request-format)
                 :response-format (ajax/transit-response-format)
                 :params db
                 :on-success [:post-form-success]
                 :on-failure [:post-form-failure]}}))


;; server returns result in a vector, so we need to destructure first element and bind it to scramblie? var
(rf/reg-event-db
 :post-form-success
 (fn [db [_ [scramblie?]]]
   (-> db
       (assoc :scramblie? scramblie? :form-dirty? true)
       (dissoc :error-msg))))


(rf/reg-event-db
 :post-form-failure
 (fn [db [_ result]]
   (let [error-msg (-> result :response first)]
     (-> db
         (assoc :error-msg error-msg :form-dirty? true)
         (dissoc :scramblie?)))))
