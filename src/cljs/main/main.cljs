(ns civi.main
  (:require 
            [goog.dom :as dom]
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
(defn submit-login [p] (do (set! (.-value (dom/getElement "login-identifier")) (p providers)) (.submit (dom/getElement "login-form"))))

(defn ^:export doLoginGoogle [] (submit-login :google))
(defn ^:export doLogout [] (.submit (dom/getElement "logout-form")))

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
