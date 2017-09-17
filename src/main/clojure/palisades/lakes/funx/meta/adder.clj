(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.meta.adder
  
  {:doc "to examine compiler output."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-09-14"
   :version "2017-09-14"})
;;----------------------------------------------------------------
(defn make-adder ^clojure.lang.IFn$DD [^double delta]
  (let [f (fn adder ^double [^double x] (+ x delta))]
    (println (class f) (str f) (instance? clojure.lang.IFn$DD f))
  (with-meta
    f
    (merge (meta f) {:delta delta}))))

(def add1 (make-adder 1))
(println (class add1) (str add1) (instance? clojure.lang.IFn$DD add1))
(println (meta add1))
  