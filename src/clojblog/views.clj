(ns clojblog.views
  (:require [clojblog.model :as model]
            [clojblog.config :as config]
            [selmer.parser :as slm]
            [clojblog.config :as config]))


(defn index []
  (slm/render-file "templates/index.html"
                   {:posts (model/read-all-posts (config/by-key :content-dir))}))

(defn show-post [slug]
  (let [post (model/post-by-slug slug)]
    (slm/render-file "templates/post.html"
                     {:post post})))