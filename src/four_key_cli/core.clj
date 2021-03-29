(ns four-key-cli.core
  (:import (org.eclipse.jgit.lib Ref)
           (org.eclipse.jgit.api Git)
           (org.eclipse.jgit.revwalk RevWalk)))

(use 'clj-jgit.porcelain)
(use 'clj-jgit.querying)

(defn ^:private get-tags
  [^Git repo]
  (.call (.tagList repo)))

(defn ^:private get-hash
  [^Ref ref ^Git repo, ^RevWalk rev-walk]
  (.getName (find-rev-commit repo rev-walk (.getObjectId ref))))

(defn ^:private get-commit-time
  [^Ref ref ^Git repo, ^RevWalk rev-walk]
  (.getCommitTime (find-rev-commit repo rev-walk (.getObjectId ref))))

(defn ^:private average
  [numbers]
  (if (empty? numbers)
    0
    (/ (reduce + numbers) (count numbers))))

(defn ^:private average-lead-time-between-commits
  [^Git repo ^RevWalk rev-walk coll]
  (->>
    (git-log repo :since (get-hash (second coll) repo rev-walk) :until (get-hash (first coll) repo rev-walk))
    (map :id)
    (drop 1)
    (map #(.getCommitTime %))
    (map #(- (get-commit-time (first coll) repo rev-walk) %))
    average
    double
    ))

(defn ^:private ref-to-version
  [^Ref ref]
  (second (re-matches #"refs/tags/(.*)" (.getName ref))))

(defn ^:private filter-version-and-previous-version
  [version coll]
  (take 2 (drop-while #(not= (ref-to-version %) version) coll)))


(defn average-lead-time-for-changes
  "Get the average lead time of all changes (commits) in this version"
  [path version]
  (with-repo path
             (->>
               (get-tags repo)
               (sort-by #(get-commit-time % repo rev-walk))
               reverse
               (filter-version-and-previous-version version)
               (average-lead-time-between-commits repo rev-walk)
               )
             )
  )

(defn release-frequency
  [path ^Long since ^Long until]
  "Get the number of releases for the given interval (timestamps in seconds)"
  (with-repo path
             (->>
               (get-tags repo)
               (filter ref-to-version)
               (map #(get-commit-time % repo rev-walk))
               (filter #(>= % since))
               (filter #(< % until))
               count
               )
             )
  )

;FIXME Clean methods, seperate namespaces
