(ns clojblog.dropbox
  (:require [clojure.tools.logging :as log]))


(defn client []
  "Creates a dropbox API v2 client with an access token."
  (log/info "Created dropbox client.")
  (com.dropbox.core.v2.DbxClientV2.
    (com.dropbox.core.DbxRequestConfig. "clojblog" "en_US")
      (System/getenv "CLOJBLOG_DROPBOX_TOKEN")))


(defn downloadBuilder [path]
  "Creates a dropbox download builder for a path."
  (.. (client) files (downloadBuilder path)))


(defn list-files [dir]
  "Gets list of files with their paths from flat dropbox folder."
  (log/info "Listing " dir " ...")
  (let [c (client)]
    (map #(.pathLower %) (.. c files (listFolder "") entries))))


(defn read-file [path]
  "Reads file from dropbox path."
  (log/info "Reading " path " from dropbox ...")
  (let [db (downloadBuilder path)]
    (slurp (.. db start body))))


