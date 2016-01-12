(defproject clojblog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :main clojblog.start
  :aot [clojblog.start]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [markdown-clj "0.9.85"]
                 [selmer "0.9.9"]
                 [slugger "1.0.1"]
                 [org.clojure/tools.logging "0.3.1"]        ; use logback for logging
                 [ch.qos.logback/logback-classic "1.1.3"]   ; use logback for logging
                 [com.dropbox.core/dropbox-core-sdk "2.0-beta-4"]
                 [clj-time "0.8.0"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:init clojblog.handler/init
         :handler clojblog.handler/app}
  :profiles
  {:dev {
         :dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}
   :uberjar {:aot :all}})
