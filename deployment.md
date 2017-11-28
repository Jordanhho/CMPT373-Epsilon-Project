# Deploying the Project

### Connect to the VM

	ssh username@cmpt373-1177e.cmpt.sfu.ca

### Directory structure in vm

    /home/username
          |
          |-- <project>
          |__ __config
                  |__ production.db
                  |__ application.prod.conf

### Copy Relevant Configuration Files to Production Machine.

> You only need to do this once.

Create a directory containing `application.prod.conf` and `production.db`, then copy that directory to the vm via ssh.
	
	scp -r /local/path/to/__config username@cmpt373-1177e.cmpt.sfu.ca:/home/username/__config

### Pull Latest Production Code

Until we setup a continous deployment pipeline, this will have to be done manually.

1. Pull the latest commit from the 'production' branch on Gitlab
	
		cd <project>/epsilonsecurity
		git checkout production
		git pull

2. Shutdown the current running backend server (advice: do it at night).
	
		// the backend server runs on port 80
		sudo lsof -n -i :80 // find its PID
		sudo kill -9 PID

3. Start a new production build, and run the backend server in the background (advice: grab some coffee).

		sbt clean stage
		sudo target/universal/stage/bin/epsilonsecurity -Dconfig.file=/path/to/__config/application.prod.conf > /dev/null &

> `sudo` because on most systems, you need superuser privileges to bind a process to port 80. `> dev/null` because we dont want Play logging to the terminal. `&` simply runs the process in the background.

### Result

Visit the url below to see our handwork.

	cmpt373-1177e.cmpt.sfu.ca

### Important Info

Only once instance of a running process can bind to TCP port 80 at a time. So, only one instance of the production server should be running at any given time on the vm. If you want to test things out, ensure that no instance is already running before spinning up yours (following the instructions above).

---

### References

How Play suggests a production build should be configured - [here](https://www.playframework.com/documentation/2.6.x/ApplicationSecret#the-application-secret)

Why `sbt stage` is good for production - [here](https://www.playframework.com/documentation/2.6.x/Deploying#running-a-production-server-in-place)

Why having a separate production `.conf` file is best practice - [here](https://www.playframework.com/documentation/2.6.x/ApplicationSecret#production-configuration-file)

---

### Known Issues

None for now.