Blog Search Service
===================================
> Spring Boot 기반으로 OPEN API를 활용한 블로그 검색 서비스 입니다.

#### Download jar file
https://github.com/jhjhj0366/blog-search/blob/master/blog-search-0.0.1-SNAPSHOT.jar


## Prerequisites
* JAVA 11
* Spring Boot 2.7.3
  * JPA
  * Feign
  * Redis
  * Lombok
* Gradle
* H2
* Swagger


## Used OPEN API
### KAKAO OPEN API
> https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog

### NAVER OPEN API
> https://developers.naver.com/docs/serviceapi/search/blog/blog.md

---

# Structure
## Table Structure
1. 인기검색어 테이블
```sql

CREATE TABLE TREND_KEYWORD (
   KEYWORD      VARCHAR(255) NOT NULL,
   COUNT        BIGINT,
   CREATED_AT   TIMESTAMP,
   PRIMARY KEY  (KEYWORD)
)

```


---
# Getting Started

## Backend
### Build
   ~~~bash
./gradlew build
   ~~~

### Starting

   ~~~bash
> java -jar blog-search-0.0.1-SNAPSHOT.jar Pprofile=local
   ~~~


### Visit
* http://localhost:8080
* http://localhost:8080/swagger-ui/index.html


# API URL
| Method | Path                | RequestParam            | ResponseBody      |
|--------|---------------------|-------------------------|-------------------|
| GET    | /api/v1/search/blog | query, sort, page, size | 블로그 키워드 검색 결과     |
| GET    | /api/v1/trend/rank  |                         | 검색 키워드 TOP 10 조회  |

---
## TODO List
1. [X] 블로그 검색
    1. [X] 검색 결과에서 Sorting(정확도순, 최신순) 기능을 지원한다.
    2. [X] 검색 결과는 Pagination 형태로 제공한다.
    3. [X] 카카오 API의 키워드로 블로그 검색한다.
    4. [X] 추후 카카오 API 이외에 새로운 검색 소스가 추가될 수 있음을 고려해야 한다.
    5. [X] 장애가 발생한 경우, 네이버 블로그 검색 API를 통해 데이터 제공한다.
        1. Circuit breaker 패턴 이용
        2. 카카오 장애가 나서 네이버로 넘어갔으나 다시 카카오가 회복되면서 카카오로 호출시 데이터 정합성이 떨어지는 것에 대한 대비 방안 고려
            1. API 요청값과 응답값에 카카오 혹은 네이버 플레그값을 추가
2. [X] 인기 검색어
    1. [X] 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공한다.
    2. [X] 검색어 별로 검색된 횟수도 함께 표기한다.
    3. [X] Redis에 캐시된 값이 지워진 경우 어떻게 대비할지 한다. 
       1. 캐시된 값을 주기적으로 디비에 저장
       2. 분단위로 디비에 저장
       3. 캐시값 삭제 룰 24시간
4. [X] 코드 레벨
    1. [X] 트래픽이 많고, 저장되어 있는 데이터가 많음을 고려
    2. [X] 테스트 케이스
    3. [X] 에러 처리
    4. [X] 멀티 모듈 구성 및 모듈간 의존성 제약