# Four Key Cli

Command Line Interface (CLI) to extract [four-key-metrics](https://www.thoughtworks.com/radar/techniques/four-key-metrics) from a GIT repository.

## Usage

### Lead Time for Changes
*The amount of time it takes a commit to get into production*

Calculates the average lead time for a given version. The version must correspond to a tag in git. For all commits between this 
tag, and the previous tag, the lead time is calculated by extracting the commit timestamp from the version timestamp. 

For this metric to work production releases should be tagged in your repository e.g. using  [semver](https://semver.org/).

`java -jar [uberjar filename] --path [repository path] lead-time --version [version tag name] `

### Release frequency
*How often an organization successfully releases to production.*

Calculates the number of GIT version tags for a given interval.

`java -jar [uberjar filename] --path [repository path] release-frequency --since [Since (unix timestamp)] --until [Until (unix timestamp)]`

### Change Failure Rate
*The percentage of deployments causing a failure in production*

TODO

### Time to Restore Services
*How long it takes an organization to recover from a failure in production*

TODO


- FIXME build CI
- FIXME Docker container (GraalVM?) (see https://hub.docker.com/_/clojure)*
