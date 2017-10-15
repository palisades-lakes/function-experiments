(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.funx.asm.bytes
  
  {:doc "get class bytes without class file."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-10-14"
   :version "2017-10-14"}
  
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [no.disassemble :as nod]
            [palisades.lakes.funx.meta.adder :as adder]))
;;----------------------------------------------------------------
(defn read-bytes ^bytes [x]
  (with-open [out (java.io.ByteArrayOutputStream.)]
    (io/copy (io/input-stream x) out)
    (.toByteArray out)))

(defn get-class-bytes [^Object object]
  (let [^Class klass (.getClass object)
        ^ClassLoader loader (.getClassLoader klass)
        ^String rpath (str
                        (s/replace 
                          (.getName klass) 
                          "." "/")
                        ".class")
        ^java.io.InputStream stream (.getResourceAsStream loader rpath)
        ^bytes b (when stream (read-bytes stream))] 
    (println klass)
    (println loader)
    (println rpath)
    (println stream)
    (println b (when b (alength b)))
    (println)
    b))
;;----------------------------------------------------------------
(get-class-bytes (adder/make-adder 1))
;;----------------------------------------------------------------
(defn make-adder ^clojure.lang.IFn$DD [^double delta]
  (fn adder ^double [^double x] (+ x delta)))
;;----------------------------------------------------------------
(get-class-bytes (make-adder 1))
;;----------------------------------------------------------------
(println (nod/disassemble (make-adder 1)))
;;----------------------------------------------------------------
;public static void attachGivenAgentToThisVM(String pathToAgentJar) {
;  try {                                                                               
;    String nameOfRunningVM = ManagementFactory.getRuntimeMXBean().getName();                                                   
;    String pid = nameOfRunningVM.substring(0, nameOfRunningVM.indexOf('@'));                                                   
;    VirtualMachine vm = VirtualMachine.attach(pid);                                                                            
;    vm.loadAgent(pathToAgentJar, "");
;    vm.detach();   
;  } catch (Exception e) {
;    e.printStackTrace();
;  }
;}