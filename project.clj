(defproject civi "0.0.1-SNAPSHOT"
    :description "Friend OpenID Demo"
    :dependencies [[org.clojure/clojure "1.5.0"]
                   [org.clojure/data.zip "0.1.1"]
                   [ring/ring-core "1.2.0-beta1"]
                   [ring/ring-jetty-adapter "1.1.0"]
                   [compojure "1.2.0-SNAPSHOT"]
                   [enlive "1.1.1"]
                   [com.cemerick/friend "0.1.5"]]
    :dev-dependencies [
                       [lein-ring "0.5.4"]
                       ]
    :plugins [[lein-ring "0.8.3"]]
    :source-paths ["src/clj"] 
    :resource-paths ["resources/public"]
    :main civi.core
    :ring {:handler civi.core/secured-app}
 )