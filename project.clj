(defproject civi "0.0.1-SNAPSHOT"
    :description "CV App"
    :dependencies [[org.clojure/clojure "1.5.0"]
                   [org.clojure/data.zip "0.1.1"]
[ring/ring-core "1.2.0-beta1"]
                   [compojure "1.2.0-SNAPSHOT"]
                   [clj-stacktrace "0.2.5"]
                   [enlive "1.1.1"]
                   [com.cemerick/friend "0.1.5"]
 ]
    :dev-dependencies [[ring/ring-devel "1.1.0-SNAPSHOT"]
                       [lein-ring "0.5.4"]
                       [ring-serve "0.1.1"]
                       ]
    :plugins [
              [lein-swank "1.4.4"]
              [lein-ring "0.8.3"]]
    :source-paths ["src/clj"] 
    :resource-paths ["resources/public"]
    :main civi.core
    :ring {:handler civi.core/secured-app}
 )