'use strict';

const net = require('net');


const socketPath = '.uds.sock';

/*
* Numeric ID (range [0,100)) of the sender. Notice that it is possible that multiple senders 
* have the same numeric ID, albeit it's statistically very unlikely
*/
const nbrID = Math.floor(Math.random()*100);

/*
* Message written to the file (Unix Domain Socket). Notice that msg embeds the numeric ID of 
* the sender (otherwise all the senders would send the same message). This way, we can start n senders and have the 
* user realize that messages from different senders are from different senders. Of course, since the file is a
* Unix Domain Socket, the server doesn't need the numeric ID to realize that two messages from different senders 
* are such, but since it then displays the received messages to standard output, we (users) need that. 
* Also, notice that there are way better methods to achieve this, but this project is only a quick and dirty proof 
* of concept, hence this hack.
*/
const msg = 'from_sender_' + nbrID;

var counter = 0;

const clientSock = net.createConnection(socketPath, (err) => {
	if (err) {
		console.log('Failed to connect to the server');
	} else {
		console.log('Sender nbr. ' + nbrID + ' about to write stuff');
		writeAndSetNextWrite(clientSock);
	}
});

function writeAndSetNextWrite(sock) {
	sock.write(msg, (err) => {
		counter++;
		if (err) {
			sock.end();
			console.log('Error while writing: ', err);
		} else if (counter < 3) {
			setTimeout(() => {
				writeAndSetNextWrite(sock);
			}, 1000);
		} else {
			sock.end();
			console.log('Sender nbr. ' + nbrID + ' is terminating');
		}
	});
}

