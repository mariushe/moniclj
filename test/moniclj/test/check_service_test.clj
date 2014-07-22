(ns moniclj.test.check-service-test
  (:use clojure.test)
  (:require [moniclj.check-service :as check-service]
            [moniclj.check-dao :as dao]
            [midje.sweet :refer :all]
            [conjure.core :refer :all]))


