(ns moniclj.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [moniclj.check-dao :as dao]
            [ring.util.response :as resp]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-body]]
            [ring.middleware.json :refer [wrap-json-response]]
            [liberator.core :refer [resource defresource]]))

(def fix-object (fn [check] (assoc check :_id (str (check :_id)))))

(defroutes app-routes
  (GET "/" [] (resp/resource-response "index.html" {:root "app"}))
  (context "/api/check" []
           (GET "/" [] (resource :available-media-types ["application/json"]
                                 :handle-ok (map fix-object (dao/get-checks)))))
  
  (route/resources "/app" {:root "app"})
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-params) (wrap-json-body) (wrap-json-response)))
