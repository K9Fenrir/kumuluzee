# KumuluzEE Maven Plugin

> KumuluzEE Maven Plugin for the Kumuluz EE microservice framework

TODO - description

## Usage

Include the plugin in your project:

```xml
<plugin>
    <groupId>com.kumuluz.ee</groupId>
    <artifactId>kumuluzee-maven-plugin</artifactId>
    <version>${kumuluzee.version}</version>
</plugin>
```

### Goals

* __kumuluzee:copy-dependencies__
    
    Copy dependencies and prepare for execution in an exploded class and dependency runtime.


* __kumuluzee:repackage__

    Repackages existing JAR archives so that they can be executed from the command line using `java -jar`.
    
    ###### Parameters
    
    * __finalName__
    
        Final name of the generated "uber" JAR.
        
        __Default value is__: `${project.build.finalName}` or `${project.artifactId}-${project.version}`
        
    * __outputDirectory__
    
        Directory containing the generated JAR.
        
        __Default value is__: `${project.build.directory}`
    
* __kumuluzee:run__

    Run the application in an exploded class and dependency runtime.


* __kumuluzee:generate-dockerfile__

    Generates a Dockerfile for the packaged JAR or the exploded class and dependecy runtime

    ###### Parameters

    * __packagingType__

        Desired packaging type.

        __Default value is__: 'uber'

        __Supported options are__: 'uber', 'layered', 'exploded'

    * __baseImage__

        Base image for the Dockerfile to use.

        __Default value is__: 'openjdk:11'

    * __windowsOS__

        Boolean determining whether the Docker image will be run in a Windows environment.

        __Default value is__: 'false'


    * __appModules__

        List of module names that are expected to change often. Modules listed here will be placed in the upper layers of the Docker image.

    * __port__

        The port the application listens on. If not specified here, will check config.yaml. If neither are present, will take default.

        __Default value is__: '8080'
