(ns noblepayne.advent2020.day2-test
  (:require [clojure.test :refer :all]
            [noblepayne.advent2020.day2 :refer :all]
            [clojure.java.io :as io]))

(def testdata (load-data "day2p1t1.txt")) 
(def realdata (load-data "day2p1.txt")) 

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-1
  (testing "part 1 test 1"
    (is (= 2 (solve-1 testdata)))))
           
(deftest realtest-1
  (testing "part 1"
    (is (= 422 (solve-1 realdata)))))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-2
  (testing "part 2 test 1"
    (is (= 1 (solve-2 testdata)))))

(deftest realtest-2
  (testing "part 2"
    (is (= 451 (solve-2 realdata)))))
