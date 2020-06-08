## Opsly Test and Spring Boot demo

The output from curl only shows the word 'test' currently. To see
the output so far you need to run from within the IDEA.

The output is no more than a log.info at present.

The HTTP tests are commented out as these were for the 'demo' and not the test and need to be re-written

This is a work in progress and needs to have added - 

Loops for checking status.
Retry of URL as they often error
Returning the json string to the controller.

To Execute:
```
./gradlew clean build bootrun
curl http://localhost:3000/
```

