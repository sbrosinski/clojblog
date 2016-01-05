(ns clojblog.model-test
  (:require [clojure.test :refer :all]
            [clojblog.model :refer :all]))


(deftest list-content-files-should-find-files

  (is (= 2 (count
             (list-content-files "resources/content"))))

  )