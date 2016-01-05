(ns clojblog.model
  (:require [markdown.core :as md]
            [clojure.string :as str]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [slugger.core :as slug]))

(defn parse-post-data [data]
  "Splits the post file contents into a map of meta data and the post itself."
  (let [[meta content] (str/split data #"\+\+\+")]
    {:meta (edn/read-string meta) :content content}))


(defn read-post [file]
  (let [data (parse-post-data (slurp file))]
    {:meta (data :meta)
     :title ((data :meta) :title)
     :slug (slug/->slug ((data :meta) :title))
     :file file
     :content (md/md-to-html-string (data :content))}))

(defn list-content-files [dir]
  (map #(.getAbsolutePath %) (.listFiles (io/file dir))))

(defn read-all-posts [dir]
  (map read-post
       (list-content-files dir)))


(defn post-by-slug [slug]
  (first
    (filter #(= slug (% :slug))
            (read-all-posts "resources/content")))
  )

