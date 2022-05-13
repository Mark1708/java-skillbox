# Устанавливаем image
```
docker pull harisekhon/hadoop
```
# Запускаем контейнер с указанными портами
```
docker run -ti -p 4040:4040 -p 8020:8020 -p 8032:8032 -p 8088:8088 -p 9000:9000 -p 10020:10020 -p 19888:19888 -p 50010:50010 -p 50020:50020 -p 50070:50070 -p 50075:50075 -p 50090:50090 harisekhon/hadoop
```
# Настройка name node
```
sudo vi /etc/hosts
0.0.0.0     b384d8e14b70
```
# Подключение к консоли hadoop
```
docker exec -it b384d8e14b70 /bin/bash
```
# Установка Apache
```
yum install wget
wget --no-check-certificate https://dlcdn.apache.org/spark/spark-3.0.3/spark-3.0.3-bin-hadoop2.7.tgz
tar -xvf spark-3.0.3-bin-hadoop2.7.tgz
rm spark-3.0.3-bin-hadoop2.7.tgz
mv spark-3.0.3-bin-hadoop2.7/ spark
chown -R root:root ./spark/
ll
```
# Копируем jar в docker container
```
docker cp SparkExample.jar 9e69ad9694b5:/spark/
```
# Запуск jar
```
cd spark
./bin/spark-submit --deploy-mode client --class Main SparkExample.jar
./bin/spark-submit --deploy-mode client --class Main SparkExample.jar hdfs://9e69ad9694b5:8020/test/file.txt /result

```
