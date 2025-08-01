
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

### step2 - 엔티티 모델링

1. 엔티티 연관 관계 설정
   - [ ] `Wish` 엔티티가 `User`, `Product` 참조하도록 함
   - [ ] `user_id`, `product_id`를 FK로 가지도록 설정

</details>

<details>
<summary><strong>Week#3-Step#3</strong></summary>

## 과제 진행 요구 사항

상품과 위시 리스트 보기에 페이지네이션을 구현한다.

- 대부분의 게시판은 모든 게시글을 한 번에 표시하지 않고 여러 페이지로 나누어 표시한다. 정렬 방법을 설정하여 보고 싶은 정보의 우선 순위를 정할 수도 있다.
- 페이지네이션은 원하는 정렬 방법, 페이지 크기 및 페이지에 따라 정보를 전달하는 방법이다.

## 프로그래밍 요구 사항

## 힌트

이를 직접 구현할 수도 있지만, 스프링 데이터는 **`Pageable`**이라는 객체를 제공하여 쉽게 구현할 수 있다. 또한 **`List`**, **`Slice`**, **`Page`** 등 다양한 반환 타입을 제공한다.

</details>



# Week#4
<details>
<summary><strong>Week#4-Step#1</strong></summary>

### 기능 요구 사항

상품 정보에 카테고리를 추가한다. 상품과 카테고리 모델 간의 관계를 고려하여 설계하고 구현한다.

- 카테고리는 1차 카테고리만 있으며 2차 카테고리는 고려하지 않는다.
- 카테고리는 수정할 수 있다.
- 관리자 화면에서 상품을 추가할 때 카테고리를 지정할 수 있다.
- 카테고리의 예시는 아래와 같다.
    - 교환권, 상품권, 뷰티, 패션, 식품, 리빙/도서, 레저/스포츠, 아티스트/캐릭터, 유아동/반려, 디지털/가전, 카카오프렌즈, 트렌드 선물, 백화점

아래 예시와 같이 HTTP 메시지를 주고받도록 구현한다.

#### Request
```
GET /api/categories HTTP/1.1
```
#### Response
```
HTTP/1.1 200
Content-Type: application/json

{
“id”: 91,
“name”: “교환권”,
“color”: “#6c95d1”,
“imageUrl”: “https://gift-s.kakaocdn.net/dn/gift/images/m640/dimm_theme.png”,
“description”: “”
}

```
### 프로그래밍 요구 사항

- 구현한 기능에 대해 적절한 테스트 전략을 생각하고 작성한다.
- 카테고리를 추가하는 문제이다. 1차카테고리를 지정하고, 수정할 수 있으며, 관리자 화면에서 지정할 수 있어야 한다.

</details>


<details>
<summary><strong>Week#4-Step#2</strong></summary>

## step 2 - 옵션 추가

### 옵션 추가
- **옵션 구현**
  - [ ] 각 상품의 옵션별 잔여 수량 저장 api구현

</details>

<details>
<summary><strong>Week#4-Step#3,4</strong></summary>

## step 3,4 차감 기능 구현 - 동시성 제어 필요

</details>

