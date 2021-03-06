(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.l2norm.bench
  "Use criterium for alternative function implementations."
  {:author "palisades dot lakes at gmail dot com"
   :since "2017-09-16"
   :version "2017-09-18"}
  (:require [palisades.lakes.bench.prng :as prng]
            [palisades.lakes.bench.core :as bench]
            [palisades.lakes.funx.l2norm.defs :as defs]))
;;----------------------------------------------------------------
(def options {:n (* 4 1024 1024) :samples 200})
(bench/bench 
  [prng/doubles defs/udouble]
  [defs/inline
   defs/invokestatic
   defs/primitive
   defs/funxprimitive
   defs/boxprimitive
   defs/boxed
   defs/funxboxed
   defs/cljmeta]
  options)
;;----------------------------------------------------------------
#_(shutdown-agents)
#_(System/exit 0)
