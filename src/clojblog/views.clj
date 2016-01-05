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
     [:title "Clojblog"]]
    [:body
     [:div {:id "content"} content]]))


(defn index []
  (let [posts (model/read-all-posts (config/by-key :content-dir))]
    (layout [:ul
             (for [p posts]
               [:li (partials/post-in-list p)])])))

(defn show-post [slug]
  (let [post (model/post-by-slug slug)]
    (layout (:content post)))
  )