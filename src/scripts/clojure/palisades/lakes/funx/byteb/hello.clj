(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.byteb.hello
  
  {:doc "byte buddy experiments."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-10-15"
   :version "2017-10-15"}
  
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [palisades.lakes.funx.meta.adder :as adder])
  (:import [java.lang.reflect Modifier]
           [net.bytebuddy ByteBuddy]
           [net.bytebuddy.dynamic.loading
            ClassLoadingStrategy$Default]))
;;----------------------------------------------------------------
(defn make-adder ^clojure.lang.IFn$DD [^double delta]
  (fn adder ^double [^double x] (+ x delta)))
;;----------------------------------------------------------------
(let [adder0 (make-adder 1)
      klass0 (class adder0)
      loader0 (.getClassLoader klass0)
      klass1 (.getLoaded
               (.load
                 (.make
                   (.redefine
                     (ByteBuddy.) klass0))
                 loader0 ClassLoadingStrategy$Default/WRAPPER))
      ;;adder1 (.newInstance (.getDeclaredConstructor klass1))
      ]
  (println 0 adder0)
  (println 0 klass0)
  (println 0 (Modifier/toString (.getModifiers klass0)))
  #_(println 1 adder1)
  #_(println 1 klass1)
  #_(println (adder1 1)))
;;----------------------------------------------------------------
