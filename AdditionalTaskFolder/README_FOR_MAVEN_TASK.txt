
Detailed note with screenshots here:
http://www.evernote.com/l/AOF9lwrFO75Fi4q2HLgFvQNu2ubQ2R0Df0k/


1. We should add Tomcat user to tomcat-users.xml to have possibility
get to Tomcat Manager App and deploy our project to Tomcat using Maven
(from Studio or commanl line):
--------------------------------------------------------------------------
 <role rolename="manager-gui"/>
 <role rolename="manager-script"/>
 <user username="admin" password="admin" roles="manager-gui,manager-script"/>
--------------------------------------------------------------------------


2. Add described user to m2 settings and put this file to m2 root (m2/settings.xml)
--------------------------------------------------------------------------
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
      <localRepository/>
      <interactiveMode/>
      <usePluginRegistry/>
      <offline/>
      <pluginGroups/>
      <servers>
		<server>
            <id>tomcat7</id>
            <username>admin</username>
            <password>admin</password>
        </server>
	  </servers>
      <mirrors/>
      <proxies/>
      <profiles/>
      <activeProfiles/>
</settings>
--------------------------------------------------------------------------

3. Add nessesary plugins to build section in a POM (tomcat7-maven-plugin, maven-compiler-plugin, maven-war-plugin)
and start deploy.

--------------------------------------------------------------------------
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
                <url>http://localhost:9080/manager/text</url>
                <server>tomcat7</server>
                <path>/personService</path>
            </configuration>
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.3.2</version>
            <configuration>
                <source>1.6</source>
                <target>1.6</target>
                <compilerArguments>
                    <endorseddirs>${endorsed.dir}</endorseddirs>
                </compilerArguments>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>2.1.1</version>
            <configuration>
                <failOnMissingWebXml>false</failOnMissingWebXml>
            </configuration>
        </plugin>

    </plugins>
</build>
--------------------------------------------------------------------------
