# Manual Installation
This is a quick "README" on how to manually install the dependencies on your local machine
 and work through the examples there incase you might want to modify the Reference Implementation or API.
 
 ## Clone repositories
 It's mandatory to clone the three following repositories:
 
 * [VisRec API](https://github.com/JavaVisRec/visrec-api)
 * [DeepNetts Core Community Edition (CE)](https://github.com/deepnetts/deepnetts-communityedition)
 * [VisRec Reference Implementation](https://github.com/JavaVisRec/visrec-ri)
 
 ## Deploy to local Maven
 Deploy (publish) all three cloned repositories to your local maven. Some repositories
 have their specified commands:
 * visrec-api: `mvn install -Dmaven.test.skip=true`
 * deepnetts: change directory to `deepnetts-core/` and then `mvn install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true`
 * visrec-ri: `mvn install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true`
 
 ## Finished
 Now you should be able to run all the examples in this repository. If you were reading the "Getting Started"
 article, proceed on example 1 !