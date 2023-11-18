
# 느낀 점 정리

## 설계

### REST API 설계

URI

## AWS ElastiCache(REDIS)

AWS에서 REDIS 서비스를 이용하기 위해서 AWS ElastiCache 서비스를 사용했다.  
본 프로젝트는 AWS ElastiCache 서비스를 이용하는 것으로 목적으로 하지만 혹시나 비용이 발생할 수도 있으니 Docker로 REDIS 서비스를 올려서 연동하는 것도 좋을 것 같다.

## AWS DynamoDB

AWS DynamoDB 서비스에 붙여서 사용하기 위해서는 다음 두 가지를 해야한다.  
1) DynamoDB 테이블 생성    
   접근할 테이블 (`@DynamoDBTable`)이 미리 준비가 되어 있어야한다.

2) Credential IAM 접근 권한 설정  
   IAM `AmazonDynamoDBFullAccess` 정책을 설정한다.

RDB 데이터 모델링과는 다르게 데이터에 어떤 목적으로 접근할 건지 먼저 파악한 후에 데이터 모델링을 진행해야 한다.  
PK(파티션키), SK(정렬키)와 문자열 패턴을 활용해서 데이터 접근 방식을 정의할 수 있다.

| Primary Key                 |                        |Attributes|                  |                       |            |               |               |  
|-----------------------------|------------------------|---|------------------|-----------------------|------------|---------------|---------------|  
| **PK(Partition Key)**       | **SK(Sort Key)**       |**openDate**| **deadlineDate** | **limitedQuantityOf** | **status** | **createdAt** | **updatedAt** |
| subject#{subjectId1}        | info                   | 2023-12-31T12:24 | 2024-01-31T12:24 | 100 | ONGOING | 2023-11-18T22:30:45.292739 | 2023-11-18T22:30:45.292739 |

| Primary Key                 |                        | Attributes       |  
|-----------------------------|------------------------|------------------|  
| **PK(Partition Key)**       | **SK(Sort Key)**       | **createdAt**    |
| subject#{subjectId1}#ticket | client#{clientId1} | 2023-12-31T12:24 |

Junit 테스트를 위해서 dynamoDB 호출은 docker에서 `amazon/dynamodb-local` 컨테이너를 올려 환경을 구성한다.

[🔍 참고자료]  
[AWS 개발자 가이드-데이터 모델 스키마 디자인](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/data-modeling-schema-social-network.html)

## JAVA

1) **`record` 키워드**  
   record 키워드를 사용한 객체를 생성할 때 객체는 상속을 할 수 없는 `final class`로 만들어진다.  
   모든 필드는 `private final` 필드로 정의된다.  
   모든 필드를 초기화하는 `RequiredArgsConstructor` 생성자가 생성된다.  
   각 필드에 해당하는 `getter` 메서드가 생성되는데 필드명으로 접근할 수 있다.

---

### 실제로 REDIS Lock, 등록/조회 비즈니스 로직 테스트는 어떻게 진행해야 할까.

[🔍 참고자료]  
[여러 가지 REDIS 테스트 시나리오의 단점 소개, 대안 TestContainer 사용 방법 포스트](https://loosie.tistory.com/813)