
### 실제로 REDIS Lock, 등록/조회 비즈니스 로직 테스트는 어떻게 진행해야 할까.


[🔍 참고자료]  
[여러 가지 REDIS 테스트 시나리오의 단점 소개, 대안 TestContainer 사용 방법 포스트](https://loosie.tistory.com/813)

### AWS ElastiCache(REDIS)

AWS에서 REDIS 서비스를 이용하기 위해서 AWS ElastiCache 서비스를 사용했다.  
본 프로젝트는 AWS ElastiCache 서비스를 이용하는 것으로 목적으로 하지만 혹시나 비용이 발생할 수도 있으니 Docker로 REDIS 서비스를 올려서 연동하는 것도 좋을 것 같다.

### AWS DynamoDB

AWS DynamoDB 서비스에 붙여서 사용하기 위해서는 다음 두 가지를 해야한다.  
1) DynamoDB 테이블 생성    
   접근할 테이블 (`@DynamoDBTable`)이 미리 준비가 되어 있어야한다.

2) Credential IAM 접근 권한 설정  
   IAM `AmazonDynamoDBFullAccess` 정책을 설정한다.

## JAVA

1) **`record` 키워드**  
   record 키워드를 사용한 객체를 생성할 때 객체는 상속을 할 수 없는 `final class`로 만들어진다.  
   모든 필드는 `private final` 필드로 정의된다.  
   모든 필드를 초기화하는 `RequiredArgsConstructor` 생성자가 생성된다.  
   각 필드에 해당하는 `getter` 메서드가 생성되는데 필드명으로 접근할 수 있다.