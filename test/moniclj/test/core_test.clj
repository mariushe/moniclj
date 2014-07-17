(ns moniclj.test.core-test
  (:use clojure.test)
  (:require [moniclj.core :as core]
            [midje.sweet :refer :all]
            [conjure.core :refer :all]))

(fact "testing that setup works"
      (core/hello-world) => "Hello world")
