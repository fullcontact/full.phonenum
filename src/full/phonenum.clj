(ns full.phonenum
  (:import (com.google.i18n.phonenumbers PhoneNumberUtil)))


;;; CLOJURE WRAPPER FOR GOOGLE's libphonenumber


(def phone-number-util (PhoneNumberUtil/getInstance))

(defn- replace-prefix [s prefix new-prefix]
  (if (and s (.startsWith s prefix))
    (str new-prefix (.substring s (.length prefix)))
    s))

(defn parse-phone-number
  ([phone-number] (parse-phone-number phone-number "US"))
  ([phone-number region-code]
   (try
     (let [parsed-number (.parse phone-number-util
                                 (replace-prefix phone-number "00" "+")  ; libphonenumber doesn't understand 00
                                 region-code)]
       {:country-code (str (.getCountryCode parsed-number))
        :national-number (str (.getNationalNumber parsed-number))})
     (catch Exception _ nil))))  ; return nil if failed to parse
