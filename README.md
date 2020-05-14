bayes-dota
==========

This is the [task](TASK.md).


Additional Information or improvements - 

1) Could have designed the relational model better. At the moment there are no mappings between entities. 
This could have been time consuming hence have decided to keep the models as simple as possible.

2) Have assumed the file size of the logs to be not too big. If otherwise, I would have processed the 
file lines in parallel with multiple threads.

3) I have followed the same package structure as was in the scaffold project provided with addition of domain and strategy packages.
Domain holding the core of the project (entities) and strategy is serving different strategies for different event types.
The source code dependencies is from outside (controller) to inside(domain).

4) Have used in-memory H2 database to store the relational data. It could be accessed using [h2-local](**http://localhost:8080/h2-console/**)

5) Also it seems that the curl command replaces the '\n' character when -d is used to specify the data file name.
Please use the following command to POST the data to the ingest end point - 

***curl -H "Content-Type: text/plain" --data-binary @combatlog_1.log.txt http://localhost:8080/api/match***  