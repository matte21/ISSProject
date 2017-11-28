const net = require('net');

const client = net.createConnection(8000, () => {
	var msg = 'msg( test_msg, dispatch, localhost_' + client.localPort + ', receiver, test_msg("From Node.js with love - demo 1"), 1 )';
	client.end(msg);
});