(ns moniclj.core
  (use [clojure.java.shell :only [sh]]
       [clojure.string :only [split]])
  (:require [moniclj.check-dao :as dao]))

(def execute-check (fn [cmd] (sh "sh" "-c" cmd)))


(def run-check (fn [check] (let [result (execute-check (-> check :cmd))]
                             (assoc check :last-update {:state (-> result :exit)
                                                        :msg (-> result :out)}))))

(def failed? (fn [check] (= (-> check :last-update :state) 2)))

(def init-or-inc (fn [value] (if-not (nil? value)
                               (inc value)
                               1)))

(def inc-if-fail (fn [check]  (if (failed? check) 
                                (update-in check [:fail-count] init-or-inc)
                                (assoc check :fail-count 0))))

(def handle-check (fn [check] (-> check 
                                  (run-check)
                                  (inc-if-fail)
                                  (dao/save-check))))

(def something (fn [value] (inc value)))

(def executor-loop (fn [] (while true 
                            (let []
                              (prn (map handle-check (dao/get-checks)))
                              (Thread/sleep 2000)))))

(def startup (fn [] (executor-loop)))
