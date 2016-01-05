(defproject clojblog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [markdown-clj "0.9.85"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:init clojblog.handler/init
         :handler clojblog.handler/app}
  :profiles
  {:dev {
         :dependencies [[javax.servlet/servlet-api "2.5"]
                        [hiccup "1.0.2"]
                        [slugger "1.0.1"]
                        [markdown-clj "0.9.85"]
                        [ring/ring-mock "0.3.0"]]}})
