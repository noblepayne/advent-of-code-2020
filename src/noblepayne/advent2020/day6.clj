(ns noblepayne.advent2020.day6
 (:require [clojure.java.io :as io]
           [clojure.string :as str]
           [clojure.set :as set]
           [noblepayne.advent2020.utils :refer [partition-while]]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn raw-data [filename]
  (slurp (io/resource filename)))

(defn load-data [filename]
  (->> (raw-data filename)
       str/split-lines
       (partition-while #{""})))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(defn count-answers [group]
  (count
    (reduce into #{} group)))

(defn solve-1 [data]
  (reduce + (map count-answers data)))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn count-answers-for-real [group]
  (count
    (reduce
      set/intersection
      (map set group))))

(defn solve-2 [data]
  (reduce + (map count-answers-for-real data)))
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata (load-data "day6p1t1.txt"))
  (def realdata (load-data "day6p1.txt")))
