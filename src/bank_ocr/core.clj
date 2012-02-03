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
  (->> (line-seq rdr)
       (remove empty?)
       (apply map vector)
       (partition-all 3)
       (map (comp rotate digit-grid))
       (map grid-to-number)))

(defn read-file [filename]
  (with-open [rdr (io/reader filename)]
    (read-digits rdr)))