(ns four-key-cli.numbers-test
  (:require [clojure.test :refer :all]
            [four-key-cli.numbers :refer :all]))

(deftest average-of-empty-list
  (testing "Empty list results in 0.0"
    (is (= 0.0 (average  [])))))

(deftest average-of-list
  (testing "Average of some numbers is correct"
    (is (= 5.0 (average [3 4 6 7 5])))))
