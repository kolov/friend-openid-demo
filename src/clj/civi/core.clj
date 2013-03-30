(ns civi.core
  (:require  
            [cemerick.friend :as friend]
            [cemerick.friend.openid :as openid]
            [compojure.core :refer (GET defroutes)]
            (compojure [handler :as handler])
            [ring.util.response :as resp]
            [hiccup.page :as h] 
            [net.cgrand.enlive-html :refer (content html deftemplate)]
            [compojure.route :as r]
            ring.adapter.jetty))

(deftemplate home-page "index.html" [req]
  [:title] (content "Welcome")
  [:div#login-area] (if-let [username (:firstname (friend/current-authentication req))]
    (content (html (str "Welcome, " username) [:br] [:a  {  :href "/logout"} "Logout"]))
    (content (html "Welcome, anonymous" [:br] [:a#login-google { :href "javascript:document.forms[0].submit();" } "Sign in with " [:b "Google"]]))))

(defn index [req]
  (->> (home-page req) resp/response)) 

(defn log-request[handler] (fn[request] (do (print (str "Identity: " (-> request :session :cemerick.friend/identity )  "\n")) (flush) (handler request))))

(defn file[f] (resp/file-response f {:root "resources/private"}))
(defroutes routes
   (GET "/" req (assoc  (index req)  :headers {"Content-Type" "text/html"}))
   (GET "/protected" req (friend/authorize #{::admin} (file "protected.html")  ))
   (GET "/logout" req (friend/logout* (resp/redirect (str (:context req) "/"))))
   (r/resources "/" {:root ""}) 
   (r/not-found (resp/file-response "not-found.html" {:root "resources/private"}))
)

(def secured-app (handler/site
           (log-request
            (friend/authenticate
              routes
              {:allow-anon? true
               :default-landing-uri  "/"
               :unauthorized-handler  (fn[_] (file "not-authorized.html"))
               :unauthenticated-handler  (fn[_] (file "not-authorized.html"))
               :workflows [(openid/workflow
                             :login-failure-handler  (fn[_] (file "login-failed.html"))
                             :openid-uri "/login"
                             :credential-fn #(-> % :email nil? not (if (merge % {:roles #{::admin}}))))]}))))

(defn -main
  "For heroku."
  [port]
  (ring.adapter.jetty/run-jetty secured-app {:port (Integer. port)}))