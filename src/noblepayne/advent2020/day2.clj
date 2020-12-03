(ns noblepayne.advent2020.day2
 (:require [clojure.java.io :as io]
           [clojure.string :as str]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn process-line [line]
  (let [[_ start end letter password]
        (re-matches #"(\d+)-(\d+) (\w): (\w+)" line)]
    {:start    (Integer/parseInt start)
     :end      (Integer/parseInt end)
     :letter   (first letter)
     :password password}))

(defn clean-data [raw-data]
  (->> raw-data
       str/split-lines
       (map process-line)))

(defn load-data [filename]
  (->> filename
       io/resource
       slurp
       clean-data))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(defn valid-1? [{:keys [:start :end :letter :password]}]
  (<=
    start
    (-> password frequencies (get letter 0))
    end))

(defn solve-base [valid-fn data]
  (->> data
       (filter valid-fn)
       count))

(def solve-1 (partial solve-base valid-1?))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn valid-2? [{:keys [:start :end :letter :password]}]
  (->> [start end]
       (map dec)
       (map #(get password %))
       (filter #{letter})
       count
       (= 1)))

(def solve-2 (partial solve-base valid-2?))

;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def p1-testdata (load-data "day2p1t1.txt"))
  (def p1-data (load-data "day2p1.txt")))
