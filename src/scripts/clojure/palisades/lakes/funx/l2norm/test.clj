(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.l2norm.test
  "Use criterium for alternative function implementations."
  {:author "palisades dot lakes at gmail dot com"
   :since "2017-09-16"
   :version "2017-09-16"}
  (:require [palisades.lakes.bench.prng :as prng]
            [palisades.lakes.bench.core :as bench]
            [palisades.lakes.funx.l2norm.defs :as defs]))
;;----------------------------------------------------------------
(def options {:n 1024 :samples 4})
(bench/bench 
  [prng/doubles defs/udouble]
  [defs/naive
   defs/invokestatic
   defs/primitive
   defs/boxprimitive
   defs/cljmeta
   defs/funxmeta
   defs/boxed]
  options)
;;----------------------------------------------------------------
(shutdown-agents)
(System/exit 0)
