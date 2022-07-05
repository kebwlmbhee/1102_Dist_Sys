const soap = require('soap');
const url = 'http://localhost:8192/AddMul?wsdl';
var args = {x: 10, y: 20};

soap.createClient(url, function (err, client) {
    if (err) console.log(err);
    //console.log(client.describe());
    client.multiply(args, function (err, result,  rawResponse, soapHeader, rawRequest) {
        if (err) console.log(err);
        console.log(rawRequest);
        console.log('');
        console.log(rawResponse);
    });
});