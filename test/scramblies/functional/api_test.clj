(ns scramblies.functional.api-test
  (:require [ring.mock.request :as mock]
            [ring.util.codec :refer [form-encode]]
            [clojure.test :refer :all]
            [scramblies.handlers :refer [app]]))


(def mocked-post
  (-> (mock/request :post "/scramblie")
      (mock/content-type "application/x-www-form-urlencoded")))


(deftest endpoint-test
  (testing "Sending well formed POST  request to /scramblie returns status status code 200"
    (let [response (app (-> mocked-post
                            (mock/body (form-encode {:letters "abc" :word "abc"}))))]
      (is (= 200 (:status response)))))
  (testing "Sending POST request to /scramblie with nil/unset params returns status code 400"
    (let [response (app (-> mocked-post
                            (mock/body (form-encode {}))))]
      (is (= 400 (:status response)))))
  (testing "Sending well formed POST request with invalid params returns status code 422"
    (let [response (app (-> mocked-post
                            (mock/body (form-encode {:letters "" :word "  "}))))]
      (is (= 422 (:status response))))))


;; (run-tests)

;; Testing on cmd via curl
;; 1 - Well formed request with valid data
;; curl -i -H "Accept:application/transit+json" -X POST localhost:3000/scramblie -F "letters=abc" -F "word=abc"
;; 200

;; 2 - Well formed request with invalid data (empty strings)
;; curl -i -H "Accept:application/transit+json" -X POST localhost:3000/scramblie -F "letters=" -F "word="
;; 422

;; 2 - Invalid request, params not set
;; curl -i -H "Accept:application/transit+json" -X POST localhost:3000/scramblie
;; 400
