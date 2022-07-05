import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.connect(("127.0.0.1", 20213))
msg = b"This is a test from python client"
s.send(msg)

while True:
    message, address = s.recvfrom(1024)
    clientMsg = f"{message}"
    print(message.decode('utf-8', clientMsg))
    quit()
