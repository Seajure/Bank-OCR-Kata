(ns bank-ocr.core
  (:require [clojure.java.io :as io]))

(def grid-to-number {[[" " " " " "]
                      [" " " " "|"]
                      [" " " " "|"]] 1

                      [[" " "_" " "]
                       [" " "_" "|"]
                       ["|" "_" " "]] 2

                       [[" " "_" " "]
                        [" " "_" "|"]
                        [" " "_" "|"]] 3

                        [[" " " " " "]
                         ["|" "_" "|"]
                         [" " " " "|"]] 4

                         [[" " "_" " "]
                          ["|" "_" " "]
                          [" " "_" "|"]] 5

                          [[" " "_" " "]
                           ["|" "_" " "]
                           ["|" "_" "|"]] 6

                           [[" " "_" " "]
                            [" " " " "|"]
                            [" " " " "|"]] 7

                            [[" " "_" " "]
                             ["|" "_" "|"]
                             ["|" "_" "|"]] 8

                             [[" " "_" " "]
                              ["|" "_" "|"]
                              [" " "_" "|"]] 9})

(defn rotate [grid]
  (apply map vector grid))

(defn digit-grid [digit]
  (for [line digit]
    (for [char line]
      (str char))))

(defn read-digits [rdr]
  (->> (line-seq rdr)
       (remove empty?)
       (apply map vector)
       (partition-all 3)
       (map (comp rotate digit-grid))
       (map (fn [g] (grid-to-number g \?)))))

(defn read-file [filename]
  (with-open [rdr (io/reader filename)]
    (read-digits rdr)))