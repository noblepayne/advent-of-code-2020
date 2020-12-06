(ns noblepayne.advent2020.utils)

(defn partition-while
  "Applies f to each value in coll, splitting it each time f
   is true. Splitting continues until f is false. Returns a
   lazy seq of partitions."
  [f coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (let [!f (complement f)
            part (doall (take-while !f s))
            part-len (count part)
            r (nthrest s part-len)
            sep (doall (take-while f r))
            sep-len (count sep)
            rst (nthrest r sep-len)]
        (cons part (partition-while f rst))))))
