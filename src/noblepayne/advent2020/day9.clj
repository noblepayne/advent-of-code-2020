(ns noblepayne.advent2020.day9
 (:require [clojure.string :as str]
           [clojure.math.combinatorics :as combo]
           [noblepayne.advent2020.utils :as utils]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn load-data [filename]
  (->> (utils/raw-data filename)
       str/split-lines
       (map #(Long/parseLong %))
       (into [])))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(defn get-data-window [data window idx]
  (subvec data (- idx window) idx))

(defn valid? [data window [idx n]]
  (let [data (get-data-window data window idx)
        sums (->> (combo/combinations data 2)
                  (remove #(apply = %))
                  (map #(apply + %))
                  (into #{}))]
    (contains? sums n)))

(defn solve-1 [data window]
  (->> (map-indexed vector data)
       (drop window)
       (remove #(valid? data window %))
       first
       second))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn generate-ranges [data]
  (mapcat #(partition % 1 data)
          (range 2 (count data))))

(defn find-valid-ranges [data sum]
  (filter #(= sum (apply + %))
          (generate-ranges data)))

(defn solve-2 [data target]
  (if-let [[match] (find-valid-ranges data target)] 
    (+ (apply min match)
       (apply max match))))
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata (load-data "day9p1t1.txt"))
  (def realdata (load-data "day9p1.txt")))
