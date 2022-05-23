from prometheus_client import start_http_server, Enum
import time
import platform
import socket
import subprocess

DEBUG = True
external_web = "tw.stock.yahoo.com" # 目標server的domain name or ip
my_web = ["127.0.0.1", 5500] # my web server的 ip and port
exporter_port = 8000 # exporter 使用的port
sleep_interval = 1 # seconds

my_web_state = Enum("my_web_state", "Is my web alive", states=["success", "fail"])
external_web_state = Enum("external_web_state", "Is the external web alive", states=["success", "fail"])
system = "windows" if platform.system().lower()=='windows' else "others"

# 透過ping指令測試與目標外部網站的連線
def export_external_web_state(hostname):
    cmd = ["ping", "-n" if system == "windows" else "-c", "1", hostname]
    try:
        response = subprocess.check_output(
            cmd,
            stderr=subprocess.STDOUT,  # get all output
            universal_newlines=True  # return string not bytes
        )
    except subprocess.CalledProcessError:
        response = None

    if response != None:
        now = "success"
    else:
        now = "fail"
    if DEBUG:
        print("External web state: ", now)
    external_web_state.state(now)

# 透過socket建立與自己的網站的連線
def export_my_web_state(ip, port):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.settimeout(0.5)
        result = sock.connect_ex((ip,port))
    if result == 0:
        now = "success"
    else:
        now = "fail"

    if DEBUG:
        print("My web state: ", now)
    my_web_state.state(now)

if __name__ == '__main__':
    # Start up the server to expose the metrics.
    start_http_server(exporter_port)
    # Generate some requests.
    while True:
        export_external_web_state(external_web) 
        export_my_web_state(*my_web)
        time.sleep(sleep_interval)