# Four Key Cli

Command Line Interface (CLI) to extract [four-key-metrics](https://www.thoughtworks.com/radar/techniques/four-key-metrics) from a GIT repository.

##### Lead Time for Changes
*The amount of time it takes a commit to get into production*

Calculates the average lead time for a given version. The version must correspond to a tag in git. For all commits between this 
tag, and the previous tag, the lead time is calculated by extracting the commit timestamp from the version timestamp. 

For this metric to work production releases should be tagged in your repository e.g. using  [semver](https://semver.org/).

##### Deployment frequency
*How often an organization successfully releases to production.*

TODO

##### Change Failure Rate
*The percentage of deployments causing a failure in production*

TODO

##### Time to Restore Ser ices
*How long it takes an organization to recover from a failure in production*

TODO

## Usage

*FIXME create CLI*

*FIXME Docker container (GraalVM?) (see https://hub.docker.com/_/clojure)*
