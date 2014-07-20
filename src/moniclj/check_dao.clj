(ns moniclj.check-dao
  (:require [monger.core :as core]
            [monger.collection :as collection]))

(defn get-checks []
  (let [conn (core/connect)
        db (core/get-db conn "moniclj")]
    (collection/find-maps db "check")))

(defn save-check [check]
  (let [conn (core/connect)
        db (core/get-db conn "moniclj")]
    (collection/save-and-return db "check" check)))
 
