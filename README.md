# neuron-automation

UI automation tests for [Neuron Wallet](https://github.com/nervosnetwork/neuron/releases).

` Note: currently support better for Windows version.`

## Prerequisites

* Java
* Maven

## Running the tests
Before running test, please make sure to fulfill the `ckbPath` and `nodePath` in the [neuronConfig.yml](neuronConfig.yml) file.

Run system/send transaction tests:
```$xslt
make system-test # running regression tests, except the sending transaction related tests.
make send-test # run the sending transaction related tests only.
```
or:
```$xslt
scripts\neuron.sh system-test # running regression tests, except the sending transaction related tests.
scripts\neuron.sh send-test # run the sending transaction related tests only.
```

## Coding Style
We use "Google Java Style Guide" as coding style guide:

* Official: https://google.github.io/styleguide/javaguide.html
* Chinese version: https://jervyshi.gitbooks.io/google-java-styleguide-zh/content/

### Installing the coding style settings in Intellij

1. Download the [intellij-java-google-style.xml](https://raw.githubusercontent.com/google/styleguide/gh-pages/intellij-java-google-style.xml) file from the http://code.google.com/p/google-styleguide/ repo.
2. go into Preferences -> Editor -> Code Style. Click on Manage and import the downloaded Style Setting file. Select GoogleStyle as new coding style.
