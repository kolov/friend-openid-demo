(ns civi.xml
  (:use [clojure.data.zip.xml])
  (:require [clojure.xml :as xml]
            [clojure.zip :as zip]))

; Some constants
(def SIMPLE_TYPE com.sun.org.apache.xerces.internal.xs.XSTypeDefinition/SIMPLE_TYPE)
(def COMPLEX_TYPE com.sun.org.apache.xerces.internal.xs.XSTypeDefinition/COMPLEX_TYPE)
(def ELEMENT_DECLARATION 
  com.sun.org.apache.xerces.internal.xs.XSConstants/ELEMENT_DECLARATION)

(defn resource-location[f] 
 "gets the locaton of a resource on the classpath"
 (-> (clojure.java.io/resource f) .getFile ))

(defn read-file[f] (with-open [r (-> (clojure.java.io/resource f) .openStream)] 
   (xml/parse r)))

(defn get-schema[r]
 "Reads schema from XSD esource on the classpath"
 (System/setProperty org.w3c.dom.bootstrap.DOMImplementationRegistry/PROPERTY 
     "com.sun.org.apache.xerces.internal.dom.DOMXSImplementationSourceImpl")
 (let [registry (org.w3c.dom.bootstrap.DOMImplementationRegistry/newInstance)
       impl (.getDOMImplementation registry "XS-Loader")
       schemaLoader (.createXSLoader impl nil)]
(.loadURI schemaLoader (resource-location r))))

(def sc (get-schema "s.xsd"))

(defn components
  ([sc n] (let[c (.getComponents sc n)] (for[i (range (.getLength c))] (.item c i))))
  ([sc] (components sc ELEMENT_DECLARATION)))

(defn types[sc]
  (map #(assoc {} :name (.getName %) 
               :namespace (.getNamespace %) 
               :type (.getType %)
               :typeName (.getTypeName %)
               :typeNamespace (.getTypeNamespace %)
               )
       (components sc COMPLEX_TYPE)))




