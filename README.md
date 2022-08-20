# ShoppingMall 
 ### Spring Boot와 Vue.js을 이용한 나만의 쇼핑몰 제작
 #### 사용 기술 및 데이터베이스(백엔드): Spring Boot,  QueryDSL, Spring Data JPA, MariaDB
 #### 사용 기술(프론트엔드): Vue.js
 ##### 간단한 CRUD을 기반으로한 사이트 입니다. -> 상품 등록, 상품 수정, 상품 목록, 특정 상품 내용을 확인 할 수 있습니다.
 
 
 
0.회원 가입 및 로그인
---------------------------------------
회원 가입을 할 수 있고 로그인을 해야 상품을 등록 및 수정, 삭제 할 수 있습니다.  (세션 및 쿠키 이용)

<img src="https://user-images.githubusercontent.com/96513191/185577605-2e30a013-cd34-42d2-a801-e294b302118b.PNG" width="70%" height="500">

<img src="https://user-images.githubusercontent.com/96513191/185577722-6a511cec-7d48-4b56-82b6-38870d8c50f4.PNG" width="70%" height="500">


1.로그인하지 않을 시 상품을 등록 및 수정,삭제를 할 수 없습니다.
---------------------------------------

<img src="https://user-images.githubusercontent.com/96513191/185577944-4d332b98-61fb-4333-bb33-1cf9383186fb.PNG" width="60%" height="700">

<img src="https://user-images.githubusercontent.com/96513191/185578253-c3e40deb-21f1-4ff0-b261-23a5ce2ca209.PNG" width="60%" height="700">

<img src="https://user-images.githubusercontent.com/96513191/185578405-3c83b646-0ec7-4a13-baa9-269df5d91d1b.PNG" width="60%" height="700">


2.상품 목록,등록,수정,삭제
-----------------------------------------------------------------

1.상품 등록

<img src="https://user-images.githubusercontent.com/96513191/185578983-fd73569d-9c89-4057-a60f-b25d39329e14.PNG" width="50%" height="600">

2.상품 목록


<img src="https://user-images.githubusercontent.com/96513191/185579232-79f0abc9-4894-4126-8f82-4a40dc924d97.PNG" width="70%" height="700">

<img src="https://user-images.githubusercontent.com/96513191/185579246-00fc1c6d-a7a4-4ecb-8326-70e348ddf8f7.PNG" width="70%" height="700">

<img src="https://user-images.githubusercontent.com/96513191/185579255-4befc200-3731-424d-88f9-6a8bf01afeac.PNG" width="70%" height="700">


3.특정 상품 내용(상품 목록에서 상품 제목 클릭시 이동 가능)

<img src="https://user-images.githubusercontent.com/96513191/185579523-f4e75d39-d7ea-49eb-9e14-88f20a535d83.PNG" width="50%" height="700">

4.상품 수정 및 변경 사항 확인

<img src="https://user-images.githubusercontent.com/96513191/185579793-c800e771-a71d-47fb-aeb1-8f83d7a34ae9.PNG" width="60%" height="600">

<img src="https://user-images.githubusercontent.com/96513191/185579910-490bac2e-a113-4050-9589-a4a6651b8f69.PNG" width="60%" height="600">


3.2022-08-20 추가 사항
---------------------------------------

1. 검색 기능이 추가되었습니다.('제목','내용','제목+내용'으로 검색할 수 있습니다.)

<img src="https://user-images.githubusercontent.com/96513191/185733590-e8b5d82d-e210-47f9-8778-3fb95ee51f41.PNG" width="60%" height="800">


2. 상품과 멤버간의 연관관계가 추가되었습니다.(특정 회원은 자신이 등록한 상품만 수정 및 삭제 가능합니다.)

<img src="https://user-images.githubusercontent.com/96513191/185733728-8342cd66-254e-4363-8d65-c6fcd5e830c0.PNG" width="60%" height="800">

<img src="https://user-images.githubusercontent.com/96513191/185733763-5db7068d-633e-4f45-899a-b877bfef0a7a.PNG" width="60%" height="800">


3.로그아웃 기능 추가.

<img src="https://user-images.githubusercontent.com/96513191/185733788-ba44b3fa-ccf3-45c7-b616-0b354440e601.PNG" width="60%" height="600">

4.추후 변경 내용: 세션이 아닌 스프링 시큐리트(토큰)을 이용한 로그인 기능 구현
---------------------------------------



