(ns algorithm-design-and-analysis-1.split-inversions
  "Algorithms and examples for counting the number of split inversions in an array.
  Check https://www.coursera.org/learn/algorithm-design-analysis/lecture/GFmmJ/o-n-log-n-algorithm-for-counting-inversions-i")


(def my-array [1 3 5 2 4 6])

(defn- with-indices [s]
  (map (fn [x] [(.indexOf s x) x]) s))

(defn- filter-inversions
  "Returns only those pairs of [index elem] which represent inversions of pair passed as a second argument"
  [s-with-indices index elem]
  (filter
    (fn [[idx e]] (and (< idx index) (> e elem)))
    s-with-indices))

(defn naive-split-inversions
  "Counts the number of split inversions in an array using naive approach
  in O(n^2)."
  [s]
  (let [elems-with-indices (with-indices s)
        inversions-per-elem (map
                              (fn [[index elem]]
                                (count (filter-inversions elems-with-indices index elem)))
                              elems-with-indices)]
    (apply + inversions-per-elem)))

(naive-split-inversions my-array)
;; max. number of inversions for 6-item array is 15
(naive-split-inversions [6 5 4 3 2 1])