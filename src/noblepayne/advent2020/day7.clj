(ns noblepayne.advent2020.day7
 (:require [clojure.string :as str]
           [noblepayne.advent2020.utils :as utils]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(def input-bag-regex #"(.+) bags contain (.+|no other bags\.)")
(def output-bag-regex #"(\d+) (.+) bags?\.?")

(defn process-output-bag [output-bag]
  (let [[_ cnt bag] (re-matches output-bag-regex output-bag)]
    {:count (Integer/parseInt cnt) :bag bag}))

(defn process-output-bags [output-bags]
  (->> (str/split output-bags #", ")
       (remove #{"no other bags."})
       (map process-output-bag)))

(defn process-line [line]
  (let [[_ input-bag output-bags] (re-matches input-bag-regex line)]
    [input-bag (process-output-bags output-bags)]))

(defn load-data [filename]
  (->> filename
       utils/raw-data
       str/split-lines
       (map process-line)
       (into {})))

;;;;;;;; PART 1;;;;;;;;;;;;;;;;;;;;
(defn contained-bags [data bag]
  (utils/gather
    #(map :bag %)
    into
    #{}
    data
    [bag]))

(defn count-can-contain [data bag]
  (->> (keys data)
       (map #(contained-bags data %))
       (filter #(contains? % bag))
       count))

(defn solve-1 [data]
  (count-can-contain data "shiny gold"))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn expand-bag [{:keys [:count :bag]}]
  (take count (repeat bag)))

(defn count-sub-bags [data bag]
  (utils/gather
    #(mapcat expand-bag %)
    (fn [n _] (inc n))
    -1
    data
    [bag]))
  
(defn solve-2 [data]
  (count-sub-bags data "shiny gold"))
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata1 (load-data "day7p1t1.txt"))
  (def testdata2 (load-data "day7p2t1.txt"))
  (def realdata (load-data "day7p1.txt")))
