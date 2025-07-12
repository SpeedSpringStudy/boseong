
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
