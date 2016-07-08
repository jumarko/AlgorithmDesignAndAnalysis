(ns algorithm-design-and-analysis-1.matrix-multiplication-test
  (:require [clojure.test :refer :all]
            [algorithm-design-and-analysis-1.matrix-multiplication :refer :all]))

(deftest naive-multiplication
  (testing "multiply empty matrices"
    (is (= [[]] (multiply-naive [[]] [[]] ))))

  (testing "multiply 2x2 matrices"
    (is (= [[19 22]
            [43 50]]
           (multiply-naive [[1 2]
                            [3 4]]
                           [[5 6]
                            [7 8]] ))))



  (testing "multiply 3x3 matrices"
    (is (= [[18 34 32]
            [60 118 116]
            [39 76 74]]
           (multiply-naive [[1 2 3]
                            [7 8 9]
                            [4 5 6]]
                           [[1 0 3]
                            [1 8 4]
                            [5 6 7]] ))))
  )