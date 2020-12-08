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

(defn gather
  "Depth-first map traversal with customizable output gathering.

   `key-fn`: receives the value in map_ for the current key
             and should return a list of new keys.
   `output-fn`: receives the current accumlated output and
   the output of `key-fn`, and should produce the new
   output.
   `output`: the initial output collection/value.
   `map_`: map to traverse.
   `keys`: list of starting keys."
  ([key-fn output-fn output map_ keys_]
   (if-let [key_ (peek keys_)]
     (let [remaining (pop keys_)
           new-keys (key-fn (get map_ key_))
           output (output-fn output new-keys)
           keys_ (into remaining new-keys)]
       (recur key-fn output-fn output map_ keys_))
     output)))
