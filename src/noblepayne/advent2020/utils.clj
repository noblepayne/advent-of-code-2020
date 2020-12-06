(ns noblepayne.advent2020.utils
  (:require [clojure.java.io :as io]))

(defn raw-data [filename]
  (slurp (io/resource filename)))

(defn partition-while
  "Applies f to each value in coll, splitting it when f is 
   true. Splitting continues until f is false. Returns a
   lazy seq of partitions."
  [f coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (let [run (take-while (complement f) s)
            rst (drop (count run) s)]
        (cons run (partition-while f (drop-while f rst)))))))
