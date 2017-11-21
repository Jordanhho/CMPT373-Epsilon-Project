# Deploying the Project

### Connect to vm

	ssh ueyee@cmpt373-1177e.cmpt.sfu.ca

### Directory structure in vm

	USER_HOME
	  |
	  |-- <project>
	  |__ __config
	          |__ production.db
	          |__ application.prod.conf

### Copying relevant configuration files to production machine.

> You only need to do this once.

Create a directory containing `application.prod.conf` and `production.db`, then copy that directory to the vm via ssh.
	
	scp -r ./__config ueyee@cmpt373-1177e.cmpt.sfu.ca:/home/ueyee/__config

### Pull latest production code

Until we setup a continous deployment pipeline, this will have to be done manually.

1. Pull the latest commit from the 'production' branch on Gitlab
	
		cd <project>
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

----------------------------------------------------------------------

### Snippets

	// copy from local machine to remote machine
	scp localfile user@host:/path/to/whereyouwant/thefile

	// copy the production.db to remote vm
	scp ./production.conf ueyee@cmpt373-1177e.cmpt.sfu.ca:/home/ueyee/production.conf

	sudo lsof -n -i :80
	sudo npm install -g webpack

	target/universal/stage/bin/epsilonsecurity

	// running the project in production mode
	target/universal/stage/bin/epsilonsecurity -Dconfig.file=/absolute/path/to/production.conf

	sudo target/universal/stage/bin/epsilonsecurity -Dconfig.file=/home/ueyee/__config/application.prod.conf > /dev/null &

	//todo: don't use a PID file
	rm -f /home/ueyee/373-Epsilon/epsilonsecurity/target/universal/stage/RUNNING_PID




