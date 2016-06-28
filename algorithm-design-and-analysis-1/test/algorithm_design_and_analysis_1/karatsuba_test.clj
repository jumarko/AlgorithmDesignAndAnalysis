(ns algorithm-design-and-analysis-1.core-test
  (:require [clojure.test :refer :all]
            [algorithm-design-and-analysis-1.karatsuba :refer :all]))

(deftest a-test
  (testing "Multiplication of 4-digit numbers."
    (is (= (karatsuba 5678 1234) 7006652))))
