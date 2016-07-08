(ns algorithm-design-and-analysis-1.split-inversions
  "Algorithms and examples for counting the number of split inversions in an array.
  Check https://www.coursera.org/learn/algorithm-design-analysis/lecture/GFmmJ/o-n-log-n-algorithm-for-counting-inversions-i")


(def my-array [5 3 1 6 4 2])

(defn- with-indices [s]
  (map (fn [x] [(.indexOf s x) x]) s))

(defn- filter-inversions
  "Returns only those pairs of [index elem] which represent inversions of pair passed as a second argument"
  [s-with-indices index elem]
  (filter
    (fn [[idx e]] (and (< idx index) (> e elem)))
    s-with-indices))

(defn naive-inversions
  "Counts the number of \"array\" inversions using naive approach O(n^2)."
  [s]
  (let [elems-with-indices (with-indices s)
        inversions-per-elem (map
                              (fn [[index elem]]
                                (count (filter-inversions elems-with-indices index elem)))
                              elems-with-indices)]
    (apply + inversions-per-elem)))

(naive-inversions my-array)
;; max. number of inversions for 6-item array is 15
(naive-inversions [6 5 4 3 2 1])



;;; TODO: following implementation is not correct and too complex
(defn- sort-and-count-split-inversions [first-half second-half]
  (println "first-half: " first-half)
  (println "second-half: " second-half)
  (cond
    (and (seq first-half) (seq second-half))
    (let [x (first first-half)
          y (first second-half)]
      (if (< x y)

        ;; if the elements from the first half are processed as first that means there are no split inversions
        (-> (sort-and-count-split-inversions (rest first-half) second-half)
            (update 0 (fn [sorted] (cons x sorted)))
            )

        ;; THIS IS CRITICAL PART!!!
        ;; if we are taking the number from the second half because it is smaller than then actual number
        ;; from first half, then we need to add the count of split inversions that this implies
        ;; -> that is the number of unprocessed elements from the first half
        (-> (sort-and-count-split-inversions first-half (rest second-half))
            (update 0 (fn [sorted] (cons x sorted)))
            (update 1 (fn [inversions-count] (+ inversions-count
                                                (count first-half)))))

        ))
    (seq first-half) [first-half 0]
    (seq second-half) [second-half (count second-half)]
    :else [[] 0]
    ))

(defn sort-and-count-inversions
  "Counts the number of \"array\" inversions using more efficient O(n log(n)) algorithm.
  Returns vector of two elements - first is sorted sequence, second is the number of inversions in original sequence."
  [s]
  (println "sequence: " s)
  (if (< (count s) 2)
    [s 0]
    (let [[first-half second-half] (split-at (/ (count s) 2) s)
          first-half-inversions (sort-and-count-inversions first-half)
          second-half-inversions (sort-and-count-inversions second-half)
          split-inversions (sort-and-count-split-inversions first-half second-half)
          total-inversions (+
                             (second first-half-inversions)
                             (second second-half-inversions)
                             (second split-inversions)
                             )]
      ;; the final outcome is the sorted sequence taken from split-inversions result and total number of inversions
      [(first split-inversions) total-inversions]
      )
    ))


(sort-and-count-inversions my-array)
;; TODO: returns 8 instead of 3 !
(sort-and-count-inversions [1 3 5 2 4 6])
;; max. number of inversions for 6-item array is 15
(sort-and-count-inversions [6 5 4 3 2 1])


