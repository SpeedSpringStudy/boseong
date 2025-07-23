
# Week#1
<details>
<summary><strong>Week#1-Step#1</strong></summary>

- [ ] **상품 조회**
    - 상품 목록을 조회하는 기능
    - HTTP 메서드: GET
    - 엔드포인트: `/api/products`

- [ ] **상품 추가**
    - 새로운 상품을 추가하는 기능
    - HTTP 메서드: POST
    - 엔드포인트: `/api/products`

- [ ] **상품 수정**
    - 기존 상품 정보를 수정하는 기능
    - HTTP 메서드: PUT
    - 엔드포인트: `/api/products/{id}`

- [ ] **상품 삭제**
    - 특정 ID를 가진 상품을 삭제하는 기능
    - HTTP 메서드: DELETE
    - 엔드포인트: `/api/products/{id}`
<img width="441" alt="스크린샷 2025-07-06 오후 4 14 29" src="https://github.com/user-attachments/assets/697fa6ec-77fc-4088-921c-01699bc34545" />

</details>


<details>
<summary><strong>Week#1-Step#2</strong></summary>

## step1 - 조회, 추가, 수정, 삭제 API
- [x] **상품 조회**
  - 상품 목록을 조회하는 기능
  - HTTP 메서드: GET
  - 엔드포인트: `/api/products`

- [x] **상품 추가**
  - 새로운 상품을 추가하는 기능
  - HTTP 메서드: POST
  - 엔드포인트: `/api/products`

- [x] **상품 수정**
  - 기존 상품 정보를 수정하는 기능
  - HTTP 메서드: PUT
  - 엔드포인트: `/api/products/{id}`

- [x] **상품 삭제**
  - 특정 ID를 가진 상품을 삭제하는 기능
  - HTTP 메서드: DELETE
  - 엔드포인트: `/api/products/{id}`

## step2 - 관리자 화면

- [ ] **상품 목록 화면**
  - 상품 목록을 화면에 표시하는 기능
  - HTML 페이지: `templates/products.html`
  - 접속 방법 : `localhost:8080/products`
  - 상품 목록과 오른쪽 상단의 상품 추가 버튼
  - 각 상품 우측 상품 수정 버튼과 상품 삭제 버튼

- [ ] **상품 추가 화면**
  - 새로운 상품을 추가하는 화면
  - HTML 페이지: `templates/product_form.html`
  - 폼을 통해 상품 정보를 입력받아 추가

- [ ] **상품 수정 화면**
  - 기존 상품 정보를 수정하는 화면
  - HTML 페이지: `templates/product_edit_form.html`
  - 폼을 통해 상품 정보를 입력받아 수정

- [ ] **AJAX를 통한 비동기 처리**
  - 상품 추가, 수정, 삭제 시 페이지 새로고침 없이 비동기로 처리
  - `fetch` API를 사용하여 서버와 통신
</details>

<details>
<summary><strong>Week#1-Step#3</strong></summary>
    
## step3 - JDBC 적용 및 리팩토링

### 기능 요구 사항
- JDBC를 사용하여 데이터를 H2 데이터베이스에 저장하고 관리한다.
- 상품 정보를 데이터베이스에 저장하고 조회, 수정, 삭제하는 기능을 구현한다.

### 구현 목록
- [x] **데이터베이스 테이블 생성**
  - `Product` 테이블 생성
- [x] **JDBC DAO 구현**
  - `ProductDao` 클래스 구현
- [x] **컨트롤러 리팩토링**
  - 기존의 `HashMap` 대신 데이터베이스를 사용하도록 `ProductController` 수정
- [x] **상품 추가/수정 시 DB 처리**
  - 상품 추가 및 수정 시 데이터베이스에 반영하도록 로직 수정
- [x] **상품 조회 시 DB 사용**
  - 상품 조회 시 데이터베이스에서 정보 조회
- [x] **상품 삭제 시 DB 사용**
  - 상품 삭제 시 데이터베이스에서 정보 삭제
</details>

# Week2

<details>
<summary><strong>Week#2-Step#1</strong></summary>

### step1 - 유효성 검사 및 예외처리

#### 기능 구현

1. 상품 추가, 수정 시 유효성 검사  
   - [ ] 상품 이름은 공백 포함 최대 15자까지 입력 가능  
   - [ ] 가능한 특수 문자: ( ), [ ], +, -, &, /, _  
   - [ ] "카카오"가 포함된 문구는 담당 MD와 협의한 경우에만 사용 가능  

</details>

<details>
<summary><strong>Week#2-Step#2</strong></summary>

### 로그인 및 회원가입

- [ ] email, password 를 통한 회원가입  
- [ ] 유저 정보를 기반으로 한 로그인  

#### 토큰 발급  
- [ ] 로그인이 완료된다면 토큰 제공 (bearer 방식 -> JWT 사용)

</details>

<details>
<summary><strong>Week#2-Step#3</strong></summary>

### 위시리스트 추가

- [ ] 유저 아이디로 식별 가능해야 함

</details>

# Week3
<details>
<summary><strong>Week#3-Step#1</strong></summary>

### 기존 코드 리팩토링
- [ ] 엔티티, 레포지토리 작성
- [ ] 그에 맞게 서비스 수정
- [ ] Dao 삭제    

</details>

<details>
<summary><strong>Week#3-Step#2</strong></summary>

spring-gift-jpa

### step1 - JPA 적용

1. 기존 코드 리팩토링
    - [ ] 엔티티, 레포지토리 작성
    - [ ] 그에 맞게 서비스 수정
    - [ ] Dao 삭제

2. 테스트 코드 작성
    - [ ] 기존 Service 테스트 수정
    - [ ] `@DataJpaTest` 이용하여 테스트 작성
    - [ ] 새로운 E2E 테스트 작성

### step2 - 엔티티 모델링

1. 엔티티 연관 관계 설정
   - [ ] `Wish` 엔티티가 `User`, `Product` 참조하도록 함
   - [ ] `user_id`, `product_id`를 FK로 가지도록 설정

</details>
