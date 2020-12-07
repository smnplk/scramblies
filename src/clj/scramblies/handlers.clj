(ns scramblies.handlers
  (:require [scramblies.api :as scramblies-api]
            [scramblies.middleware.errors :refer [wrap-error-handling]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [site-defaults  wrap-defaults]]
            [scramblies.template :refer [main-page]]
            [ring.middleware.resource :refer [wrap-resource]]
            [muuntaja.middleware :as mw]
            [ring.logger.timbre :as timbre-logger]
            [clojure.string :as st]))


(defn ^:private get-param [param request]
  (some-> request
          (get-in [:params param])
          (st/trim)))


(defn scramblie-handler [request]
  (let [letters (get-param :letters request)
        word    (get-param :word request)]
    {:status 200 :body [(scramblies-api/scramble? letters word)]}))


(defroutes approutes
  (GET "/" [] (main-page))

  (POST "/scramblie" req (scramblie-handler req))

  (route/not-found "Can't find what you're looking for."))


(def app
  (-> approutes
      (wrap-error-handling)
      (timbre-logger/wrap-with-logger)
      (mw/wrap-format)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))
      (wrap-resource "public")))
