# 선착순 서비스 - API Server

> **🥇 first come, first served.**  
> REDIS, SQS, NoSQL을 사용해보기 위해 본 프로젝트를 기획했습니다.  
> 부족한 점이 많은데 객관적인 지적, 조언 해주시면 감사하겠습니다.🙂 

수량이 제한된 어떠한 대상이 있고 사용자들은 대상의 사용 권한을 얻기 위해 획득 요청을 한다.  
많은 사용자들이 제한된 대상 사용 권한을 갖기 위해 발생하는 다수의 요청을 견딜 수 있는 서버를 구축해보려고 한다.

## 기술적 목표

### Java

### Redis

API 서버 단에서 트래픽을 빠르고 효율적으로 처리하기 위해 Redis를 사용하기로 결정했다.   
메모리 기반로 빠르게 데이터를 읽올 수 있는 Key-Value Store DB인 Redis를 경험해본다.

### SQS

API 서버의 부하를 줄이고 요청된 순서를 보장받기 위해 AWS SQS를 사용하기로 결정했다.

### DynamoDB

Serverless, Fully managed 환경인 NoSQL DB를 학습해보기 위해 AWS DynamoDB를 사용하기로 결정했다.

---

## Structure

![structure.png](./docs/assets/structure.png)

---

## 애플리케이션 작동 방식

### 대상(SUBJECT)

한정 수량의 Ticket을 설정하기 위해 대상(SUBJECT)를 등록해야 한다.  
애플리케이션이 `AWS DynamoDB`에 **Write**하는 작업은 SUBJECT를 등록하는 케이스만 허용한다.

### 티켓(TICKET)

이번 프로젝트의 목표는 순간적인 트래픽을 대응할 수 있는 애플리케이션을 구축하는 것이다.  
티켓(TICKET) 획득 요청에서 순간적인 트래픽을 발생한다는 것을 가정하고 애플리케이션을 구축했다.  
대량의 요청은 1차로 **Redis**에서 필터링을 한다. Redis에서 제한된 수량을 초과하는 요청인 경우를 바로 파악하고 Response를 한다.  
정상적인 요청은 **Redis → SQS → Worker** 흐름으로 요청이 흐른다.  
Ticket 데이터는 `AWS DynamoDB`에서 **Read** 작업만 한다. **Write** 작업은 **Consumer Worker**에서 진행한다.

---

## 도메인

예약할 대상을 의미하는 **Subject**  
예약하는 주체를 의미하는 **Client**  
대상에 주체가 예약했음을 의미하는 **Ticket**

### 대상(Subject)

최대한 심플한 정보만 갖는다.  
상세한 정보는 호출하는 외부 시스템에서 관리하도록 유도한다.  
시스템에 등록된 주제 정보는 **DB**로 관리한다.

| 속성(영어)              | 속성(한글)       | 설명                 |
|---------------------|--------------|--------------------|
| ID                  | 식별 값         | 어떤 주제를 특정하는 식별 값   |
| OPEN_DATE           | 오픈날짜         | 예약 오픈 날짜           |
| DEADLINE_DATE       | 마감날짜         | 예약 마감 날짜           |
| LIMITED_QUANTITY_OF | 한정수량         | 한정 수량 정보           |
| STATUS              | 데이터 상태       | [진행전, 진행중, 취소, 종료] |
| ❔ SYSTEM_ID         | ❔ 등록한 시스템 정보 | ❔ * 고려하지 않음 *      |

### 주체(Client)

예약하는 주체에 대한 정보는 보통 사용자 정보일 것이다.  
사용자 정보 역시 외부 시스템에서 관리하도록 유도한다.
따라서 본 시스템에서는 주체에 대한 정보는 **DB**로 관리하지 않는다.

### 입장권(Ticket)

주체(Client)가 특정 대상(Subject)에 대해 소유권을 갖게 되는 경우 입장권을 갖는다라는 의미로 **Ticket**으로 명명했다.  
입장권에 대한 정보는 시스템에서 **DB**에 저장 관리한다.

| 속성(영어)     | 속성(한글) | 설명                |
|------------|--------|-------------------|
| CLIENT_ID  | 소유권자   | 입장권을 소유한 대상 |
| SUBJECT_ID | 주제     | 어디(주제) 입장권        |
| CREATED_AT | 소유한 날짜 | 소유권을 얻은 날짜        |

📌 주체(Client)는 특정 주제(Subject)에 대한 입장권(Ticket)을 **하나**만 소유할 수 있다.  
📌 등록한 주제(Subject) 정보는 수정할 수 없다. 필요하다면 새로 등록해서 사용하도록 한다.

___

## DB 모델링

| Primary Key                 |                        |Attributes|                  |                       |            |               |               |  
|-----------------------------|------------------------|---|------------------|-----------------------|------------|---------------|---------------|  
| **PK(Partition Key)**       | **SK(Sort Key)**       |**openDate**| **deadlineDate** | **limitedQuantityOf** | **status** | **createdAt** | **updatedAt** |
| subject#{subjectId1}        | info                   | 2023-12-31T12:24 | 2024-01-31T12:24 | 100 | ONGOING | 2023-11-18T22:30:45.292739 | 2023-11-18T22:30:45.292739 |

| Primary Key                 |                        | Attributes       |  
|-----------------------------|------------------------|------------------|  
| **PK(Partition Key)**       | **SK(Sort Key)**       | **createdAt**    |
| subject#{subjectId1}#ticket | client#{clientId1} | 2023-12-31T12:24 |

---

📎 [시나리오를 정리한다.](./docs/scenario.md)    
📎 [REST API 문서](./docs/rest-api.md)

---

#### ☕ 도움을 주신 분

🤝 **김지황 님**  
[Redis&Kafka를 활용한 선착순 쿠폰 이벤트 개발기 (feat. 네고왕)](https://techblog.gccompany.co.kr/redis-kafka%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%84%A0%EC%B0%A9%EC%88%9C-%EC%BF%A0%ED%8F%B0-%EC%9D%B4%EB%B2%A4%ED%8A%B8-%EA%B0%9C%EB%B0%9C%EA%B8%B0-feat-%EB%84%A4%EA%B3%A0%EC%99%95-ec6682e39731)  
선착순 시스템 설계를 고민하다 김지황 님이 쓰신 위의 글을 보게 되었고 궁금한 부분이 메일을 보냈는데 작년에 쓴 글이었음에도 불구하고 질문에 대해 상세하게 피드백을 해주셔서 프로젝트를 진행하는 데에 많은 도움이 되었습니다.