'use strict';

const net = require('net');

function sendMessage(msgId, msgType, sender, receiver, content, seqNum, destIPandPort) {
	const client = net.createConnection(destIPandPort, () => {
		const msg = 'msg( ' +  msgId  + ', ' + msgType + ', ' + sender + ', ' +  receiver + ', ' + content + ', ' + seqNum + ' )';
		client.end(msg);
	});
}

exports.sendMessage = sendMessage;