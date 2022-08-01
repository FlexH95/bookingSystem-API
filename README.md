
#  예약시스템 : Restful-API 서버 구현
 > 사내 강연 신청 플랫폼 Restful-API 개발 (BookingSystem)

## 주요 내용

>  강연장마다 입장 가능한 인원수가 제한되어 있어 강연 참여 신청 받아 강연 진행 예정.
 강연 신청  플랫폼 Backend의 설계 및 API 개발.

**주요 타겟**
   - 사내 BackOffice, Front User

## 목차 

1.  [개발 스택](#개발-스택)
    + [테이블 정의](#테이블-정의)    
    + [DDL](#ddl)    

2. [개발 상세](#개발-상세)
    + [시스템 구조](#시스템-구조)

    + [개발 API](#개발-api)
    + [추가 고려](#추가-사항)
    
3. [개발 후기](#개발-후기)    

## 개발 스택
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">  <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">  <img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white">  <img src="https://img.shields.io/badge/Apache Maven-C71A36?style=for-the-badge&logo=Apache Maven&logoColor=white">  <img src="https://img.shields.io/badge/talend-FF6D70?style=for-the-badge&logo=Apache talend&logoColor=white">
> - OS : Window 10
> - 개발 언어 : java 1.8
> - 프레임워크 : Spring(Boot) , MyBatis
> - RDBMS : ORACLE 
>  - DB Tool : SQL Developer   
> -  API : JDBC
> - Build Tool :  Maven 
> - 테스트 방법 : Talend API Tester 
> - 데이터 설계 (※ 하단 테이블 정의 및 DDL(소스 schema.sql) 참고)


### 테이블 정의

*   **Lect_TB(강연 마스터)**

|컬럼명         |PK|NULL|TYPE         |설명         | 비고          |
|--------------|:--:|:----:|-------------|:------------:|---------------|
|`lecturerName`  |1 |N   |VARCHAR2(100)|강연자       |               |
|placeName     |2 |N   |VARCHAR2(100)|강연장       |               |
|capCnt        |3 |N   |NUMBER(3)    |수용 인원    |MAX(999)       |                      
|dateTimeStamp |4 |N   |VARCHAR2(12) |강연 일자시간 |YYYYMMDDHH24MI |
|lectDesc      |  |Y   |VARCHAR2(100)|강연 내용    |               |

* **Lect_Emp_TB(강연 신청 마스터)**

|컬럼명        |PK|NULL|TYPE         |설명         | 비고          |
|-------------|:--:|:----:|-------------|:-------------:|---------------|
|`lecturerName` |1 |N   |VARCHAR2(100)|강연자        | FK(Lect_TB. lecturerName) |
|empNo        |2 |N   |VARCHAR2(5)  |사원번호      |               |           

### DDL
```
CREATE TABLE lect_TB  
(
  lecturerName    VARCHAR2(100)  NOT NULL,
  placeName       VARCHAR2(100)  NOT NULL,
  capCnt          NUMBER(3)      NOT NULL,
  dateTimeStamp   VARCHAR2(12)   DEFAULT TO_CHAR(SYSDATE,'YYYYMMDDHH24MI'),
  lectDesc        VARCHAR2(100),
  CONSTRAINT lect_pk PRIMARY KEY (lecturerName, placeName, capCnt, dateTimeStamp)
);
```
```
CREATE TABLE lect_Emp_TB 
( 
  lecturerName   VARCHAR2(100)	NOT NULL,
  empNo          VARCHAR2(5)    NOT NULL,
  CONSTRAINT lect_Emp_pk PRIMARY KEY (lecturerName,empNo)
);
```
```
ALTER TABLE lect_Emp_TB ADD CONSTRAINT fk_lect foreign KEY(lecturerName) references Lect_TB (lecturerName);
```
## 개발 상세 

###  시스템 구조

> **com.exam.bookingSystem.config**
> : 각종 외부 설정들의 Bean 등록을 위한 패키지.
> 
> **com.exam.bookingSystem.controller**
> : 클라이언트의 요청을 처리할 패키지.
> 
> **com.exam.bookingSystem.service**
> : 비지니스 로직을 접급 및 호출할 패키지. 
> 
>   ` (LectTableService(선언), LectTableServiceImpl(호출)로 나눠짐) `
> 
> **com.exam.bookingSystem.mapper**
> : DB에 접근을 위한 패키지. ` (LectTableMapper.xml에서 SQL 제어) `
> 
> **com.exam.bookingSystem.model**
> : Class로 객체 관리 패키지.` (DB Table과 동일) `

### 개발 API

 - **BackOffice** 
 
   *   전체 강연 목록 
       > URI(Query ID) : "/all" (getLectAll) 
       > 
       > ex) http://localhost:8080/booking/lect/all
       
   * 강연 등록
       > URI(Query ID) : "/new" (insertLect(model.LectList)) 
       > 
       > ex) http://localhost:8080/booking/lect/new      
       > 
       > ` parameter : 강연자, 강연장, 수용 인원, 강연 시간, 강연 내용 ` 
       >
       > ` 등록 전 강연 중복 확인(getLectCnt) 후 등록 `

   * 강연신청자 목록(강연별 신청한 사번 목록)
        > URI(Query ID) : "/emp/list" 또는 "/emp/list/{lectName}" (getLectEmpList(String)) 
        >
        > ex) http://localhost:8080/booking/lect/emp/list
        > 또는 http://localhost:8080/booking/lect/emp/list/강연자A
        >
        > ` parameter : 강연자 `
        > 
        > ` 파라미터값에 따라 Null이면 전체 사번 목록 출력, 아니면 해당 강연의 사번 목록 출력 `
 
 
 - **Font User**
 
   * 강연 신청 목록(강연시작시간1주일 전에 노출, 1일 후 노출되지 않음.)
       > URI(Query ID) : "/expo-7" (getLectBefore7days) 
       >
       > ex) http://localhost:8080/booking/lect/expo-7
       
   *  강연 신청(사번(5자리만 입력, 1인 1좌석, 같은 강연 중복 신청 불가, 타 강연 신청 가능)
       > URI(Query ID) : "/emp/new" (insertLectEmp(model.LectApplyList)) 
       > 
       > ex) http://localhost:8080/booking/lect/emp/new
       > 
       > ` parameter : 강연자, 사번 `
       > 
       > ` 신청 등록 전 등록된 강연인지 확인(getLectAll) `
       > 
       > ` 중복(getLectEmpCnt) 있는지 확인 `
       > 
       > ` 사번이 5자리가 맞는지 체크 후 등록 `
      
   *  사번으로 신청된 강연 목록 조회 가능. 
       > URI(Query ID) : "/emp/{empNo}" (getLectListByEmp(String)) 
       >
       > ex) http://localhost:8080/booking/lect/emp/34567
       >
       > ` parameter : 사번 `
       
   * 신청한 강연 취소 가능. 
       > URI(Query ID) : "/emp/{lect}" (deleteLectEmp(model.LectApplyList)) 
       >
       > ex) http://localhost:8080/booking/lect/emp/강연자A?empNo=34567
       >
       > ` parameter : 강연자, 사번 `
       >
       > ` 신청한 강연 취소(삭제) 전 신청 내역이 존재하는지 확인 후 삭제 `
      
   *  실시간 인기 강연 (3일간 가장 신청이 많은 강연순)
       > URI(Query ID) : "/rank" (getLectRank()) 
       > 
       > ex) http://localhost:8080/booking/lect/rank
       
### 추가 사항
- 동시성 이슈 고려
> `@Transactional`(readOnly = false, isolation = Isolation.SERIALIZABLE,
> propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
>

- 데이터 일관성 고려
> `@Transactional`(readOnly = true, isolation = Isolation.READ_COMMITTED)
>

- 단위 테스트
> Jnuit4(controller, mapper, service)

## 개발 후기

### Restful API를 공부 및 개발하면서 느낀점은 아래와 같습니다.

- Front-End와 Back-End의 명확한 분업이 가능하다. (각자의 역할이 명확하게 분리되어 있다.)
- View 영역이 포함되지 않아 신속하고 온전한 서버 개발 진행이 가능하다.
- HTTP Method(HTTP: GET, POST, PUT, DELETE)와 URI를 통해 정의되며 직관적으로 요청을 이해하기 쉽다.
- 개발자간의 소통이 쉽고 혼선을 줄일 수 있을 것 같다.
- 조금 아쉬운 부분은 공식화된 표준(가이드)이(가) 없어 사람마다 다르게 해석이 가능할 것 같다.

### 추가적으로 개발하고 싶은 부분

- 백오피스와 유저에 따라 권한 넣는 부분을 추가하고 싶다.
- 강연에 관련된 정보를 더욱 세분화하여 몇시간 하는지 등에 따라 같은 강연자의 강연이라도 시간을 계산하여 신청 가능 여부를 판단하는 기능을 추가하고 싶다.
