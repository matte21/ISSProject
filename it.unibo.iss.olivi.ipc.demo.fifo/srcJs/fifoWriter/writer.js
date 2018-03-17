'use strict';

const fs = require('fs');

var msg 	= 'test message';
var counter = 0;

fs.open('fifoDir/fifo', 'w', (err, fd) => {
	if (err) {
		console.log('Error opening the file: ', err);
	} else {
		writeAndSetNextWrite(fd);
	}
});

function writeAndSetNextWrite(fd) {
	fs.write(fd, msg, function(err) {
		counter++;
		if (err) {
			console.log('Error while writing: ', err);
		} else if (counter < 3) {
			setTimeout(() => {
				writeAndSetNextWrite(fd);
			}, 1000);
		} else {
			fs.close(fd, (err) => { 
					if (err) {
						console.log();
					}
				});
		}
	});
}
