(ns noblepayne.advent2020.day1-test
  (:require [clojure.test :refer :all]
            [noblepayne.advent2020.day1 :refer :all]
            [clojure.java.io :as io]))

(def testdata (load-data "day1p1t1.txt")) 
(def realdata (load-data "day1p1.txt")) 

(deftest provided-test-1
  (testing "part 1 test 1"
    (is (= 514579 (solve1 testdata)))))
           
(deftest realtest-1
  (testing "part 1"
    (is (= 181044 (solve1 realdata)))))

;;;;;;;;;;;;;; part 2 ;;;;;;;;;;;;;;;;;;;;

(deftest provided-test-2
  (testing "part 2 test 1"
    (is (= 241861950 (solve2 testdata)))))

(deftest realtest-2
  (testing "part 2"
    (is (= 82660352 (solve2 realdata)))))
