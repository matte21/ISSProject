This is a "quick and dirty" toy project to experiment with IPC communication based on FIFOs between software written in Java and JavaScript (Node.js)

To build it, just run "gradle build" in the root directory. It is an Eclipse project, which means that it can be opened from Eclipse and built from there.

To run it:
1) Execute "gradle build" from the command line (if you are using eclipse, run "gradle eclipse" first).
2) Take the tar/zip file generated in the build process and extract it. 
3) Open a terminal and go to the directory it.unibo.iss.olivi.ipc.demo.fifo.FifoReader.
4) Execute the command "./buildFifo.sh"
5) Open another terminal window while staying in the same directory
6) On one window, execute the command "java -jar it.unibo.iss.olivi.ipc.demo.fifo.jar"
7) On the other window, execute the command "node srcJs/fifoWriter/writer.js"

Notes:
1) Currently it is only possible to execute it under Linux/OSx, because some utility scripts provided 
(e.g. buildFifo.sh) are present only for Linux/OSx.

2) Execution requires to have Node.js installed on the machine. The writer.js script has been written using Node.js v9.8.0 
