(ns bank-ocr.test.core
  (:require [clojure.java.io :as io])
  (:use [bank-ocr.core]
        [clojure.test]))

(deftest test-digits-from-string
  (with-open [rdr (io/reader (.getBytes "   \n  |\n  |\n"))]
    (is (= [1] (read-digits rdr)))))

(deftest test-question-mark-from-bogon
  (with-open [rdr (io/reader (.getBytes "   \n ||\n  |\n"))]
    (is (= [\?] (read-digits rdr)))))

(deftest test-full-file
  (with-open [rdr (io/reader "resources/digits.txt")]
    (is (= (range 1 10) (read-digits rdr)))))