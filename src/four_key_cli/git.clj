(ns four-key-cli.git (:import (org.eclipse.jgit.lib Ref)
                              (org.eclipse.jgit.api Git)
                              (org.eclipse.jgit.revwalk RevWalk)))


(use 'clj-jgit.porcelain)
(use 'clj-jgit.querying)

(defn get-tags
  [^Git repo]
  (.call (.tagList repo)))

(defn get-hash
  [^Ref ref ^Git repo, ^RevWalk rev-walk]
  (.getName (find-rev-commit repo rev-walk (.getObjectId ref))))

(defn get-commit-time
  [^Ref ref ^Git repo, ^RevWalk rev-walk]
  (.getCommitTime (find-rev-commit repo rev-walk (.getObjectId ref))))

(defn ref-to-version
  [^Ref ref]
  (second (re-matches #"refs/tags/(.*)" (.getName ref))))