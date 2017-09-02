# currency_converter

[![codecov](https://codecov.io/gh/alessandrocandolini/currency_converter/branch/master/graph/badge.svg)](https://codecov.io/gh/alessandrocandolini/currency_converter)


## Build & Run

To build the apk (all flavours)
```bash
gradle assemble
```

To run unit tests
```bash
gradle test
```

To run jacoco test coverage report (in debug flavour)
```bash
 gradle jacocoTestDebugUnitTestReport
 ```
 
 To run lint static code analyser
```bash
gradle lint
```

For CI/CD I used nevercode.io, running unit and integration tests, jacoco test coverage and lint at every push.
Apks are stored on AWS S3 and email is sent with link to latets apk. 
After CI/CD runs jacoco, reports are automatic sent to codecov.io to track the code coverage during the development. 
SOnarque is not well supported by nevercode at the moment (you need to self-host it yourself) so it's not setup
