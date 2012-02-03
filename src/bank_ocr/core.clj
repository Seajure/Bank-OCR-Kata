(ns bank-ocr.core
  (:require [clojure.java.io :as io]))

(def char-map {\space "O" \| "X" \_ "Y"})

(def grid-to-number {[["O" "O" "O"]
                      ["O" "O" "X"]
                      ["O" "O" "X"]] 1})

(defn rotate [grid]
  (apply map vector grid))

(defn digit-grid [digit]
  (for [line digit]
    (for [char line]
      (char-map char))))

(defn read-digits [rdr]
  (let [lines (line-seq rdr)
        non-blank-lines (remove empty? lines)
        columns (apply map vector non-blank-lines)
        digit-grids (partition-all 3 columns)
        rotated-grids (for [grid digit-grids]
                        (rotate (digit-grid grid)))
        numbers (map grid-to-number rotated-grids)]
    (doseq [digit digit-grids]
      (println (interpose \newline rotated-grids)))
    numbers))

(defn read-file [filename]
  (with-open [rdr (io/reader filename)]
    (read-digits rdr)))