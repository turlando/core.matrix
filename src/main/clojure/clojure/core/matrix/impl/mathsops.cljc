(ns clojure.core.matrix.impl.mathsops
  #?(:clj (:require [clojure.math.numeric-tower :as numeric-tower]))
  #?(:cljs (:require-macros [clojure.core.matrix.impl.mathsops :refer [def-mop def-mops]])))

;; data structure for code generation of maths operations
;; format is (<symbol> <java.lang.Math equivalent>)

(defn ^double to-degrees*
  [^double x]
  #?(:clj (Math/toDegrees x)
     :cljs (/ (* x 180.0) Math/PI)))

(defn ^double to-radians*
  [^double x]
  #?(:clj (Math/toRadians x)
     :cljs (/ (* x Math/PI) 180.0)))

(def maths-ops
  '[(abs #?(:clj  numeric-tower/abs
            :cljs Math/abs))
    (acos Math/acos)
    (asin Math/asin)
    (atan Math/atan)
    (cbrt Math/cbrt)
    (ceil #?(:clj  numeric-tower/ceil
             :cljs Math/ceil))
    (cos Math/cos)
    (cosh Math/cosh)
    (exp #?(:clj  numeric-tower/expt
            :cljs Math/exp))
    (floor #?(:clj  numeric-tower/floor
              :cljs Math/floor))
    (log Math/log)
    (log10 Math/log10)
    (round #?(:clj  numeric-tower/round
              :cljs Math/rint))
    (signum Math/signum Math/sign)
    (sin Math/sin)
    (sinh Math/sinh)
    (sqrt #?(:clj  numeric-tower/sqrt
             :cljs Math/sqrt))
    (tan Math/tan)
    (tanh Math/tanh)
    (to-degrees Math/toDegrees to-degrees*)
    (to-radians Math/toRadians to-radians*)])

(defmacro def-mop
  "Define a mathematical operator function."
  [op-sym fn-sym]
  `(defn ~op-sym
     ~(vary-meta
        `([~(vary-meta 'x assoc :tag 'double)]
          (~fn-sym ~'x))
        assoc :tag double)))

(defmacro def-mops
  []
  `(do
     ~@(for [[sym# op#] maths-ops]
         `(def-mop ~sym# ~op#))))

(def-mops)

