# datafoundry_ocdp_hive_demo

Demo about how to use datafoundry ocdp hive service.

## Build
- git clone https://github.com/asiainfoLDP/datafoundry_ocdp_hive_demo.git

- cd datafoundry_ocdp_hive_demo

- mvn clean package

## Usage

- Run kinit command to get ticket (kerberos principal/password can get from service binding env vars.)
- Run code example by java:
(Command need 6 parameters: hive host, hive port, hive user principal, hive sql, sql type (execute|query), hive sql)

Command like below:
```
java -jar target/df_ocdp_hive_demo-1.0-SNAPSHOT-jar-with-dependencies.jar hadoop-2.jcloud.local 10000 7cc4a1d4a1b711e6b7a3fa163d0e0615 hive/hadoop-2.jcloud.local@ASIAINFO.COM query 'show databases'
```