(ns four-key-cli.lead-time
  (:require [four-key-cli.numbers :as numbers]
            [four-key-cli.git :as git])
  (:import (org.eclipse.jgit.api Git)
           (org.eclipse.jgit.revwalk RevWalk)))

(use 'clj-jgit.porcelain)
(use 'clj-jgit.querying)

(defn ^:private average-lead-time-between-commits
  [^Git repo ^RevWalk rev-walk coll]
  (->>
    (git-log repo :since (git/get-hash (second coll) repo rev-walk) :until (git/get-hash (first coll) repo rev-walk))
    (map :id)
    (drop 1)
    (map #(.getCommitTime %))
    (map #(- (git/get-commit-time (first coll) repo rev-walk) %))
    (numbers/average)
    ))

(defn ^:private filter-version-and-previous-version
  [^String version coll]
  (take 2 (drop-while #(not= (git/ref-to-version %) version) coll)))

(defn average-lead-time-for-changes
  "Get the average lead time of all changes (commits) in this version"
  [^String path ^String version]
  (with-repo path
             (->>
               (git/get-tags repo)
               (sort-by #(git/get-commit-time % repo rev-walk))
               reverse
               (filter-version-and-previous-version version)
               (average-lead-time-between-commits repo rev-walk)
               )
             )
  )
