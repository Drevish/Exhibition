# Exhibition
<h3>Exhibition service</h3>
Task:<br/>
Exhibition service system. There is a list of <b>showrooms</b> and every showroom has a list of <b>exhibits</b>.
<b>User</b> buys <b>tickets</b> via <b>payments</b> after choosing the <b>exhibition theme</b>.

<br/>
<br/>
<h3>Setup</h3>
<ul>
<li>JDK 1.8 or higher</li>
<li>Apache Maven 3.6.1 or higher</li>
<li>PostreSQL 11.1 or higher</li>
<li>Apache Tomcat 8.5.40 or higher</li>
</ul>
<br/>

<h3>Installation</h3>
<ul>
<li>Download project from GitHub</li>
<li>Unpack .zip</li>
<li>Specify values of "username" and "password" keys in \src\main\java\com\drevish\model\repository\DBCPDataSource.java</li>
<li>Start PostgreSQL server and execute script init.sql from \src\main\resources to init database</li>
<li>Cd to root project folder and execute command mvn clean install</li>
<li>Copy artifact exhibition-1.0-SNAPSHOT from target folder to %TOMCAT%\webapps</li>
</ul>
<br/>
