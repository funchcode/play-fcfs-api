# 선착순 서비스 - API Server

> **🥇 first come, first served.**  
> REDIS, SQS, NoSQL을 사용해보기 위해 본 프로젝트를 기획했습니다.  
> 부족한 점이 많은데 객관적인 지적, 조언 해주시면 감사하겠습니다.🙂 

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
*본 레파지토리 API Server에서는 Kotlin을 사용하지 않는다.

---

📎 [도메인을 정리한다.](./docs/domain-entity.md)  

📎 [시나리오를 정리한다.](./docs/scenario.md)  

---

#### ☕ 도움을 주신 분

🤝 **김지황 님**  
[Redis&Kafka를 활용한 선착순 쿠폰 이벤트 개발기 (feat. 네고왕)](https://techblog.gccompany.co.kr/redis-kafka%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%84%A0%EC%B0%A9%EC%88%9C-%EC%BF%A0%ED%8F%B0-%EC%9D%B4%EB%B2%A4%ED%8A%B8-%EA%B0%9C%EB%B0%9C%EA%B8%B0-feat-%EB%84%A4%EA%B3%A0%EC%99%95-ec6682e39731)  
선착순 시스템 설계를 고민하다 김지황 님이 쓰신 위의 글을 보게 되었고 궁금한 부분이 메일을 보냈는데 작년에 쓴 글이었음에도 불구하고 질문에 대해 상세하게 피드백을 해주셔서 프로젝트를 진행하는 데에 많은 도움이 되었습니다.