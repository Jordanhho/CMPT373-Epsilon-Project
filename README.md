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

#### Java 1.8

If you don't have Java 1.8 already installed, go ahead and download that from the Oracle website. 

## Running

At the IDEA start screen, open the `epsilonsecurity/` folder. Try compiling the project. SBT may spend a long time downloading resources—be patient.

If all is successful, select 'SBT Run' from the configurations drop down and hit the Run button to launch the server. You will see SBT's output in the integrated console. Go to [`localhost:9000`](localhost:9000) to see your handiwork. The first load may take a while, as SBT is lazily compiling your web page. 

### Command Line

tl;dr call `sbt run` from the `epsilonsecurity` directory

### Details

First you will need to install SBT. Follow the instructions on the [website](http://www.scala-sbt.org/download.html). For the lazy:

If you're using Ubuntu:

    echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
    sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
    sudo apt-get update
    sudo apt-get install sbt

On a Mac you can install SBT (Scala Build Tool) with homebrew:

```
$ brew install sbt@1
```


SBT can be run interactively. If you type `sbt` in your console it will load up the project in the current directory. From here, you can type commands, such as `run`, `clean`, and `exit`. 

The first time you run SBT it may appear to be doing nothing—it is in fact downloading the internet. Either have faith or call it with the `-v` flag. 

From the interactive prompt, calling `run` will start up the server. You can now go to [`localhost:9000`](localhost:9000) to see your handiwork. 

After a server start-up, the first page load will take a while. This is because Play is compiling the necessary resources lazily; future reloads (on this running instance) will be essentially instantaneous. 

Hitting enter again will stop the server and drop you back to the SBT console. Hit `<Control-C>` to exit back to your own shell.  

## Virtual Machine

### Login

To log into our Virtual Machine you must first have `ssh` installed. In the command-line run:

```
ssh SFU_ID@cmpt373-1177e.cmpt.sfu.ca
```

Where you fill in `SFU_ID`. 

You will be prompted on whether you want to permenantly add the host to your known hosts. Say yes. 

Then enter your password. 

### Cloning our Project

Your fresh account does not yet have an SSH key. Generate it by running

```
ssh-keygen -t rsa -C "YOUR_EMAIL"
```
Unless you know what you're doing, just leave it with the default name. 

You will then be prompted to enter a password. Do not leave this field blank; enter a password. 

Next, add your public key to your GitLab profile:

```
cat ~/.ssh/id_rsa.pub
```

Copy the entirety of this output and add it to your profile. 

Now you're ready to `git clone` as described at the top of this document. 

## Troubleshooting:

> **SBT is stuck!**

Be patient; any time you clean SBT's caches—and the first time it's launched—it will have to download a lot of our project's dependencies. It may not give much feedback, but it's probably doing something. 

> **My web page is loading forever!**

By the time you read this, it's probably done loading. Play/SBT compile sources lazily so that you can prototype features faster. The initial compilation only does the bare-minimum required to launch the server, after that it will only compile pages as requested. Subsequent reloads will be instaneous. 

> **Relaunching the server over and over again is tedious!**

Stop reloading the server. Keep it running and let SBT auto-compile your sources as you save them. This will create another long page load, but at least you don't have recompile the whole server. 

> **I'm getting weird errors from SBT!**

IDEs are very complicated and sometimes errors can occur. Your first trouble-shooting step should be to go to `epsilonsecurity/` from the command-line and try running SBT from there. 

Additionally, don't let multiple instances of the server run simultaneously; that's a recipe for distaster. 

> **I'm _still_ getting weird errors from SBT!**

Build systems, like IDEs, are also complicated. Stop the server and recursively delete every single `target/` folder under `epsilonsecurity`. The next launch will require SBT to redownload its cached dependencies. 

> **SBT says there's no project!**

Run SBT from the `epsilonsecurity/` folder, not from our project root. 

> **I'm having other issues!**

Google the issue. Check StackOverflow. Then go to the most relevant Slack channel and ask with as much pertinent detail as possible. 

