(defproject four-key-cli "0.1.0-SNAPSHOT"
  :description "Command Line Interface (CLI) to extract four-key-metrics"
  :url "https://github.com/erwinvandenbeld/four-key-cli"
  :license {:name "MIT"
            :url "https://choosealicense.com/licenses/mit"
            :comment "MIT License"
            :year 2021
            :key "mit"}
  :dependencies [[org.clojure/clojure "1.10.1"], [clj-jgit "1.0.1"], [com.fzakaria/slf4j-timbre "0.3.21"], [cli-matic "0.4.3"]]
  :plugins [[lein-cloverage "1.2.2"], [lein-kibit "0.1.8"]]
  :repositories [["github" {:url "https://maven.pkg.github.com/erwinvandenbeld"}]]
  :main four-key-cli.core
  :aot [four-key-cli.core]
  :repl-options {:init-ns four-key-cli.core})
