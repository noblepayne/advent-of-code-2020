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
(defn bpart [{:keys [:lower :upper]} ltr]
  (let [𝚫 (- upper lower)
        ½𝚫 (-> 𝚫 (/ 2) Math/floor int)
        new-lower (- upper ½𝚫)
        new-upper (+ lower ½𝚫)]
    (case ltr
      \F {:lower lower :upper new-upper}
      \L {:lower lower :upper new-upper}
      \B {:lower new-lower :upper upper}
      \R {:lower new-lower :upper upper})))

(defn find-base [start input]
  (->> input
       (reduce bpart start)
       :lower))

(def find-row (partial find-base {:lower 0 :upper 127}))
(def find-col (partial find-base {:lower 0 :upper 7}))

(defn seat-id [{:keys [:rowpass :colpass]}]
  (let [row (find-row rowpass)
        col (find-col colpass)]
    (+ col (* 8 row))))

(defn solve-1 [data]
  (->> data
       (map seat-id)
       (apply max)))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn solve-2 [data]
  (->> data
       (map seat-id)
       sort
       (partition 2 1) ;; e.g. ([123, 124], [124, 125])
       (map reverse)
       (filter #(not= 1 (apply - %)))
       first
       first
       dec))

;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata (load-data "day5p1t1.txt"))
  (def realdata (load-data "day5p1.txt")))
