# 텍스트마이닝 기반 가구 온라인 고객 리뷰분석 및 추천 웹어플리케이션
<br>

## 프로젝트 기간
2021.02-2021.06

<br>

## 프로젝트 목표

>가구와 인테리어 상품에 대한 리뷰를 분석하여 소비자의 구매를 돕고 개인의 취향에 맞는 상품을 추천하는 시스템을 제안한다.
>리뷰 분석을 통해 각 상품을 잘 설명하는 핵심 키워드를 설정하였고 리뷰의 긍정 부정 예측이 가능하다. 또한, 소비자의 구매 이력과 관심 목록을 기반으로 개인에게 맞는 상품을 추천한다.

가. 웹 크롤링 기반 상품 목록 및 리뷰 수집
- 자체 데이터가 없기 때문에 크롤링을 통해 네이버 쇼핑에 있는 상품 목록 및 리뷰를 수집하여 사용한다.

나. 자연어 처리 기반 리뷰 시각화 및 긍정 부정 예측과 추천 시스템
- 크롤링을 통해 가져온 리뷰를 자연어 처리를 통해서 키워드 화하고 출연 빈도수에 따라 점수화해 리뷰 시각화를 한다. 또한, 이 키워드를 기반으로 리뷰의 긍정 부정 예측 모델을 구축한다.
- 소비자들의 관심 목록과 구매 목록의 데이터를 기반으로 개인 맞춤 추천 시스템을 구축한다.

다. 자체 웹 플랫폼 및 커뮤니티 기능
- 회원가입과 로그인 기능을 구현한 ‘뒤집어집’을 통해 구매한 상품에 대해서 자체적으로 리뷰 입력이 가능한 웹 플랫폼을 구현한다.
- 가구 인테리어에 관심이 많은 사람이 정보를 공유하고 소통할 수 있는 커뮤니티 기능을 포함한다.

<br>

## 프로젝트 구조

<img width="462" alt="image" src="https://user-images.githubusercontent.com/58569346/168980594-4a7bafe9-85e0-4834-8721-c36672b02b56.png">
<br>

## 프로젝트 설명

* 상품 카테고리에서 상품을 볼 수 있고 검색이 가능하다. 각 상품 상세페이지로 들어가면 자연어 처리를 통해 얻은 상품 키워드를 확인할 수 있다.

* 키워드를 바탕으로 상품의 특성을 파악할 수 있으며 소비자들의 리뷰를 한눈에 볼 수 있고 리뷰 검색 필터도 가능하다.

* 추천시스템을 통해 상품을 추천을 받을 수 있다. flask 서버와 연결된 아이템 기반 추천 코드는 Spring Boot 내에서 요청 시 응답을 한다.

* Spring Security 를 통해 사이트의 보안을 강화한다. 회원가입 시에 비밀번호는 bcryptencoder 를 이용하여 보안 수준을 높인다.

* jwt token을 이용해서 로그인에 대한 응답을 하고 사이트에 대한 권한을 부여함으로써 더 안전하게 데이터를 주고받는다.

* UserDetails 인터페이스를 이용하여 ‘뒤집어집’에 가입한 사용자들만 개인화 된 기능이 가능하다.

<br>

## 프로젝트 결과

* 메인 페이지
![image](https://user-images.githubusercontent.com/58569346/168983877-46dbc729-21f8-4adf-8f5a-bb09e7dc8723.png)
<br>

* 회원 가입 페이지
![image](https://user-images.githubusercontent.com/58569346/168983898-c288acba-8db5-439e-9ac1-fa775bedb59f.png)
<br>

* 로그인 후 메인 페이지
![image](https://user-images.githubusercontent.com/58569346/168983915-9abc90d2-bbcb-4d8d-bbd3-bc5e71256a61.png)
<br>

* 카테고리 별 상품 목록 페이지
![image](https://user-images.githubusercontent.com/58569346/168983941-4d7caa0a-0870-402d-919f-eb56777aa8f1.png)
<br>

* 상품 상세 페이지
![image](https://user-images.githubusercontent.com/58569346/168984060-43b27939-c581-423d-9b13-e86b83b5a382.png)
<br>

* 상품 리뷰 페이지
![image](https://user-images.githubusercontent.com/58569346/168984141-3c6e1b75-0baa-47c4-8c4f-c2ebd2015e50.png)
<br>

* 장바구니 목록 페이지
![image](https://user-images.githubusercontent.com/58569346/168984167-c5d18a4e-0ee5-4366-bd4e-2c0ef9ccfa43.png)
<br>

* 구매 목록 페이지
![image](https://user-images.githubusercontent.com/58569346/168984197-ac29d637-8f5b-462e-a132-3f4dadb263df.png)
<br>

* 리뷰 작성 페이지
![image](https://user-images.githubusercontent.com/58569346/168984233-047c50eb-4ad1-48bc-a305-209d4266944d.png)
<br>

* 마이 페이지
![image](https://user-images.githubusercontent.com/58569346/168984255-8e8b1f37-62b9-40bb-8d35-779d5319d093.png)
<br>

* Q&A 커뮤니티
![image](https://user-images.githubusercontent.com/58569346/168984269-c6469e4d-02fe-42fe-913e-955ac4dcfb17.png)
<br>

* 포토 커뮤니티 게시글
![image](https://user-images.githubusercontent.com/58569346/168984461-ec4e8f2d-1661-47a6-a619-feb7bc3a19cb.png)
<br>

* 포토 커뮤니티 게시글 목록
![image](https://user-images.githubusercontent.com/58569346/168984433-837a0356-3630-44df-af90-b50b5382b40b.png)


<br>

## 개발 환경

* AWS ec2

* mysql

* IntelliJ

* Java, Spring Boot


