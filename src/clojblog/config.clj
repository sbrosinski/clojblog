(ns clojblog.config
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def ^:private config-state (atom #{}))

(defn config []
  @config-state
  )

(defn by-key [key]
  (@config-state key)
  )

(defn from-edn
  [fname]
  (with-open [rdr (-> (io/resource fname)
                      io/reader
                      java.io.PushbackReader.)]
    (edn/read rdr)))

(defn load-config! []
  (reset! config-state (from-edn "config.edn")))