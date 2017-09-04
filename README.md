# currency_converter

[![codecov](https://codecov.io/gh/alessandrocandolini/currency_converter/branch/master/graph/badge.svg)](https://codecov.io/gh/alessandrocandolini/currency_converter)


## Build & Run

To build the apk (all flavours)
```bash
gradle assemble
```

Flavours are:
* debug: no proguard obfuscation/shrinking, debuggable, code coverage enabled, logs, no signing credentials
* release: proguard for obfuscation/shrinking, non-debuggable, no code coverage, no logs; signing credentials not provided 

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

To generate the KDoc documentation, run the dokka documentation engine through gradle
```bash
gradle dokka
```

For CI/CD I have used nevercode.io:
* It runs unit and integration tests, jacoco test coverage and lint at every push.
* Apks are stored on AWS S3 and email is sent with link to latest apk. 
* After CI/CD runs jacoco, reports are automatic sent to codecov.io to track the code coverage during the development. 
* Sonarque is not well supported by nevercode at the moment (you need to self-host it yourself) so it's not setup
