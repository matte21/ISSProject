A QActor sends JavaScript code to a Node.js server over HTTP. The server executes the code (which
contains the dispatch of a message back to the QActor). The server life cycle is independent from that
of the QActor system. The code sent to the server simulates the asynchronous acquisition of a temperature 
measurement (but it's just an example to prove the point, it could be anything).

To run the demo, first launch startServer.sh. Then launch the jar file.
