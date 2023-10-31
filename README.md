# 선착순 서비스 - API Server

> 🥇 first come, first served.

수량이 제한된 어떠한 대상이 있고 사용자들은 대상의 사용 권한을 얻기 위해 획득 요청을 한다.  
많은 사용자들이 제한된 대상 사용 권한을 갖기 위해 발생하는 다수의 요청을 견딜 수 있는 서버를 구축해보려고 한다.

## 기술적 목표

### Java

사용해왔던 기술인 Spring MVC, JPA의 개념을 다시 한번 정리하기 위해 Java를 사용하기로 결정했다.

### Redis

API 서버 단에서 트래픽을 빠르고 효율적으로 처리하기 위해 Redis를 사용하기로 결정했다.   
메모리 기반로 빠르게 데이터를 읽올 수 있는 Key-Value Store DB인 Redis를 경험해본다.

### SQS

API 서버의 부하를 줄이고 요청된 순서를 보장받기 위해 AWS SQS를 사용하기로 결정했다.

### DynamoDB

Serverless, Fully managed 환경인 NoSQL DB를 학습해보기 위해 AWS DynamoDB를 사용하기로 결정했다.

### Kotlin

서버의 효율성 및 안정성을 위해 멀티 스레딩 기법을 이용하기로 결정했다.  
이번 프로젝트를 통해 Kotlin을 경험해본다.  
Kotlin에서 경량화된 스레드라고 할 수 있는 coroutine을 이용한다.  
*본 레파지토리 FCFS SQS Consumer에서는 Redis를 사용하지 않는다.

---
