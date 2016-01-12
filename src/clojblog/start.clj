(ns clojblog.start
  (:require [ring.adapter.jetty :as ring]
            [clojblog.handler :as handler]
            [clojblog.config :as config])
  (:gen-class))


(defn -main [& args]
  (config/load-config!)
  (ring/run-jetty handler/app {:port 8181})
  )

