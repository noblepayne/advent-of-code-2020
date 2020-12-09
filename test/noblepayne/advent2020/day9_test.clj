(ns noblepayne.advent2020.day9-test
  (:require [clojure.test :refer :all]
            [noblepayne.advent2020.day9 :refer :all]))

(def testdata (load-data "day9p1t1.txt")) 
(def realdata (load-data "day9p1.txt")) 

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-1
  (testing "part 1 test 1"
    (is (= 127 (solve-1 testdata 5)))))
           
(deftest realtest-1
  (testing "part 1"
    (is (= 393911906 (solve-1 realdata 25)))))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-2
  (testing "part 2 test 1"
    (is (= 62 (solve-2 testdata 127)))))

(deftest realtest-2
  (testing "part 2"
    (is (= 59341885 (solve-2 realdata 393911906)))))
