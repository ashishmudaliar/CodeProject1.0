# CodeProject1.0

CodeProject 1.0 developed using the <b>Spring Framework</b> with <b>jre 1.8</b> compiler as part of the Programming task.

<b>System requirements:-</b>

JRE compiler 1.8

If building and running :-

Apachen Maven

https://maven.apache.org/

<b>Javadoc</b>

The JavaDoc for the project can be found in the docs folder

<b>Running the Service </b>

Run the file <b>run_codeproject.bat</b> or <b>run.sh</b>. This file will execute the runnable JAR file <b>CodeProject1.0-0.0.1-SNAPSHOT</b>.The service will run on <b>http://localhost:8080</b>

<b> Access to the web service</b>

1.Adding items to the queue:

<b>URL</b>        : localhost:8080/addOrder

<b>Parameters</b> : customerId :- customer ID of the customer placing the order(0-20000)
 
 quantity :- amount of rubber ducks required in the order(1-25)

<b>Example</b> : localhost:8080/addOrder?customerId=629&quantity=7

2.Check position and waiting time:

<b>URL</b>        : localhost:8080/getWaitTime

<b>Parameters</b> : customerId :- customer ID of the customer (0-20000)
 
<b>Example</b> : localhost:8080/getWaitTime?customerId=1454

3.View the current list:

<b>URL</b>        : localhost:8080/getList

<b>Example</b> : localhost:8080/getList

4.Retrieve next delivery:

<b>URL</b>        : localhost:8080/getOrders

<b>Example</b> : localhost:8080/getOrders

5.Cancel an order:

<b>URL</b>        : localhost:8080/removeOrder

<b>Parameters</b> : customerId :- customer ID of the customer cancelling the order(0-20000)
 
<b>Example</b> : localhost:8080/removeOrder?customerId=629
    
