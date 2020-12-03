(ns noblepayne.advent2020.day3
 (:require [clojure.java.io :as io]
           [clojure.string :as str]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn clean-data [raw-data]
  (let [lines  (str/split-lines raw-data)
        height (count lines)
        width  (-> lines first count)
        world  (for [[y line]  (map-indexed vector lines)
                     [x value] (map-indexed vector line)]
                 [[x y] value])]
    {:width  width
     :height height
     :world  (into {} world)}))

(defn load-data [filename]
  (->> filename
       io/resource
       slurp
       clean-data))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(defn lookup [{:keys [:width :world]} [x y]]
  (get world [(mod x width) y]))

(defn apply-slope [slope pos]
  (map + slope pos))

(defn sled [{:keys [:height] :as data} slope]
  (->> (iterate
         #(apply-slope slope %)
         [0 0])
       (take-while
         (fn [[x y]] (< y height)))
       (map
         #(lookup data %))))

(defn solve-base [slope data]
  (->> slope
       (sled data)
       (filter #{\#})
       count))

(def solve-1 (partial solve-base [3 1]))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn solve-2 [data]
  (->> [[1 1]
        [3 1]
        [5 1]
        [7 1]
        [1 2]]
       (map #(solve-base % data))
       (reduce *)))

;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def p1-testdata (load-data "day3p1t1.txt"))
  (def p1-data (load-data "day3p1.txt")))
