(ns clojblog.model
  (:require [markdown.core :as md]
            [clojure.string :as str]
            [clojure.edn :as edn]
            [clojblog.config :as config]
            [clojblog.store :as store]
            [slugger.core :as slug]
            [clj-time.format :as tf]))

(defn content-store []
  (let [store (config/by-key :content-store)]
    (cond
      (= :fs store) (store/->FileSystemStore)
      (= :dropbox store) (store/->DropboxStore)
      :else (throw (Exception. (str "Unknown content store: " store))))
    ))

(defn parse-post-data [data]
  "Splits the post file contents into a map of meta data and the post itself."
  (let [[meta content] (str/split data #"\+\+\+")]
    {:meta (edn/read-string meta) :content content}))


(defn read-post [file]
  (let [data (parse-post-data (.read-content (content-store) file))]
    {:meta (:meta data)
     :title (:title (:meta data))
     :date (tf/parse (:date (:meta data)))
     :slug (slug/->slug (:title (:meta data)))
     :file file
     :content (md/md-to-html-string (:content data))}))

(defn list-content-files [dir]
  (.list-content (content-store) dir))

(defn read-all-posts [dir]
  (map read-post
       (list-content-files dir)))

(defn post-by-slug [slug]
  (first
    (filter #(= slug (:slug %))
            (read-all-posts (config/by-key :content-dir))))
  )

