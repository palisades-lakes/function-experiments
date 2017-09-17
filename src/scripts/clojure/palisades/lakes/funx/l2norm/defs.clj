(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.l2norm.defs
  
  {:doc "Benchmarks for l2norm alternatives."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-09-16"
   :version "2017-09-16"}
  
  (:require [palisades.lakes.bench.prng :as prng]
            [palisades.lakes.funx.spaces.linear.l2 :as l2])
  
  (:import [clojure.lang IFn$D]
           [palisades.lakes.bench.java.spaces.linear L2Norm]))
;;----------------------------------------------------------------
(let [urp (prng/uniform-random-provider "seeds/Well44497b-2017-04-05.edn")
      umin -100.0
      umax 100.0]
  (def ^IFn$D udouble (prng/uniform-double umin umax urp)))
;;----------------------------------------------------------------
(defn naive ^double [^"[D" x] (L2Norm/naive x))
(defn invokestatic ^double [^"[D" x] (L2Norm/invokestatic x))
(defn primitive ^double [^"[D" x] (L2Norm/primitive l2/square x))
(defn boxprimitive ^double [^"[D" x] (L2Norm/boxing l2/square x))
(defn cljmeta ^double [^"[D" x] (L2Norm/boxing l2/clj-meta-square x))
(defn funxmeta ^double [^"[D" x] (L2Norm/boxing l2/funx-meta-square x))
(defn boxed ^double [^"[D" x] (L2Norm/boxing l2/boxed-square x))
;----------------------------------------------------------------
