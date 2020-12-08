(ns noblepayne.advent2020.day8-test
  (:require [clojure.test :refer :all]
            [noblepayne.advent2020.day8 :refer :all]))

(def testdata (load-data "day8p1t1.txt")) 
(def realdata (load-data "day8p1.txt")) 

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-1
  (testing "part 1 test 1"
    (is (= 5 (solve-1 testdata)))))
           
(deftest realtest-1
  (testing "part 1"
    (is (= 1317 (solve-1 realdata)))))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-2
  (testing "part 2 test 1"
    (is (= 8 (solve-2 testdata)))))

(deftest realtest-2
  (testing "part 2"
    (is (= 1033 (solve-2 realdata)))))
