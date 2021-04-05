(ns four-key-cli.core
  (:require [four-key-cli.lead-time :as lead_time]
            [four-key-cli.release-frequency :as release_frequency]
            [cli-matic.core :refer [run-cmd]])
  (:gen-class))

(defn release-frequency
  [{:keys [path since until]}]
  (println (release_frequency/release-frequency path since until)))

(defn lead-time
  [{:keys [path version]}]
  (println (lead_time/average-lead-time-for-changes path version)))

(def CONFIGURATION
  {:app         {:command     "4key"
                 :description "Four Key Command Line Interface (CLI) to extract four-key-metrics"
                 :version     "0.0.1"}
   :global-opts [{:option "path" :short "p" :as "Repository path" :type :string :default "."}]
   :commands    [{:command     "release-frequency"
                  :description "How often an organization successfully releases to production."
                  :opts        [
                                {:option "since" :short "s" :as "Since (unix timestamp)" :type :int}
                                {:option "until" :short "u" :as "Until (unix timestamp)" :type :int}]
                  :runs        release-frequency}
                 {:command     "lead-time"
                  :description "The amount of time it takes a commit to get into production."
                  :opts        [
                                {:option "version" :short "v" :as "Version tag" :type :string}]
                  :runs        lead-time}
                 ]})




(defn -main [& args]
  (run-cmd args CONFIGURATION))


