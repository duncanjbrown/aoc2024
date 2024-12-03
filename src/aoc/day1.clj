(ns aoc.day1
  (:require [clojure.string :as s]))

(defn load-input [path]
  (->> (slurp path)
       (s/split-lines)
       (map #(s/split % #"\s+"))
       (map #(map Integer/parseInt %))))

(defn day-1 [input]
               ;; matrix transform
  (let [lists (apply map list input)
       [sorted-1 sorted-2] (map sort lists)
       diffs (map - sorted-1 sorted-2)
       abs-diffs (map abs diffs)]
   (reduce + abs-diffs)))

(day-1 (load-input "inputs/day1_example"))
(day-1 (load-input "inputs/day1"))

(defn day-1-part-2 [input]
  (let [[list1 list2] (apply map list input)
       freqs (frequencies list2)]
    (reduce + (map #(* % (get freqs % 0)) list1))))

(day-1-part-2 (load-input "inputs/day1_example"))
(day-1-part-2 (load-input "inputs/day1"))