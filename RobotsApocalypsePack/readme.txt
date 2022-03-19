Installation and Deployment Guide information
This section is provided to give information on the deployment of the Robots Apocalypse API. 

Database Creation
Steps
1.	Create the Database: robotsInversiondb in Microsoft SQL Server
2.	Run the script RobotsApocalypse_Table_Procedure_Script.sql to create tables and procedures 

Robots Apocalypse API Deployment
Steps
1.	Update the following in the config.properties file in the war file with your environment db data:

localdbserver=********
localdbport =****
localdbname=********
localdbuser=*******
localdbpwd=******
sqlinstance=******

2.	Install Application server: Glassfish, wildfly, tomcat etc
3.	Deploy the war file to the autodeploy (autodeploy exists in Glassfish) folder on the server.
4.	The application will automatically be deployed.
5.	Or follow your preferred application server deployment step to deploy the application
6.	Once deployment is completed, update the API base URL domain and port (http://localhost:8080/ ) to run any of the APIs


NB: war file - RobotsApocalypse.war can be found in the dist folder in the source code RobotsApocalypse.



