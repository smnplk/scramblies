(ns scramblies.unit.api-test
  (:require [clojure.test :refer :all]
            [scramblies.api :as api]
            [scramblies.helpers]))


(deftest scramble?-test
  (testing "It should return true when string `letters` contains enough letters to produce a string `word`"
    (is (true?  (api/scramble? "rekqodlw" "world")))
    (is (true?  (api/scramble? "cedewaraaossoqqyt" "codewars")))
    (is (true?  (api/scramble? "abcdeeed" "deebca")))
    (is (true?  (api/scramble? "aholdnsawolldthr" "allanholdsworth")))
    (is (true?  (api/scramble? "abcdef" "dabc")))
    (is (true?  (api/scramble? "aaa" "aaa"))))

  (testing "It should return false when string `letters` doesn't contain enough letters to produce a `word`"
    (is (false? (api/scramble? "nilso" "noisly")))
    (is (false? (api/scramble? "tehn"  "hentai")))
    (is (false? (api/scramble? "nomrsdfh" "normal")))
    (is (false? (api/scramble? "sktjd" "sketch")))
    (is (false? (api/scramble? "cljutre" "clojure"))))

  (testing "When one or both strings are nil, it should throw an exception with data {:type :bad-request}"
    (is (thrown-with-data? {:type :bad-request} (api/scramble? nil nil)))
    (is (thrown-with-data? {:type :bad-request} (api/scramble? "demo" nil)))
    (is (thrown-with-data? {:type :bad-request} (api/scramble? nil "demo"))))

  (testing "When one or both strings are blank, it should throw an exception with data {:type :unprocessable-entity}"
    (is (thrown-with-data? {:type :bad-request} (api/scramble? nil nil)))
    (is (thrown-with-data? {:type :bad-request} (api/scramble? "demo" nil)))
    (is (thrown-with-data? {:type :bad-request} (api/scramble? nil "demo")))))


;; (run-tests)

;; (def chrs  (map char (range 97 123)))
;; (defn gen-string [len]
;;   (apply str
;;          (repeatedly len #(rand-nth chrs))))

;; (def letters (gen-string 100000))
;; (def word (gen-string 1000000))
