const http = require('http');

http.createServer(executeRemoteCode).listen(3000, function() {
	console.log("Server listening on port 3000");
});

function executeRemoteCode(req, res) {
    console.log('request received');
	
    var codeToExecute = '';
    req.on('data', function (data) {
        codeToExecute += data;
    });
    req.on('end', function () {
    	eval(codeToExecute);
    });
    res.writeHead(200, {'Content-Type': 'text/plain'});
    res.end('request received');
}