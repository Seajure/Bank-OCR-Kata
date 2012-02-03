(ns bank-ocr.test.core
  (:require [clojure.java.io :as io])
  (:use [bank-ocr.core])
  (:use [clojure.test]))

(deftest test-digits-from-string
  (with-open [rdr (io/reader (.getBytes "   \n  |\n  |\n"))]
    (is (= [1] (read-digits rdr)))))
                                               