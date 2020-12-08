(ns noblepayne.advent2020.day8
 (:require [clojure.string :as str]
           [noblepayne.advent2020.utils :as utils]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;;
(defn process-line [[idx line]]
  (let [[op arg] (str/split line #" ")]
    {:idx idx 
     :op (keyword op)
     :arg (Integer/parseInt arg)}))

(defn load-data [filename]
  (->> (utils/raw-data filename)
       str/split-lines
       (map-indexed vector)
       (mapv process-line)))

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(def instructions
  {:nop (fn [state arg]
          (update state :pos inc))
   :acc (fn [state arg]
          (-> state (update :pos inc) (update :acc + arg)))
   :jmp (fn [state arg]
          (update state :pos + arg))})

(defn run
  ([show-loops? program] (run show-loops? program {:acc 0 :ran #{} :pos 0}))
  ([show-loops? program {:keys [:acc :ran :pos] :as state}]
   (if (= pos (count program))
      acc
      (let [{:keys [:op :arg :idx]} (get program pos)]
        (if (contains? ran idx)
          (if show-loops?
             ::loop
             acc)
          (let [instfn (get instructions op)
                state (instfn state arg)
                state (update state :ran conj idx)]
            (recur show-loops? program state)))))))

(defn solve-1 [program]
  (run false program))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(defn swap-op [program pos]
  (let [{:keys [:op :arg :idx]} (get program pos)
        new-op ({:nop :jmp :jmp :nop} op)]
    (assoc program pos {:op new-op :arg arg :idx idx})))

(defn ops-to-swap [program]
  (->> program
       (filter (comp #{:jmp :nop} :op))
       (map :idx)))

(defn solve-2 [program]
  (->> (ops-to-swap program)
       (map (partial swap-op program))
       (map #(run true %))
       (remove #{::loop})
       first))

;;;;;;;; REPL ;;;;;;;;;;;;;;;;;;;;;;
(comment
  (def testdata1 (load-data "day8p1t1.txt"))
  (def testdata2 (load-data "day8p2t1.txt"))
  (def realdata (load-data "day8p1.txt")))
