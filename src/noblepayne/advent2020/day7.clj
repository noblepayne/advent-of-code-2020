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

;;;;;;;; COMMON ;;;;;;;;;;;;;;;;;;;;
(defn gather
  "Depth-first traversal with customizable output gathering.

   `inner-bag-fn`: receives the list of inner bag maps for a
   bag, and should return a list of bag names.
   `output-bag-fn`: receives the current accumlated output and
   the output of `inner-bag-fn`, and should produce the new
   output.
   `output`: the initial output collection/value.
   `data`: map containing information keyed on bag name.
   `bags`: list of starting bags." 
  [inner-bag-fn output-fn output data bags]
  (if-let [current-bag (peek bags)]
    (let [remaining-bags (pop bags)
          inner-bags (inner-bag-fn (get data current-bag))
          new-output (output-fn output inner-bags)
          new-bags (into remaining-bags inner-bags)]
      (recur inner-bag-fn output-fn new-output data new-bags))
    output))

;;;;;;;; PART 1;;;;;;;;;;;;;;;;;;;;
(defn contained-bags [data bag]
  (gather #(map :bag %) into #{} data [bag]))

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
  (gather #(mapcat expand-bag %) (fn [n _] (inc n)) -1 data [bag]))
  
(defn solve-2 [data]
  (count-sub-bags data "shiny gold"))
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata1 (load-data "day7p1t1.txt"))
  (def testdata2 (load-data "day7p2t1.txt"))
  (def realdata (load-data "day7p1.txt")))
