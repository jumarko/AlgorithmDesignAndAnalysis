(ns algorithm-design-and-analysis-1.matrix-multiplication
  "Demonstrates two algorithms for matrix multiplication
   -- naive with running time O(n^3)\n
   -- Strassen's with running strictly less than O(n^3) using a trick with productions reduction from 8 to 7.

   Matrix is represented by vector of vectors (rows).")

(defn- dot-product
  "Computes dot product of vectos x and y."
  [x y]
  (apply + (map (fn [x y] (* x y)) x y)))

(defn- transpose
  "Tranpose matrix m (vector of vectors) by switching rows with columns.
  Check http://stackoverflow.com/questions/10347315/matrix-transposition-in-clojure"
  [m]
  (apply mapv vector m))

(defn multiply-naive
  "Multiplies two matrices a and b using naive multiplication algorithm"
  [a b]
  (for [row a ]
    (for [col (transpose b)]
      (dot-product row col))))

(multiply-naive
  [[1 2]
   [3 4]]

  [[5 6]
   [7 8]])
