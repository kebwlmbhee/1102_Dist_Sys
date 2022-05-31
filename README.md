# 第八組 Prometheus

## Outline
  - [基本架構圖](#基本架構圖)
  - [功能性和非功能性](#功能性和非功能性)
  - [節點功能](#節點功能)
  - [操作步驟](#操作步驟)
  - [影片、PPT連結 <a name="special-title"></a>](#影片ppt連結-)
  - [Grafana Graph](#Grafana-Graph)

## 基本架構圖
![architecture picture](architecture.jpg)
## 功能性和非功能性

## 節點功能

## 操作步驟

### [Server_Exporter](server%20exporter/README.md)

### [Web States Alert](Server監控/README.md)

### [MySQL & MySQL_Exporter & Grafana](https://docs.google.com/document/d/1xd6boe9BuJ7gB__l12_fa_s02MIc0rkNlu8X-ZMbxik/edit)

## 影片、PPT連結 <a name="special-title"></a>

### [影片]()

### [PPT](https://docs.google.com/presentation/d/1tQe05gncTl-CJvk5dt6QzU1edvb3t6xSlCecpbbTogA/edit?usp=sharing)

## Grafana Graph

![Grafana png](Grafana.png)

| 指標 | 名稱 | 說明 
|------|------|-----|
| MySQL Status | MySQL 狀態 | UP or DOWN |
| MySQL Uptime | 運行時長 | MySQL服務器自從上次重啟運行到現在的時長 |
| Current QPS | 每秒查詢速率 | 根據使用MySQL的SHOW STATUS命令查詢到的結果.它是服務器在晟後一秒內執行的語句數星，這個變量包含在存儲程式中執行的語句.與Questions變量不同 |
| InnoDB Buffer Pool Size | InnoDB Buffer 快取池 | MySQL服務器自從上次重啟運行到現在的時長 |
| MySQL Status Graph | MySQL 狀態圖 | for Alert |
| MySQL Connections | MySQL 連接數 | MySQL 服務器自從上次重啟運行到現在的最大連接數 |
| MySQL Client Thread Activity | Client Thread 數量 | 未休眠的 Thread 數量 |
| MySQL Questions | Server 執行的語句 | 與 QPS 不同，這裡只包括 Client 到 Server 的 Question，不包含 Storage process 的 Question |
| MySQL Thread Cache | Thread 快取 | Clinet 斷線時，快取未滿，則 Client 的 Thread 會被放入快取 |
| MySQL Temporary Objects | MySQL 的 Temporary Table | 加速查詢用 |
| MySQL Select Types | 未使用 index 查詢的 counter | 有多少查詢導致了全部 table 都要掃描 |
| MySQL Aborted Connections | 終止的連接數 | 主機在連接中被中斷時，MySQL 會將其資料保存在系統表中 |
| MySQL Table Locks | 使用 Lock 的數量 | MySQL 會因為各種原因需要用到 Lock |
| MySQL NetWork Traffic | 網路流量 | InBound 是收到的網路流量 |
| MySQL Network Usage Hourly | 網路使用量/小時 | -- |
| MySQL Internal Memory Overview | 內存概覽 | -- |
| Top Command Counters | 每個 xxx 語句已執行的次數 | 每種類型的語句都有一個狀態變量, e.g. Com_delete 和 Com_update 計數 |
| Top Command Counters Hourly | 每小時最高命令計數器 | -- |
| MySQL Handlers | 關於 MySQL 如何選擇、更新、插入和修改行、表和索引的內部統計 | 當服務器執行全表掃描時，`read_rnd_next` 會增加；當使用索引完成讀取時，`read_key` 會增加；當存儲引擎讀取下一個索引條目時，`read_next` 會增加。高值意味著正在執行大量索引掃描。|
| MySQL Query Cache Memory | 只有一個線程可以同時在查詢緩存中進行操作。這種序列化不僅適用於 SELECT，也適用於 INSERT/UPDATE/DELETE | `query_cache_size` 設置的越大，這些操作就越慢 |
| MySQL Slow Queries | 比 long_query_time 設置慢的查詢 | -- |
| MySQL File Openings | 當前打開 file 的計數器 | -- |
| MySQL Open Files | 自上次重新啟動 MySQL 以來打開 file 的計數器 | -- |
| MySQL Table Open Cache Status | 打開 Table 的快取狀態 | -- |
| MySQL Open Tables | 打開 Table 的數量 | -- |
| MySQL Table Definition Cache | 所有 Table 快取 | -- |
