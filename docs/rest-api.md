## 공통

### 에러 코드

| 에러코드 | 에러 설명     |
|------|-----------|
| 5001 | 데이터 조회 에러 |
| 5100 | 요청 데이터 에러 |
| 1100 | 주제 정책 에러  |
| 1200 | 티켓 정책 에러  |

### 데이터 타입

**DATETIME object**  

| 파라미터 이름 | 필수 여부 | 데이터 타입          | 설명                          |
|---------|-------|-----------------|-----------------------------|
| year    | 필수    | number          | 연도 (요청날짜 기준 +1년까지 입력 허용)    |
| month   | 필수    | DATETIME object | 월 (1-12 입력 허용)              |
| day     | 필수    | DATETIME object | 일 (1-요청month의 범위 안까지 입력 허용) |
| hour    | 필수    | DATETIME object | 시 (0-23까지 입력 허용)            |
| minute  | 필수    | DATETIME object | 분 (0-59까지 입력 허용)            |

## 주제(Subject)

### 주제 등록

#### POST /v1/subjects

> Content-Type: application-json

**BODY**  

|파라미터 이름|필수 여부| 데이터 타입          | 설명       |
|---|---|-----------------|----------|
|limitedQuantityOf|필수| number          | 티켓 수량    |
|openDateTime|필수| DATETIME object | 예약 오픈 시간 |
|deadlineDateTime|필수| DATETIME object | 예약 마감 시간|

```json
{
  "limitedQuantityOf": 5,
  "openDateTime": {
    "year": 2023,
    "month": 11,
    "day": 12,
    "hour": 15,
    "minute": 30
  },
  "deadlineDateTime": {
    "year": 2024,
    "month": 10,
    "day": 12,
    "hour": 15,
    "minute": 30
  }
}
```

**RESPONSE**  

| HTTP 응답 코드 | 설명        |
|------------|-----------|
| 201        | 등록 성공     |
| 400        | 알 수 없는 요청 |
| 409        | 정책 에러     |
| 500        | 서버 에러     |

### 티켓 발급

#### POST /v1/subjects/{subjectId}/tickets/acquire

> Content-Type: application-json

**BODY**  

| 파라미터 이름  | 필수 여부 | 데이터 타입 | 설명  |
|----------|-------|--------|-----|
| clientId | 필수    | string | 대상  |

```json
{
  "clientId": "{사용자ID}"
}
```

**RESPONSE**

| HTTP 응답 코드 | 설명        |
|------------|-----------|
| 201        | 등록 성공     |
| 400        | 알 수 없는 요청 |
| 409        | 정책 에러     |
| 500        | 서버 에러     |