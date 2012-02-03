(ns bank-ocr.test.core
  (:require [clojure.java.io :as io])
  (:use [bank-ocr.core])
  (:use [clojure.test]))

(deftest test-digits-from-string
  (is (= [1] (read-digits (io/reader (.getBytes "   \n  |\n  |\n"))))))
                                               