(ns clojblog.store
  (:require [clojure.java.io :as io]
            [clojblog.dropbox :as dbx]))

(defprotocol ContentStore
  "Protocol for listing and reading content from a content store."
  (list-content [this path] "List content in path")
  (read-content [this path] "Read content from a path returned by (list-content)"))

(deftype FileSystemStore []
  ContentStore

  (list-content [this path]
    (map #(.getAbsolutePath %) (.listFiles (io/file path))))

  (read-content [this path]
    (slurp path)))

(deftype DropboxStore []
  ContentStore

  (list-content [this path]
    (dbx/list-files path))

  (read-content [this path]
    (dbx/read-file path)))



; List files in current dir
; (.list-content (FileSystemStore.) ".")