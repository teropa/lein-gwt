(ns leiningen.gwt
  (:require [clojure.contrib.duck-streams :as streams])
  (:require [clojure.contrib.string :as string])
  (:require [leiningen.classpath :as cp]))

(def default-options
  {:style "OBF"
   :localWorkers 2})

(defn- to-str [v]
  (if (keyword? v)
    (.substring (str v) 1)
    (str v)))

(defn- to-opt-str [k]
  (let [s (to-str k)]
    (if (.startsWith s "-")
      s
      (str "-" (to-str s)))))

(defn- opts [project]
  (reduce
    (fn [res [k v]]
      (let [r (conj res (to-opt-str k))]
        (if (string/blank? (to-str v))
          r
          (conj r (to-str v)))))
    []
    (merge default-options (:gwt-options project))))

(defn- classpath [project]
  (string/join
    java.io.File/pathSeparatorChar
    (cp/get-classpath project)))

(defn- process-args [project]
  (java.util.ArrayList.
    (concat
      ["java"
       "-cp"
       (classpath project)
       "com.google.gwt.dev.Compiler"]
      (opts project)
      (:gwt-modules project))))
  
(defn gwt [project & args]
  (let [p (-> (ProcessBuilder. (process-args project))
              (.start))]
    (doseq [line (streams/read-lines (.getInputStream p))]
      (println line))))
