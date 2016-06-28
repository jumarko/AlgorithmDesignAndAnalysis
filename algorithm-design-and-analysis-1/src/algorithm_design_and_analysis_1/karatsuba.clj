(ns algorithm-design-and-analysis-1.karatsuba)

(defn digits-count [x]
  (count (.toString x)))

(defn- split-number
  "Splits number into two halves. If it's an odd number than the first half has one more digit than the second.
  Returns vector containing both halves.
  If the number contains single digit, then the single element vector containing the original number is returned."
  [x]
  (let [x-str (.toString x)
        x-length (digits-count x)
        split-point (/ x-length 2)]
    (if (< x-length 2)
      [x]
      (map #(read-string (apply str %)) (split-at split-point x-str))
      )))

(defn karatsuba
  "Multiplies two integers x and y using karatsuba multiplication.
  Both numbers have to had the same number of digits."
  [x y]
  (if (not= (digits-count x) (digits-count y))
    (throw (ex-info "Input numbers must have the same number of digits"
                    {:x-digits-count (digits-count x)
                     :y-digits-count (digits-count y)})))
  ;; debug print
  (println (str "Doing karatsuba " x " * " y))
  (if (< (digits-count x) 2)
    (* x y)
    (let [[a b] (split-number x)
          [c d] (split-number y)
          ac (karatsuba a c)
          bd (karatsuba b d)
          adbc (- (* (+ a b) (+ c d))
                  ac
                  bd)
          n (digits-count x)]
      (+ (* (int (Math/pow 10 n)) ac)
         (* (int (Math/pow 10 (/ n 2))) adbc)
         bd)
      )))

;; examples
;(karatsuba 5678 1234)
;(karatsuba 5 4)
;(karatsuba 12345678 98765432)
;(* 12345678 98765432)


