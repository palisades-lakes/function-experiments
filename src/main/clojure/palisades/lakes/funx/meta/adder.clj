(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.meta.adder
  
  {:doc "to examine compiler output."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-09-14"
   :version "2017-09-19"})
;;----------------------------------------------------------------
#_(defn make-adder ^clojure.lang.IFn$DD [^double delta]
   (let [f (fn adder ^double [^double x] (+ x delta))]
     (println (class f) (str f) (instance? clojure.lang.IFn$DD f))
   (with-meta
     f
     (merge (meta f) {:delta delta}))))
#_(def add1 (make-adder 1))
#_(println (class add1) (str add1) (instance? clojure.lang.IFn$DD add1))
#_(println (meta add1))
;;----------------------------------------------------------------
(defn a1 ^double [^double x] (+ 1.0 x))
(def m1 (with-meta a1 {:domain Double/TYPE :codomain Double/TYPE}))

(println (class a1) a1)
(println (class m1) m1)
(println (= a1 m1))
(println (instance? clojure.lang.RestFn a1))
(println (instance? clojure.lang.RestFn m1))