(ns clojblog.partials
  (:require [clojblog.model :as model]
            [hiccup.page :as page]))


(defn post-in-list [post]
  [:a {:href (str "/post/" (:slug post))} (:title post)])