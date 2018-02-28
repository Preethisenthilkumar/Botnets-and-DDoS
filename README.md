# Botnets and Distributed Denial of Service Attacks

Master Bot commands the Slave Bot to perform some operations

COMMANDS:

1.-connect (IPAddressOrHostNameOfYourSlave|all) (TargetHostName|IPAddress) TargetPortNumber [NumberOfConnections: 1 if not specified] [keepalive]
When the keepalive option is given, your client should select that socket option while creating the related connection. 

2.- connect (IPAddressOrHostNameOfYourSlave|all) (TargetHostName|IPAddress) TargetPortNumber [NumberOfConnections: 1 if not specified] [url=path-to-be-provided-to-web-server]
The slave will generate a HTTP request equivalent to: https://www.google.com/#q=YourRandomString
The length of the string being added to the should also be generated at random between 1 char and 10 chars.

3.-list
Will list all current slaves with the following format:
SlaveHostName IPAddress SourcePortNumber RegistrationDate

4.-rise-fake-url
Parameters: url and port number
Bots will behave like web servers and serve a virtual html page that will link two other pages each containing fake urls

5.-down-fake-url
the websites will be brought down

6.-disconnect (IPAddressOrHostNameOfYourSlave|all) (TargetHostName|IPAddress) [TargetPort:all if no port specified]
Close a number of connections to a given host

Master will take the following command line argument:
-p PortNumber

Slave will take two arguments:
-h IPAddress|Hostname (of Master -p port where master is listening for connections
