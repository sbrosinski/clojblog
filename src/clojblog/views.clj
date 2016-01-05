(ns clojblog.views
  (:require [clojblog.model :as model]
            [hiccup.page :as page]
            [clojblog.config :as config]))

(defn layout [content]
  "Base layout for site."
  (page/html5
    [:head
     [:title "Hello World"]]
    [:body
     [:div {:id "content"} content]]))


(defn index []
  (let [posts (model/read-all-posts "resources/content")]
    (layout [:ul
             (for [p posts]
               [:li [:a {:href (str "/post/" (p :slug))} (p :title)]])])))

(defn show-post [slug]
  (let [post (model/post-by-slug slug)]
    (layout (post :content)))
  )