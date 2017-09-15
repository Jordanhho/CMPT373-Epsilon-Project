## Command line instructions

```
git clone git@csil-git1.cs.surrey.sfu.ca:apa53/373-Epsilon.git
cd 373-Epsilon
```

Don't forget to setup your identity:

```
git config --global user.name "Boaty McBoatface"
git config --global user.email "mcface@sfu.ca"
```

If you actively develop with a different name/email you can use the `--local` flag to only set this email for this git repo.


## Installation

### Requirements

  1. Java 1.8
  2. SBT 

#### Java 1.8

If you don't have Java 1.8 already installed, go ahead and download that from the Oracle website. 

#### SBT

Follow the instructions on the [website](http://www.scala-sbt.org/download.html). For the lazy:

If you're using Ubuntu:

    echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
    sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
    sudo apt-get update
    sudo apt-get install sbt

On a Mac you can install SBT (Scala Build Tool) with homebrew:

```
$ brew install sbt@1
```

## Running the Server

tl;dr call `sbt run` from the `epsilonsecurity` directory

### Details

SBT can be run interactively. If you type `sbt` in your console it will load up the project in the current directory. From here, you can type commands, such as `run`, `clean`, and `exit`. 

The first time you run SBT it may appear to be doing nothing—it is in fact downloading the internet. Either have faith or call it with the `-v` flag. 

From the interactive prompt, calling `run` will start up the server. You can now go to [`localhost:9000`](localhost:9000) to see your handiwork. 

After a server start-up, the first page load will take a while. This is because Play is compiling the necessary resources lazily; future reloads (on this running instance) will be essentially instantaneous. 

Hitting enter again will stop the server and drop you back to the SBT console. Hit `<Control-C>` to exit back to your own shell.  
