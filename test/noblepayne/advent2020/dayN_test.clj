(ns noblepayne.advent2020.dayN-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [noblepayne.advent2020.dayN :refer :all]))

(def testdata (load-data "dayNp1t1.txt")) 
(def realdata (load-data "dayNp1.txt")) 

;;;;;;;; PART 1 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-1
  (testing "part 1 test 1"
    (is (= nil (solve-1 testdata)))))
           
(deftest realtest-1
  (testing "part 1"
    (is (= nil (solve-1 realdata)))))

;;;;;;;; PART 2 ;;;;;;;;;;;;;;;;;;;;
(deftest provided-test-2
  (testing "part 2 test 1"
    (is (= nil (solve-2 testdata)))))

(deftest realtest-2
  (testing "part 2"
    (is (= nil (solve-2 realdata)))))
