(ns noblepayne.advent2020.day4
 (:require [clojure.java.io :as io]
           [clojure.string :as str]
           [clojure.set :as set]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn clean-data [raw-data]
  (for [raw-passport (str/split raw-data #"\n\n")]
    (into {}
      (for [entry (str/split raw-passport #"\s|\n")]
        (let [[k v] (str/split entry #":")]
          [(keyword k) v])))))

(defn load-data [filename]
  (->> filename
       io/resource
       slurp
       clean-data))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(def required-keys #{:byr :iyr :eyr :hgt :hcl :ecl :pid})

(defn valid-1? [passport]
  (set/subset?
    required-keys
    (set (keys passport))))

(defn solve-base [valid-fn data]
  (->> data
       (filter valid-fn)
       count))

(def solve-1 (partial solve-base valid-1?))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn number-between? [lower upper s]
  (<= lower (Integer/parseInt s) upper))

(defn matches-with-count? [re cnt s]
  (if (re-matches re s)
    (= cnt (count s))))

(def validators {:byr (partial number-between? 1920 2002)
                 :iyr (partial number-between? 2010 2020)
                 :eyr (partial number-between? 2020 2030)
                 :pid (partial matches-with-count? #"\d+" 9)
                 :hcl (partial matches-with-count? #"#[0-9a-f]+" 7)
                 :ecl #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"}
                 :hgt #(if-let [[_ height unit] (re-matches #"(\d+)(cm|in)" %)]
                         (case unit
                           "in" (number-between? 59 76 height)
                           "cm" (number-between? 150 193 height)
                           false))})

(defn valid-passport-entry? [[k v]]
  (let [validator (get validators k (constantly true))]
    (validator v)))

(defn valid-2? [passport]
  (and (valid-1? passport)
       (every? valid-passport-entry? passport)))

(def solve-2 (partial solve-base valid-2?))

;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def p1-testdata (load-data "day4p1t1.txt"))
  (def p1-data (load-data "day4p1.txt")))
