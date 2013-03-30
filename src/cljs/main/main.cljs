(ns civi.main
  (:require 
            [goog.dom :as dom]
            [goog.string :as gs]
            [clojure.browser.repl :as repl]
            [clojure.string :as str]
            [clojure.browser.repl :as repl] 
            [goog.ui.Bubble :as bubble]
            [goog.positioning :as positioning]
)
  (:use  [jayq.util :only [map->js]]
        [domina :only [append! destroy-children!]]
        [domina.xpath :only [xpath]]
        [domina.events :only [listen!]]
        [domina.css :only [sel ]]
        )
) 
 
(defn ^:export initPage []   (
                  do
                  (repl/connect "http://localhost:9000/repl")
                ))
(def providers {:google "https://www.google.com/accounts/o8/id"
                :yahoo "http://me.yahoo.com/"
                :wordpress "http://username.wordpress.com"})

(set! (.-bubbleWidth goog.ui.Bubble/defaultConfig) 350) 
(defn set-action-and-submit[form action]
  (let[base  (.-baseURI form)] (set! (.-action form) (str (if-not (gs/endsWith base "/") "/") action)) (.submit form)))

(defn submit-login [p]  (set! (.-value (dom/getElement "login-identifier")) (p providers)) (set-action-and-submit (dom/getElement "login-form") "login"))
(defn ^:export doLoginGoogle [] (submit-login :google))
(defn ^:export doLogout [] (set-action-and-submit (dom/getElement "logout-form") "logout"))

(defn show-bubble[el content ] 
  (let [b (goog.ui.Bubble. content)] 
    (.setAutoHide b js/true) (.setTimeout b 0)
   (.setPosition b (positioning/AnchoredPosition. el nil))
    (.render b)  (.attach b el) (.setVisible b js/true) )
   (listen! (sel "#login-google") :click (partial submit-login :google))
   (listen! (sel "#login-facebook") :click (partial submit-login :facebook))
)

(def login-buttons "<a id=\"login-google\" class=\"btn-auth btn-google\" href=\"#\">Sign in with <b>Google</b></a>
       <a id=\"login-facebook\" class=\"btn-auth btn-facebook\" href=\"#\">Sign in with <b>Facebook</b></a>")

(listen! (sel "#login") :click (fn [evt] (show-bubble (dom/getElement "login") login-buttons)))
