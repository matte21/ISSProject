const net = require('net');

function sendMsg(id, type, sender, receiver, content, seqNum) {
	const client = net.createConnection(8000, () => {
		const msg = 'msg( ' +  id  + ', ' + type + ', ' + sender + ', ' +  receiver + ', ' + content + ', ' + seqNum + ' )';
		client.end(msg);
	});
}

function getTemp(callback) {
	const temp = 23;
	callback(temp);
}

// Callback to send the result back to the client
function sendTempBack(temp) {
	sendMsg('result', 'dispatch', 'remote_worker', 'client', 'result(' + temp + ')', '1');
}

// Use setTimeout to mimic an asynchronous request to an external service.
setTimeout(() => {
	getTemp(sendTempBack);
}, 5000);

