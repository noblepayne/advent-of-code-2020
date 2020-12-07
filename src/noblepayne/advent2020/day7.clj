(ns noblepayne.advent2020.day7
 (:require [clojure.string :as str]
           [noblepayne.advent2020.utils :as utils]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(def input-bag-regex #"(.+) bags contain (.+|no other bags.)")
(def output-bag-regex #"(\d+) (.+) bags?.?")

(defn process-output-inner [output-entry]
  (let [[_ n bag] (re-matches output-bag-regex output-entry)]
    {:count (Integer/parseInt n) :bag bag}))

(defn process-output [output]
  (map process-output-inner (str/split output #", ")))

(defn process-line [line]
  (let [[_ input-color output] (re-matches input-bag-regex line)
        output-colors (if (= "no other bags." output)
                        '()
                        (process-output output))]
    [input-color output-colors]))

(defn load-data [filename]
  (->> filename
       utils/raw-data
       str/split-lines
       (map process-line)
       (into {})))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(defn contained-bags
  ([data bag] (contained-bags data [bag] #{}))
  ([data bags output]
   (if-let [current-bag (peek bags)]
      (let [remaining-bags (pop bags)
            inner-bags (map :bag (get data current-bag))
            new-output (into output inner-bags)
            new-bags (into remaining-bags inner-bags)]
        (recur data new-bags new-output))
      output)))

(defn count-can-contain [data bag]
  (->> data
       keys
       (map #(contained-bags data %))
       (filter #(contains? % bag))
       count))

(defn solve-1 [data]
  (count-can-contain data "shiny gold"))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn expand-bag [{:keys [:count :bag]}]
  (take count (repeat bag)))

(defn count-sub-bags
  ([data bag] (count-sub-bags data [bag] -1))
  ([data bags cnt]
   (if-let [current-bag (peek bags)]
     (let [remaining-bags (pop bags)
           inner-bags (get data current-bag)
           new-cnt (inc cnt)
           new-bags (into remaining-bags (mapcat expand-bag inner-bags))]
       (recur data new-bags new-cnt))
     cnt)))
  
(defn solve-2 [data]
  (count-sub-bags data "shiny gold"))
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata1 (load-data "day7p1t1.txt"))
  (def testdata2 (load-data "day7p2t1.txt"))
  (def realdata (load-data "day7p1.txt")))
