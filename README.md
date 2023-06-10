# Getting Started

This project uses JDK Version 11 You need to insall JRE Version 11 before hand.
You can use pre-build JAR archives which are inside `JARs` directory.

## Server Setup

Pre-built server uses port 8000 as its binding port. If you want to change the port open `src/com/cyberfreak/file_access/server/Server.java` file, and change the port number in line 32. To rebuild the server you can use this command in Linux terminal:

```bash
# Inside the src/com/cyberfreak/file_access/server direcotry type
javac -d {{path/to/directory/of/output}} *.java

# To create a JAR file from the .class files
# Change you directory to your .class files directory {{path/to/directory/of/output}}
jar cf {{file_name_of_the_jar.jar}} *

# To run the jar file
java -jar {{filename.jar}}
```

## Client Setup

For the client you can use the pre-built JAR archive which is inside the `JARs` directory. To run the JAR file type

```bash
java -jar Client.jar
```

### Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `JARs`: the folder to maintain built archives

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

### Project Maintainer

If you want to see the all source codes you can follow this GitHub repository link <https://github.com/CyberFreak1911/CMPE411-Concurrent-File-Access>
