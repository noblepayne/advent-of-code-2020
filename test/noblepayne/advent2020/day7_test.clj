(ns noblepayne.advent2020.day7-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [noblepayne.advent2020.day7 :refer :all]))

;;;;;;;; DATA ;;;;;;;;;;;;;;;;;;;;;;
(def testdata1 (load-data "day7p1t1.txt")) 
(def testdata2 (load-data "day7p2t1.txt")) 
(def realdata (load-data "day7p1.txt")) 

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-1
  (testing "part 1 test 1"
    (is (= 4 (solve-1 testdata1)))))

(deftest provided-test-2
  (testing "part 1 test 2"
    (is (= 0 (solve-1 testdata2)))))
           
(deftest realtest-1
  (testing "part 1"
    (is (= 172 (solve-1 realdata)))))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-3
  (testing "part 2 test 1"
    (is (= 32 (solve-2 testdata1)))))

(deftest provided-test-4
  (testing "part 2 test 2"
    (is (= 126 (solve-2 testdata2)))))

(deftest realtest-2
  (testing "part 2"
    (is (= 39645 (solve-2 realdata)))))
