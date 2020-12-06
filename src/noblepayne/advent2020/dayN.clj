(ns noblepayne.advent2020.dayN
 (:require [clojure.string :as str]
           [noblepayne.advent2020.utils :as utils]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn load-data [filename]
  (str/split-lines (utils/raw-data filename)))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(defn solve-1 [data])

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn solve-2 [data])
    
;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata (load-data "dayNp1t1.txt"))
  (def realdata (load-data "dayNp1.txt")))
