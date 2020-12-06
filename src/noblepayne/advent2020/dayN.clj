(ns noblepayne.advent2020.dayN
 (:require [clojure.java.io :as io]
           [clojure.string :as str]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn raw-data [filename]
  (slurp (io/resource filename)))

(defn load-data [filename]
  (str/split-lines (raw-data filename)))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;

(defn solve-1 [data])

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn solve-2 [data])
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata (load-data "dayNp1t1.txt"))
  (def realdata (load-data "dayNp1.txt")))
