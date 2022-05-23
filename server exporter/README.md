# Server Exporter使用說明
## Dependency
[**Prometheus Python Client**](https://github.com/prometheus/client_python): Prmometheus官方提供的library

安裝方式
```
pip install prometheus-client
```

## 使用說明
```python 
external_web = "tw.stock.yahoo.com" # 目標外部網站的domain name or ip
my_web = ["127.0.0.1", 5500] # 自己架站所使用的 ip and port
exporter_port = 8000 # exporter 使用的 port
sleep_interval = 1 # 發送export的間隔時間, 單位為秒
```

設定完畢後，啟動程式並透過 Prometheus監聽 exporter的 port(以上面的code為例, 8000)

Prometheus會有*my_web_state*和 *external_web_state*兩個 metrices

### my_web_state
有*success* 和 *fail*兩種狀態。

當自己的網站存活時 success值為1， fail值為0

當自己的網站死亡時 success值為0， fail值為1

### external_web_state
有*success* 和 *fail*兩種狀態。

當外部的網站存活時 success值為1， fail值為0

當外部的網站死亡時 success值為0， fail值為1

## Example
### 情境: 自己的網站死亡
```python
my_web = ["127.0.0.1", 5500] #5000 port未開啟
```
*in Prometheus*
```
my_web_state{my_web_state="success"} 0.0
my_web_state{my_web_state="fail"} 1.0
```
### 情境: 自己的網站存活
```python
my_web = ["127.0.0.1", 5500] #5000 port已開啟
```
*in Prometheus*
```
my_web_state{my_web_state="success"} 1.0
my_web_state{my_web_state="fail"} 0.0
```
### 情境: 外部網站死亡
```python
external_web = "never.exist.website"
```
*in Prometheus*
```
external_web_state{external_web_state="success"} 0.0
external_web_state{external_web_state="fail"} 1.0
```
### 情境: 外部網站存活
```python
external_web = "google.com.tw"
```
*in Prometheus*
```
external_web_state{external_web_state="success"} 1.0
external_web_state{external_web_state="fail"} 0.0
```
