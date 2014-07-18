(ns moniclj.test.core-test
  (:use clojure.test)
  (:require [moniclj.core :as core]
            [midje.sweet :refer :all]
            [conjure.core :refer :all]))

(fact "execute shell command"
      (core/execute-check "echo hello world") => {:exit 0 :out "hello world\n" :err ""})

(fact "handle check"
      (core/handle-check {:cmd "echo hello world" :last-update {}}) => {:cmd "echo hello world"
                                                                        :fail-count 0
                                                                        :last-update {:state 0
                                                                                      :msg "hello world\n"}})

(fact "nil when init-or-inc"
      (core/init-or-inc nil) => 1)

(fact "0 when init-or-inc"
      (core/init-or-inc 0) => 1)

(fact "1 when init-or-inc"
      (core/init-or-inc 1) => 2)
