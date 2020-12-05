(ns noblepayne.advent2020.day5
 (:require [clojure.java.io :as io]
           [clojure.string :as str]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn raw-data [filename]
  (slurp (io/resource filename)))

(defn load-data [filename]
  (str/split-lines (raw-data filename)))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(defn seat-id [pass]
  (let [binary-pass (apply str (map {\F 0 \B 1 \L 0 \R 1} pass))]
    (Integer/parseInt binary-pass 2)))

(defn solve-1 [data]
  (apply max (map seat-id data)))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn solve-2 [data]
  (let [ids (map seat-id data)
        max-id (apply max ids)
        min-id (apply min ids)
        all-ids (range min-id (inc max-id))]
    (- (apply + all-ids)
       (apply + ids))))
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata (load-data "day5p1t1.txt"))
  (def realdata (load-data "day5p1.txt")))
