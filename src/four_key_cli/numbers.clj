(ns four-key-cli.numbers)

(defn average
  [numbers]
  (if (empty? numbers)
    0.0
    (double (/ (reduce + numbers) (count numbers))) ))

(defn test-kibit
  (if (> test)
           (println "help")
           nil)  )


