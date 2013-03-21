(ns civi.core
  (:use compojure.core
        [ring.util.response :only (file-response resource-response status response)]
        ring.util.servlet
        [ring.middleware params session file file-info]
        [net.cgrand.enlive-html]
        )
  (:require [compojure.route :as r]
            [clj-http.client :as c]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            [clojure.string :as s]
            )
)

 
(deftemplate home-page "hello.html" []
  [:title] (content "hi") 
 [:body] (content "hi"))

(defn index
  "Index page handler"
  [req]
  (->> (home-page) response)) ;; A sexier way to write (response (home-page))
 
;; Routes definition
(defroutes app-routes
  (GET "/" [] (index nil) )
  (r/not-found "<h1>Page not found</h1>"))
 
(defservice app-routes)

(def app (handler/site app-routes))
;(defonce server  (ring/run-jetty #'app {:port 8888 :join? false}))
; to start: (use 'ring.util.serve)
; (serve app)

(defn -main [port]
  (ring/run-jetty app {:port (Integer. port)}))