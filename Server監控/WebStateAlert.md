# Web States Alert使用說明
## Alert Manager 使用說明
- 撰寫alertmanager所需要的設定檔，[詳細設定](https://prometheus.io/docs/alerting/latest/configuration/)

*alertmanager.yml*
```yml
route:
  group_by: ['alertname'] # 警訊分組
  group_wait: 10s
  group_interval: 10s
  repeat_interval: 1h
  receiver: "gmail-notifications" 
receivers: # 警告方式，這裡採用email通知
  - name: "gmail-notifications" 
    email_configs: 
      - to: receiver@emxample.com # 收信的郵件地址
        from: sender@example # 送信的郵件地址，可與收信地址相同
        smarthost: smtp.gmail.com:587 # 使用的SMTP host
        auth_username: sender@example # 送信帳號的username
        auth_identity: sender@example # 送信帳號ID
        auth_password: SenderPassword # 送信帳號的密碼
        send_resolved: true
```
- 使用命令列開啟alertmanager並傳入寫好的設定黨和使用port(例如9093)
```
./alertmanager.exe --config.file=alertmanager.yml --culuster.advertise0address=127.0.0.1:9093
```
## WebStateAlert使用方式
這部分已經寫好了，可直接下載使用或根據需求自行更改

*WebStateAlert.yml*
```yml
groups:
- name: Test
  rules:
  - alert: Test_for_My_Web_No_Request # 警告名稱
    expr: my_web_state{my_web_state = "fail"} == 1 # 警告規則
    for: 1m # 警告規則符合多久要發送警告
    labels:
      severity: page
    annotations:
      summary: My Web Test
  - alert: Test_for_External_No_Request # 警告名稱
    expr: my_web_state{external_web_state = "fail"} == 1 # 警告規則
    for: 1m # 警告規則符合多久要發送警告
    labels:
      severity: page
    annotations:
      summary: External Web Test
```
## Prometheus 設定說明
- 在Prometheus的設定中加入以下內容

*prometheus.yml*
```yml
# Alertmanagerg 相關設定
alerting:
  alertmanagers:
    - static_configs:
        - targets:
          - localhost:9093
 # 加入rule檔案
rule_files:
  - "path/to/WebStateAlert.yml"
```