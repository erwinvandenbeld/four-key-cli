(ns four-key-cli.release-frequency
  (:require [four-key-cli.git :as git]))

(use 'clj-jgit.porcelain)

(defn release-frequency
  [^String path ^long since ^long until]
  "Get the number of releases for the given interval (timestamps in seconds)"
  (with-repo path
             (->>
               (git/get-tags repo)
               (filter git/ref-to-version)
               (map #(git/get-commit-time % repo rev-walk))
               (filter #(>= % since))
               (filter #(< % until))
               count
               )
             )
  )
