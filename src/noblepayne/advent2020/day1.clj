(ns noblepayne.advent2020.day1
 (:require [clojure.java.io :as io]
           [clojure.string :as str]
           [clojure.math.combinatorics :as combo]))

(defn clean-data [raw-data]
  (->> (str/split-lines raw-data)
       (map #(Integer/parseInt %))))

(defn load-data [filename]
  (->> filename
       io/resource
       slurp
       clean-data))

(defn solve-base [target-sum sum-number data]
  (->> (combo/combinations data sum-number)
       (filter #(= target-sum (apply + %)))
       (map #(apply * %))
       first))

(def solve1 (partial solve-base 2020 2))
(def solve2 (partial solve-base 2020 3))

  
(comment
  (def p1-data (load-data "day1p1.txt"))
  (def p1-testdata (load-data "day1p1t1.txt")))
