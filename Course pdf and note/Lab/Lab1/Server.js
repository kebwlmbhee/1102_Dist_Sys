const dgram = require('dgram')
const server = dgram.createSocket('udp4')

server.on('error', (err) =>{
    console.log(`server error:\n${err.stack}`);
    server.close();
})

server.on('message', (msg, rinfo) =>{
    console.log(`${msg}`);
    let send_port = `${rinfo.port}`;
    let send_msg = send_port.concat(":", msg);
    server.send(send_msg,send_port,'localhost',function(err){
        if(err){
            client.close();
        }else{
           server.close()
        }
    });
})

server.on('listening', () => {
    const address = server.address();
    console.log(`server listening ${address.address}:${address.port}`);
  });

server.bind(20213,'127.0.0.1')