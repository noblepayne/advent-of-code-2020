(ns noblepayne.advent2020.day6-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [noblepayne.advent2020.day6 :refer :all]))

(def testdata (load-data "day6p1t1.txt")) 
(def realdata (load-data "day6p1.txt")) 

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-1
  (testing "part 1 test 1"
    (is (= 11 (solve-1 testdata)))))
           
(deftest realtest-1
  (testing "part 1"
    (is (= 6662 (solve-1 realdata)))))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-2
  (testing "part 2 test 1"
    (is (= 6 (solve-2 testdata)))))

(deftest realtest-2
  (testing "part 2"
    (is (= 3382 (solve-2 realdata)))))
