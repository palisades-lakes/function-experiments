(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.l2norm.bench
  "Use criterium for alternative function implementations."
  {:author "palisades dot lakes at gmail dot com"
   :since "2017-09-16"
   :version "2017-09-16"}
  (:require [palisades.lakes.bench.prng :as prng]
            [palisades.lakes.bench.core :as bench]
            [palisades.lakes.funx.l2norm.defs :as defs]))
;;----------------------------------------------------------------
(def options {} #_{:n 1024 :samples 4})
(bench/bench 
  [prng/doubles defs/udouble]
  [#_defs/naive
   #_defs/invokestatic
   #_defs/primitive
   defs/boxprimitive
   #_defs/cljmeta
   defs/funxmeta
   #_defs/boxed]
  options)
;;----------------------------------------------------------------
#_(shutdown-agents)
#_(System/exit 0)
