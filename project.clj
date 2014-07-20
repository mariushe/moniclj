(defproject moniclj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:init moniclj.core/startup
         :handler moniclj.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [liberator "0.10.0"]
                        [org.clojure/data.json "0.2.5"]
                        [org.clojars.runa/conjure "2.1.3"]
                        [ring/ring-json "0.3.1"]
                        [midje "1.6.3"]
                        [com.novemberain/monger "2.0.0"]]}})
