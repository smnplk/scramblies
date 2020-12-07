(ns scramblies.middleware.errors)


(defn bad-request [message]
  (ex-info message {:type :bad-request}))


(defn unprocessable-entity [message]
  (ex-info message {:type :unprocessable-entity}))


(defn ^:private get-response-from-error
  "Converts a thrown error into a response that should be sent back to client"
  [error]
  (let [message (.getMessage error)]
    (case (:type (ex-data error))
      :bad-request          {:status 400 :body [message]}
      :unprocessable-entity {:status 422 :body [message]}
      {:status 500 :body ["Internal Server Error"]})))


(defn wrap-error-handling
  "Ring middleware that catches exceptions and returns appropriate response based on exception"
  [handler]
  (fn [req]
    (try
      (handler req)
      (catch Exception e
        (get-response-from-error e)))))
