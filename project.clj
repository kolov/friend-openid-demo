(defproject civi "0.0.1-SNAPSHOT"
    :description "CV App"
    :dependencies [[org.clojure/clojure "1.5.1"]
                   [org.clojure/data.zip "0.1.1"]
                   [compojure "1.1.3"]
                   [clj-stacktrace "0.2.4"]
                   [ring/ring-core "1.1.0"]
                   [clj-http "0.3.0"]
                   [jayq "0.1.0-alpha4"]
                   [domina "1.0.2-SNAPSHOT"]
                   [enlive "1.1.2"]
                   [org.clojure/google-closure-library "0.0-1376-2"]
                   [org.clojure/google-closure-library-third-party "0.0-1376-2"]
                   [ring/ring-jetty-adapter "1.1.0-SNAPSHOT"]
                   [com.novemberain/monger "1.4.2"]]
    :dev-dependencies [[ring/ring-devel "1.1.0-SNAPSHOT"]
                       [lein-ring "0.5.4"]
                       [ring-serve "0.1.1"]
                       ]
    :plugins [[lein-cljsbuild "0.3.0"]
              [lein-swank "1.4.4"]
              [lein-ring "0.8.3"]]
    :java-source-paths ["src/java"]
    :javac-options     ["-target" "1.6" "-source" "1.6"]
    :source-paths ["src/clj"]
    ; :web-content "resources/public"
    :resource-paths ["resources/public"]
    :main civi.core
    :ring {:handler civi.core/app}
    )