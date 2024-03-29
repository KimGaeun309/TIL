var http = require('http');
var fs = require('fs');
var url = require('url'); // url 이라는 모듈 사용

var app = http.createServer(function (request, response) {
  var _url = request.url;
  var queryData = url.parse(_url, true).query;

  if (_url == '/') {
    _url = '/index.html';
  }
  if (_url == '/favicon.ico') {
    //return response.writeHead(404);
    response.writeHead(404);
    response.end();
    return;
  }
  response.writeHead(200);
  //response.end(fs.readFileSync(__dirname + _url));
  response.end(queryData.id);
});
app.listen(3000);
