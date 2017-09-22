(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.spaces.linear.l2
  
  {:doc "benchmark primitive vs boxing vs adding meta data..."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-09-16"
   :version "2017-09-20"}
  
  (:require [palisades.lakes.dynafun.core :as d])
  (:import [clojure.lang IFn$DD]))
;;----------------------------------------------------------------
(defn square ^double [^double x] (* x x))
(def clj-meta-square 
  (with-meta square 
    {:domain Double/TYPE
     :codomain Double/TYPE
     :support Double/TYPE
     :range [0.0 Double/POSITIVE_INFINITY Double/NaN]}))
(def funx-meta-square 
  (d/with square 
    {:domain Double/TYPE
     :codomain Double/TYPE
     :support Double/TYPE
     :range [0.0 Double/POSITIVE_INFINITY Double/NaN]}))
(set! *unchecked-math* false)
(defn boxed-square [x] (* x x))
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(defn squared ^IFn$DD [^IFn$DD f]
  (with-meta 
    (fn squared0 ^double [^double x]
      (let [fx (.invokePrim f x)]
        (* fx fx)))
    {:domain Double/TYPE
     :range [0.0 Double/POSITIVE_INFINITY Double/NaN]}))
;;----------------------------------------------------------------
