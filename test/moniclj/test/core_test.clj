(ns moniclj.test.core-test
  (:use clojure.test)
  (:require [moniclj.core :as core]
            [moniclj.check-dao :as dao]
            [midje.sweet :refer :all]
            [conjure.core :refer :all]))

(fact "execute shell command"
      (core/execute-check "echo hello world") => {:exit 0 :out "hello world\n" :err ""})

(fact "handle check"
      (core/handle-check {:cmd "echo hello world" :last-update {}}) => {:cmd "echo hello world"
                                                                        :fail-count 0
                                                                        :last-update {:state "OK"
                                                                                      :msg "hello world\n"}})

(fact "nil when init-or-inc"
      (core/init-or-inc nil) => 1)

(fact "0 when init-or-inc"
      (core/init-or-inc 0) => 1)

(fact "1 when init-or-inc"
      (core/init-or-inc 1) => 2)

(fact "failed? when not failed"
      (core/failed? {:last-update {:state "OK"}}) => false)

(fact "failed? when failed"
      (core/failed? {:last-update {:state "CRITICAL"}}) => true)


(fact "give status real name OK"
      (core/give-state-real-name 0) => "OK")

