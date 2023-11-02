## 서비스 목표

### 빠른 응답

선착순 시스템이기 때문에 순간적으로 많은 트래픽이 발생할 수 있다.  

**Ticket** 정보가 최종으로 저장되기 위해서는 **SQS Consumer Worker**에 도달해야 한다.  
- "api server → sqs → sqs consumer worker"

**api server**에서 **REDIS**를 활용하여 주제(Subject)에 대해 제한 수량이 초과되는 경우 나머지 요청을 즉시 응답(409)한다.

## 사용할 Http Status Code

| 코드  | 의미                    | 사용 목적                                |
|-----|-----------------------|--------------------------------------|
| 200 | OK                    | 요청을 성공적으로 처리                         |
| 201 | Created               | 새로운 리소스 성공적으로 생성 처리                  |
| 400 | Bad Request           | 잘못된 요청으로 인해 서버에서 요청을 이해할 수 없음        |
| 409 | Conflict              | 요청 정보는 정상적이지만 내부 정책에 의해 실패           |
| 422 | Unprocessable Entity  | 요청 정보는 정상적이지만 내부 정책에 의해 실패, 변경 후 재시도 |
| 500 | Internal Server Error | 서버 에러                                |