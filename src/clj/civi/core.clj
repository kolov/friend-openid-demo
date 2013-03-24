(ns 
  civi.core
  (:require 
            [cemerick.friend :as friend]
            [cemerick.friend.openid :as openid]
            [compojure.core :refer (GET defroutes)]
            (compojure [handler :as handler])
            [ring.util.response :as resp]
            [hiccup.page :as h] 
            [net.cgrand.enlive-html :refer (content html deftemplate)]
            [compojure.route :as r]))

(deftemplate home-page "index.html" [req]  
  [:title] (content "Welcome")   
  [:div#login-area] (if-let [auth (friend/current-authentication req)] 
    (content (html (str "Welcome, " (:firstname auth)) [:button  {  :onClick "civi.main.doLogout();"} "Logout"]))
    (content (html [:a#login-google {:class "btn-auth btn-google" :onClick "civi.main.doLoginGoogle();" } "Sign in with " [:b "Google"]]))))

(defn index [req]
  (->> (home-page req) resp/response)) 


(defroutes routes
   (GET "/" req (index req) )
   (GET "/logout" req (friend/logout* (resp/redirect (str (:context req) "/"))))
   (r/resources "/" {:root ""}) 
   (r/not-found (resp/file-response "not-found.html" {:root "resources/private"}))
)

(def secured-app (handler/site
            (friend/authenticate
              routes
              {:allow-anon? true
               :default-landing-uri  "/"
               :workflows [(openid/workflow
                             :openid-uri "/login"
                             :credential-fn identity)]})))