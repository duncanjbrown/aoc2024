(ns aoc.day2
  (:require [clojure.string :as s]))

(defn load-input [path]
  (->> (slurp path)
       (s/split-lines)
       (map #(s/split % #"\s+"))
       (map #(map Integer/parseInt %))))

(def ex (load-input "inputs/day2_example"))
(def puzzle-input (load-input "inputs/day2"))


(comment
  (map #(abs (apply - %)) (partition 2 1 [1 6 3 9 5])))

(defn safe-report? [readings]
  (and
   (or (apply < readings) (apply > readings))
   (let [diffs (map #(abs (apply - %)) (partition 2 1 readings))]
     (>= 3 (apply max diffs)))))

(map safe-report? ex)

;; part 1 solution
(count (filter safe-report? puzzle-input))

; https://stackoverflow.com/a/24553906
(defn drop-nth [n coll]
  (keep-indexed #(when (not= %1 n) %2) coll))

(defn problem-dampener-candidates [readings]
  (map (fn [n] (drop-nth n readings)) (range 0 (count readings))))

(defn safe-report-with-dampening [readings]
  (let [candidates (problem-dampener-candidates readings)]
    (some true? (map safe-report? candidates))))

(count (filter true? (map safe-report-with-dampening puzzle-input)))