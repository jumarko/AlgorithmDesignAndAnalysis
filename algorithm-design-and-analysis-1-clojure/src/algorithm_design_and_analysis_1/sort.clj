(ns algorithm-design-and-analysis-1.sort)

;;; Merge sort
(defn- merge-numbers
  "Merge two sorted collections of numbers into the single collection of sorted numbers."
  [first-numbers second-numbers]
  (cond
    (and (seq first-numbers) (seq second-numbers))
    (let [x (first first-numbers)
          y (first second-numbers)]
      (if (< x y)
        (concat [x] (merge-numbers (rest first-numbers) second-numbers))
        (concat [y] (merge-numbers first-numbers (rest second-numbers)))
        ))
    (seq first-numbers) first-numbers
    (seq second-numbers) second-numbers
    :else [])
  )

(merge-numbers [1 4 5 7] [2 3 6 8])

(defn merge-sort
   "Sorts input collection of numbers using merge sort algorithm."
  [numbers]
  (if (< (count numbers) 2)
    numbers
    (let [[first second] (split-at (/ (count numbers) 2) numbers)]
      (merge-numbers
        (merge-sort first)
        (merge-sort second))
      )))

(merge-sort [5 4 1 8 7 2 6 3])
(merge-sort [5 4 1 8 7 2 6 3 9])
(merge-sort [5 4])
(merge-sort [5])
(merge-sort [])



;;; Selection sort

(defn selection-sort
  "Sorts input vector using selection sort algorithm.
   The duplicate elements are not allowed - they are not preserved in the final result."
  [numbers]
  (loop [sorted []
         unsorted numbers]
    #_(println "sorted: " sorted)
    #_(println "unsorted: " unsorted)
    (if-not (seq unsorted)
      sorted
      (let [unsorted-min (reduce #(if (< %2 %1) %2 %1) unsorted)]
        (recur (conj sorted unsorted-min)
               (filter #(not= unsorted-min %) unsorted)))
      )))

(selection-sort [5 4 1 8 7 2 6 3])
(selection-sort [5 4 1 8 7 2 6 3 9])
(selection-sort [5 4])
(selection-sort [5])
(selection-sort [])


;;; Insertion sort

(defn insertion-sort
  "Sorts the input vector using insertion sort algorithm: https://en.wikipedia.org/wiki/Insertion_sort"
  [numbers]
  (loop [sorted '()
         unsorted numbers]
    (println "sorted: " sorted)
    (println "unsorted: " unsorted)
    (if-not (seq unsorted)
      sorted
      (let [elem (first unsorted)
            sorted-split (split-with #(< % elem) sorted)]
        (recur (concat (sorted-split 0) [elem] (sorted-split 1))
               (rest unsorted)
         )))))

(insertion-sort [5 4 1 8 7 2 6 3])
(insertion-sort [5 4 1 8 7 2 6 3 9])
(insertion-sort [5 4])
(insertion-sort [5])
(insertion-sort [])


;;; Bubble sort
;;; Bubble sort is too strange and poor algorithm to implement it here.
;;; Some ideas on how to implement bubble sort in functional language can be found at https://smthngsmwhr.wordpress.com/2012/11/09/sorting-algorithms-in-haskell/#bubble_sort
