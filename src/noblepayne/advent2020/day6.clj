(ns noblepayne.advent2020.day6
 (:require [clojure.string :as str]
           [clojure.set :as set]
           [noblepayne.advent2020.utils :as utils])) 

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn load-data [filename]
  (->> (utils/raw-data filename)
       str/split-lines
       (utils/partition-while #{""})))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(defn count-answers [group]
  (count (reduce into #{} group)))

(defn solve-1 [data]
  (reduce + (map count-answers data)))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn count-answers-for-real [group]
  (count (reduce set/intersection (map set group))))

(defn solve-2 [data]
  (reduce + (map count-answers-for-real data)))
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata (load-data "day6p1t1.txt"))
  (def realdata (load-data "day6p1.txt")))
