(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.l2norm.funx
  "Use criterium for alternative function implementations."
  {:author "palisades dot lakes at gmail dot com"
   :since "2017-09-16"
   :version "2017-09-18"}
  (:require [palisades.lakes.bench.prng :as prng]
            [palisades.lakes.bench.core :as bench]
            [palisades.lakes.funx.l2norm.defs :as defs]))
;;----------------------------------------------------------------
(def options {:n (* 4 1024 1024) :samples (* 16 1024)})
(bench/profile
  [prng/doubles defs/udouble]
  [defs/funxprimitive]
  options)
;;----------------------------------------------------------------
#_(shutdown-agents)
#_(System/exit 0)
