(defproject civi "0.0.1-SNAPSHOT"
    :description "CV App"
    :dependencies [[org.clojure/clojure "1.5.0"]
                   [org.clojure/data.zip "0.1.1"]
[ring/ring-core "1.2.0-beta1"]
                   [compojure "1.2.0-SNAPSHOT"]
                   [clj-stacktrace "0.2.5"]
                   [jayq "0.1.0-alpha4"]
                   [domina "1.0.2-SNAPSHOT"]
                   [enlive "1.1.1"]
                   [org.clojure/google-closure-library "0.0-1376-2"]
                   [org.clojure/google-closure-library-third-party "0.0-1376-2"]
                   [com.novemberain/monger "1.4.2"]
                   [com.akolov/xelery "0.1.0"]
                   [com.cemerick/friend "0.1.5"]
 ]
    :dev-dependencies [[ring/ring-devel "1.1.0-SNAPSHOT"]
                       [lein-ring "0.5.4"]
                       [ring-serve "0.1.1"]
                       ]
    :plugins [[lein-cljsbuild "0.3.0"]
              [lein-swank "1.4.4"]
              [lein-ring "0.8.3"]]
 :repositories [["java.net" "http://download.java.net/maven/2"]]
    :java-source-paths ["src/java"]
    :javac-options     ["-target" "1.6" "-source" "1.6"]
    :source-paths ["src/clj"] 
    :resource-paths ["resources/public"]
    :main civi.core
    :ring {:handler civi.core/secured-app}
    :cljsbuild {
    ;  :crossovers [civi.flow]
      :crossover-path "crossover-cljs"
      :builds [{:source-path "src/cljs/main",
                        :compiler {:pretty-print true,
                                   :output-to "resources/public/cljs/main.js",
                                   :optimizations :whitespace}}

                       ]
              :repl-listen-port 9000
              :repl-launch-commands {
                                      "la" ["firefox" "-jsconsole" "http://localhost/my-page"]
                                      }
               }
    )