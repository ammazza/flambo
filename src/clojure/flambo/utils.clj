(ns flambo.utils
  (:require [clojure.tools.logging :as log])
  (:import [scala Tuple2]
           [org.apache.spark.util.random BernoulliSampler XORShiftRandom]
           [java.io PrintStream]
           [flambo WriterOutputStream]
           [org.apache.log4j Logger WriterAppender SimpleLayout]))

(defn echo-types [c]
  (if (coll? c)
    (log/debug "TYPES" (map type c))
    (log/debug "TYPES" (type c)))
  c)

(defn trace [msg]
  (fn [x]
    (log/trace msg x)
    x))

(defn truthy? [x]
  (if x (Boolean. true) (Boolean. false)))

(defn as-integer [s]
  (Integer. s))

(defn as-long [s]
  (Long. s))

(defn as-double [s]
  (Double. s))

(defn bernoulli-sampler [lower-bound upper-bound complement?]
  (BernoulliSampler. lower-bound upper-bound complement?))

(defn sampler-complement [sampler]
  (.cloneComplement sampler))

(defn bootstrap-emacs []
  (-> (Logger/getRootLogger)
      (.addAppender (WriterAppender. (SimpleLayout.) *out*)))
  (System/setOut (PrintStream. (WriterOutputStream. *out*))))

(def ^:private flambo
  ["101111111011101                        11111      1  1                         1"
   "111111111110                          11111       1 11                       111"
   "0111111001                           111111      11111                     11111"
   "0111111                             1111111      11111                   1111111"
   "01111                               1111111     1111111                111111111"
   "101                               111111111   111111111              11111111111"
   "                                 1111111111   111111111         1   11111111111"
   "                                11111111111  1111111111         1 1111111111111"
   "                               111111111111 111111111111        111111111111111"
   "                              11111111111111111111111111       111 111111111111"
   "                             11111111111  1111   1111111       111 111111111111"
   "                            11111111111  1111    1111111    1  1111111111111111"
   "                           1111111111    11       111111  111  1111111111111111"
   "                           111     11             111111 1111  1111111111111111"
   "                          1111  00011              11111 1111  11 1111111111111"
   "                          1111 00 01            1  11111 11111 11 1111111111111"
   "                          1111000 01         0000   1111 11111 1111111111111111"
   "                          111111111         00  0   1111 1111111 11111111111111"
   "                          111111111        0011     1111 1111 11 11111111111111"
   "                          111111111                 1111 1111 11 11111111111111"
   "      11111                11111111      11        1111111111 11 11111111111111"
   " 11111111111111            11111111  0001         11111 11111111  11111111111111"
   "111111111111111111          11111111              11111111111111  11111111111111"
   "1111111111111111111111111111 11111111           111111 1111111111111111111111111"
   "11111111111111111111111111111 111111111       1111111 111111 111 111111111111111"
   "11111111111111111111111111  111 1111111111 111111111 1111111111 1111111111111111"
   "111111111111111111111111111 11111 1111111   11111  11111111 11111111111111111111"
   "1111111111111111111111111111 1111 111111  1111  11111111111111 11111111111111111"
   "11111111111111111111111111 111  1 111111 1 111 11111111111111 111111111111111111"
   "1111111111111111111111111111 111111111111 11 1  11111111111111111111111111111111"
   "111111111111111111111111111111  1111111111 1111 1111111111 1 1111111111111111111"
   "111111111111111111111111111111111 1111111111 111 1111111111111111111111111111111"
   "11111111111111111111111111111111111111111111111111111111111111111111111111111111"
   "1111111111111111111111111111111111111111111111111 111111111 11111111111111111111"
   "1111111111111111111111111111111111 1111111111111111111111 1 11111111111111111111"
   "1111111111111111111111111111111111 111111111111111 11111111 11111111111111111111"
   "11111111111111111111111111111111111111111111111111 11111111 11111111111111111111"
   "11111111111111111111111111111111111 1111111111111111111111 111111111111111111111"
   "1111111111111111111111111111111111111111111111111111111111 1 1111111111111111111"
   "111111111111111111111111111111111111 11111111111111 111111111111111111111111111"])

(defn hail-flambo []
  (doseq [hs flambo]
    (println hs)))
