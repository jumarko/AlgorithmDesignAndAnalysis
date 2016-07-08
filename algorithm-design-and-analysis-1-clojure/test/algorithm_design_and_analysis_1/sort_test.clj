(ns algorithm-design-and-analysis-1.sort-test
  (:require [clojure.test :refer :all]
            [algorithm-design-and-analysis-1.sort :refer :all]))

(deftest merge-sort-in-increasing-order
  (testing "Sort input vector properly in increasing order"
    (is (= [1 2 3 4 5 6 7 8]
           (merge-sort [5 4 1 8 7 2 6 3])))
    ))

(deftest merge-sort-for-empty-input
  (testing "Sort input vector properly in increasing order"
    (is (= []
           (merge-sort [])))
    ))

(deftest merge-sort-for-sorted-input
  (testing "Sort input vector properly in increasing order"
    (is (= [1 2 3 4]
           (merge-sort [1 2 3 4])))
    ))
