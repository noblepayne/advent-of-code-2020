(ns noblepayne.advent2020.day5-test
  (:require [clojure.test :refer :all]
            [noblepayne.advent2020.day5 :refer :all]
            [clojure.java.io :as io]))

(def testdata (load-data "day5p1t1.txt")) 
(def realdata (load-data "day5p1.txt")) 

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-1
  (testing "part 1 test 1"
    (is (= 820 (solve-1 testdata)))))
           
(deftest realtest-1
  (testing "part 1"
    (is (= 901 (solve-1 realdata)))))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(deftest realtest-2
  (testing "part 2"
    (is (= 661 (solve-2 realdata)))))
