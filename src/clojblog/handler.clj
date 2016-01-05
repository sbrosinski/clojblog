(ns clojblog.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojblog.views :as views]
            [clojblog.config :as config]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
           (GET "/" [] (views/index))
           (GET "/post/:slug" [slug] (views/show-post slug))
           (route/not-found "Not Found"))

(defn init []
  "Inits web app. Run on startup by Ring as defined in project.clj."
  (config/load-config!))

(def app
  (wrap-defaults app-routes site-defaults))
