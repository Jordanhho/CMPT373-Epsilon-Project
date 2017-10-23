# Type Safe Queries

## Installation Instructions

  1. Install [Ebean Enhancement Plugin](http://ebean-orm.github.io/docs/query/typesafe) to IntelliJ, v11.x

  2. Add these dependencies to `build.sbt`, then do refresh/auto-import

    libraryDependencies += "io.ebean" % "querybean-generator" % "10.1.2"
    libraryDependencies += "io.ebean" % "ebean-querybean" % "10.1.1"

  3. Enable Annotation Processor - Setting => Build, Execution & Deployment => Compiler => Annotation Processor
   - Just tick the check box, no need to change anything else.

  4. Rebuild the project with Ebean Enhancement Plugin options checked.
Now the project should rebuild and auto-generate Q classes needed for type-safe queries.

If the Message toolbar displays messages of: information: enhanced.... with the names of Q classes matching with Db classes, then the generator succeeded. Otherwise, turn off IntelliJ, turn it on, and rebuild again.

There are also alternative of creating the generator manually that would need to be researched upon further, if this method can't guarantee stable setup.

After finished, in `target/scala-2.12/classes`, there should be a new folder called `generated`, containing all of our
newly generated classes.

  5. Go to File => Project Structure => Follow the path of target/scala-2.12/classes/generated.
   Add that path to sources (Find the above path-> click Sources)

  6. Exit that window, Build -> Build Module 'root'

  7. Rebuild Project again.

  8. Go to some class, and try to write QDbUser (or any QDb class you want). If IntelliJ auto-suggest "import package" for that class, then you got it setup properly.

  9. Note: When you do `sbt run`, the terminal may span multiple lines of "expecting .class file but instead ...."; this shouldn't affect your run (except when debugging, maybe). 
This is an issue of Play being not well-compatible with querybeans, and currently there are no known solutions to fix this.

## Using Type-Safe Queries

To retrieve a Db Object, say a user, by their `teamId`, use the corresponding QDb class: 

	  DbUser dbUser = new QDbUser().teamId.eq(2).findUnique();

If you want to retrieve a list of users, do the same thing, but instead of `findUnique()`, do `findList()` at the end. This returns a `List<DbUser>`.

Queries can also do conjunction/disjunction, using `or()`/`and()`. For example, "find users with teamId 2 and last name that are Swanson or Fung":

	  List<DbUser> list = new QDbUser().teamId.eq(2).and().lastName.eq("Swanson").or().lastName.eq("Fung").findList();

- Further instruction can be found in [this video](https://www.youtube.com/watch?v=Kp528zWlbqU&feature=youtu.be) and at the [official docs](http://ebean-orm.github.io/docs/query/typesafe).

                                                                                                                                              

