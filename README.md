# sakak_food
## 식품영양성분 DB에 대한 Search API, REST API 구현


## 스택
<div style = "display: flex; justify-content: center;">
<img src="https://img.shields.io/badge/springboot-바탕색?style=flat&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/mysql-blue?style=flat&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/git-red?style=flat&logo=git&logoColor=white"/>
<img src="https://img.shields.io/badge/docker-blue?style=flat&logo=docker&logoColor=white"/>
<img src="https://img.shields.io/badge/intellij-black?style=flat&logo=intellijidea&logoColor=white"/>
</div>

음식 검색

- **URL:** `/foods/search`
- **메소드:** `GET`
- **설명:** 지정된 요청인자에 따라 음식 목록을 검색
- **매개변수:**
    - `food_name`: 식품이름.
    - `research_year`: 연도(YYYY).
    - `maker_name`: 지역/제조사.
    - `food_code`:식품코드.
- **응답:** 음식정보 JSON 객체 반환

#### ID로 음식 조회

- **URL:** `/foods/{id}`
- **메소드:** `GET`
- **설명:** ID로 특정 음식 항목을 조회
- **매개변수:**
    - `id`: 음식 항목의 ID.
- **응답:** 음식정보 JSON 객체 반환

#### 인기 음식 조회

- **URL:** `/foods`
- **메소드:** `GET`
- **설명:** 매개변수를 기준으로 페이지별로 음식 목록 조회
- **매개변수:**
    - `page`: 페이지 번호 (기본값은 0).
    - `size`: 페이지 당 항목 수 (기본값은 10).
    - `sortBy`: 정렬 기준 필드 (기본값은 `id`).
    - `sortOrder`: 정렬 순서 (`asc` 또는 `desc`, 기본값은 `asc`).
- **응답:** 페이지 단위로 음식 객체 목록 반환

#### 새로운 음식 생성

- **URL:** `/foods`
- **메소드:** `POST`
- **설명:** 새로운 음식 생성, foodCd, foodName, researchYear, makerName 필수 입력
- **요청 바디:** FoodDTO 객체 (id를 제외한 음식 정보를 포함하는 데이터).
- **응답:** 생성된 음식 객체 JSON 형태로 반환

#### 기존 음식 업데이트

- **URL:** `/foods/{id}`
- **메소드:** `PUT`
- **설명:** 기존 음식 업데이트, foodCd, foodName, researchYear, makerName 필수 입력
- **매개변수:**
    - `id`: 업데이트할 음식 항목의 ID.
- **요청 바디:** FoodDTO 객체 (id를 제외한 음식 정보를 포함하는 데이터).
- **응답:** 업데이트된 음식 객체를 JSON 형태로 반환

#### 특정 음식 삭제

- **URL:** `/foods/{id}`
- **메소드:** `DELETE`
- **설명:** 특정 음식 삭제
- **매개변수:**
    - `id`: 삭제할 음식 항목의 ID.
- **응답:** 삭제 성공 메시지 반환

---
