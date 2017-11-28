'use strict';

const qaSystem = require('./QASystem');

const content = retrieveContent();

function retrieveContent() {
	return 'test_message("From Node.js with love - demo 3")';
}

qaSystem.sendMessage('test_msg', 'dispatch', 'custom_js_job', 'receiver', content, '1', {port: 8000});