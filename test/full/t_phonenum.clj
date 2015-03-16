(ns full.t-phonenum
  (:require [midje.sweet :refer :all]
            [full.phonenum :refer :all]))

(facts
  "about phone number parsing"
  (parse-phone-number "123" "US") => {:country-code "1" :national-number "123"}
  (parse-phone-number "+14156843376") => {:country-code "1" :national-number "4156843376"}
  (parse-phone-number " 415-684-3376 ") => {:country-code "1" :national-number "4156843376"}
  (parse-phone-number "+371-2929.9959") => {:country-code "371" :national-number "29299959"}
  (parse-phone-number "0037129299959") => {:country-code "371" :national-number "29299959"})
