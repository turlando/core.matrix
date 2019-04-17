(ns clojure.core.matrix.impl.ndarray-double
  (:refer-clojure :exclude [vector?])
  (:require [clojure.core.matrix.impl.defaults]
            [clojure.core.matrix.impl.ndarray-magic :as magic]
            [clojure.core.matrix.protocols :as mp]
            [clojure.core.matrix.implementations :as imp]
            [clojure.core.matrix.impl.mathsops :as mops]
            [clojure.walk :as w]
            [clojure.core.matrix.impl.ndarray :refer :all]
            [clojure.core.matrix.macros :refer :all]
            [clojure.core.matrix.macros-clj :refer :all]
            [clojure.core.matrix.impl.ndarray-macro :refer :all]
            [clojure.math.numeric-tower :as numeric-tower]))

(magic/spit-code :double)
