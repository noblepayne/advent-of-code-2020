(ns noblepayne.advent2020.day5
 (:require [clojure.java.io :as io]
           [clojure.string :as str]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn process-pass [pass]
  {:rowpass (take 7 pass)
   :colpass (drop 7 pass)})

(defn load-data [filename]
  (->> filename
       io/resource
       slurp
       str/split-lines
       (map process-pass)))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(defn pass-to-int [pass]
  (Integer/parseInt
   (->> pass
        (map {\F 0 \B 1 \L 0 \R 1})
        (apply str))
   2))

(defn seat-id [{:keys [:rowpass :colpass]}]
  (let [row (pass-to-int rowpass)
        col (pass-to-int colpass)]
    (+ col (* 8 row))))

(defn solve-1 [data]
  (->> data
       (map seat-id)
       (apply max)))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn solve-2 [data]
  (let [ids (map seat-id data)
        max_ (apply max ids)
        min_ (apply min ids)
        seat-range (range min_ (inc max_))]
    (- (apply + seat-range)
       (apply + ids))))
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata (load-data "day5p1t1.txt"))
  (def realdata (load-data "day5p1.txt")))
