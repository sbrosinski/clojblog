(ns clojblog.views
  (:require [clojblog.model :as model]
            [clojblog.partials :as partials]
            [clojblog.config :as config]
            [hiccup.page :as page]
            [clojblog.config :as config]))

(defn layout [content]
  "Base layout for site."
  (page/html5
    [:head
     (page/include-css "http://yui.yahooapis.com/pure/0.6.0/pure-min.css")
     (page/include-css "http://yui.yahooapis.com/pure/0.6.0/grids-responsive-min.css")
     [:title "Clojblog"]]
    [:body
     [:div {:class "pure-g"}
      [:div {:class "pure-u-1-24 pure-u-md-5-24"}]
      [:div {:class "pure-u-11-12 pure-u-md-5-8"} content]
      [:div {:class "pure-u-1-24 pure-u-md-1-6"}]]]))


(defn index []
  (let [posts (model/read-all-posts (config/by-key :content-dir))]
    (layout [:ul
             (for [p posts]
               [:li (partials/post-in-list p)])])))

(defn show-post [slug]
  (let [post (model/post-by-slug slug)]
    (layout (:content post)))
  )