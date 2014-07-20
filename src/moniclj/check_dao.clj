(ns moniclj.check-dao
  (:require [monger.core :as core]
            [monger.collection :as collection]))

(def conne (atom (core/connect)))

(defn get-checks []
  (let [db (core/get-db @conne "moniclj")]
    (collection/find-maps db "check")))

(defn save-check [check]
  (let [db (core/get-db @conne "moniclj")]
    (collection/save-and-return db "check" check)))
 
