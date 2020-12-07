(ns scramblies.api
  (:require [clojure.string :as st]
            [scramblies.middleware.errors :as errors]))

;; (defn scramble? [str1 str2]
;;   (let [char-map-s2 (frequencies str2)
;;         char-map-s1 (frequencies str1)]
;;     (every? (fn [[k v]]
;;               (and (contains? char-map-s1 k)
;;                    (>= (get char-map-s1 k) v)))
;;             char-map-s2)))

;; (defn scramble? [letters word]
;;   (let [letter-cnt (select-keys (frequencies letters) (seq word))
;;         word-cnt   (frequencies word)]
;;     (every? (comp zero? val)
;;             (merge-with (fn [c1 c2]
;;                           (+ c1 (- c2)))
;;                         word-cnt letter-cnt))))


(defn ^:private letter-available? [letters-cnt letter]
  (and (contains? letters-cnt letter)
       (pos? (get letters-cnt letter))))


(defn ^:private *scramble? [letters word]
  (loop [letters-cnt (frequencies letters) word-seq (seq word)]
    (if (empty? word-seq)
      true
      (let [letter (first word-seq)]
        (if (letter-available? letters-cnt letter)
          (recur (update letters-cnt letter dec) (rest word-seq))
          false)))))


(defn scramble?
  "Returns true if a portion of characters from `letters`  can be arranged into `word`"
  [^String letters ^String word]
  (cond
    (or (nil? letters) (nil? word))
    (throw (errors/bad-request "`letters` or `word` string is null"))

    (or (st/blank? letters) (st/blank? word))
    (throw (errors/unprocessable-entity "`letters` or `word` string is blank"))

    :else
    (*scramble? letters word)))
