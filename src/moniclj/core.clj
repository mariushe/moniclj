(ns moniclj.core
  (use [clojure.java.shell :only [sh]]
       [clojure.string :only [split]])
  (:require [moniclj.check-dao :as dao]))

(def execute-check (fn [cmd] (sh "sh" "-c" cmd)))

(def give-state-real-name (fn [code]  (let [code-key (keyword (str code))]
                                         (-> {:0 "OK" 
                                              :1 "WARNING"
                                              :2 "CRITICAL"} code-key))))

(def run-check (fn [check] (let [result (execute-check (-> check :cmd))]
                             (assoc check :current {:state (give-state-real-name (-> result :exit))
                                                        :msg (-> result :out)}))))

(def failed? (fn [check] (= (-> check :current :state) "CRITICAL")))

(def init-or-inc (fnil (fn [value] (inc value)) 0))

(def inc-if-fail (fn [check]  (if (failed? check) 
                                (update-in check [:fail-count] init-or-inc)
                                (assoc check :fail-count 0))))

(def handle-check (fn [check] (-> check 
                                  (run-check)
                                  (inc-if-fail))))

(def start-check-process (fn [check] (-> check
                                         (handle-check)
                                         (dao/save-check))))

(def executor-loop (fn [] (while true 
                            (let []
                              (prn (map start-check-process (dao/get-checks)))
                              (Thread/sleep 30000)))))

(def loop-agent (agent 0))

(def startup (fn [] (.start (Thread. executor-loop))))
