# My First App | 마퍼앱
* 주소록 데이터를 받아오고 앱에 내장된 사진을 앨범 형식으로 볼 수 있습니다.
* 간단한 메모장 기능을 추가하여 개인용 기록장으로 사용할 수 있는 앱입니다.
* 팀원 : 하현수, 이지현

## 개발 환경
* OS: Android (minSdk: 21, targetSdk: 32)
* Language: Kotlin
* IDE: Android Studio
* Target Device: Galaxy S7

## 0. 메인화면
* 세 개의 탭이 띄워진 메인 화면입니다.
* 버튼에 ClickListner를 연결하고 intent로 각각의 행동에 해당하는 activity로 넘어가게 했습니다.
<p align="center">
  <img src="https://user-images.githubusercontent.com/76734678/177277587-baf036ff-2d1f-49f8-aa6e-4a2a5fda2c17.png" width="270" height="480"> 
</p>

## 1. TAB1 주소록 보여주기
* 휴대폰에 내장된 주소록 데이터를 contentResolver를 이용해 받아온 뒤 ListView로 보여주는 페이지입니다.
* 해당 activity를 최초로 실행시켰을 때 주소록 접근 권한을 확인하고 deny 상태일 경우 권한을 요청합니다.
* 이 때, 권한을 거부하면 권한을 허용해야 기능을 사용할 수 있다는 내용과 함께 "설정>앱 세부 설정"으로 이동하는 탭을 dialog로 띄웁니다.
<p align="center">
  <img src="https://user-images.githubusercontent.com/76734678/177278505-5f25a180-d941-4c13-b65d-7851ad1de874.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177277808-8cf7d3d1-a355-4bb8-8b25-7e35ef4e9cec.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177317389-3031144b-1913-458c-b84f-f6b29d4bab58.png" width="270" height="480">
</p>

## 2. TAB2 나만의 앨범
<p align="center">
  <img src="https://user-images.githubusercontent.com/76734678/177277846-61aca445-7a29-4412-a44e-39e30076f83f.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177277879-ee962b6c-5e3c-4a67-9f8b-0e47dbb78aec.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177277920-626fbd5f-b2c6-4856-bb75-20f2e13b2a2f.png" width="270" height="480">
</p>

* 카메라 접근 권한 허용이 되지 않은 경우 다음과 같이 권한 허가를 요청하는 문구를 발송합니다.
* 만약 deny를 누를 경우 다시 한 번 권한 요청을 하며 이 경우 ok를 누를 경우 환경설정창으로 이동되어 사용자가 직접 권한을 허가할 수 있습니다.
<p align="center">
  <img src="https://user-images.githubusercontent.com/76734678/177317764-3e08c1a9-eb31-44f9-bdd9-63a71ba7ca95.png" width="270" height="480">
  <imge src="https://user-images.githubusercontent.com/76734678/177317906-342aeb3e-3240-44fd-85a6-33302d163e70.png" width="270" height="480">
</p>

* 카메라 접근 권한을 사용자가 허용하면 장치에 내장된 카메라 어플리케이션이 작동하여 사용자가 직접 사진을 찍을 수 있게 됩니다. 
<p align="center">
  <img src="https://user-images.githubusercontent.com/76734678/177318088-c5c99a9e-29f6-4f23-aa53-11e4fbdc3613.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177318277-89944279-67cc-4193-a937-01e69f72333b.png" width="270" height="480">
</p>

## 3. TAB3 나만의 메모장
* 간단한 메모를 추가하고, 삭제하고, 변경하고, 검색할 수 있는 페이지입니다.
* SQLite를 이용하여 쿼링을 진행하고 RecyclerView로 메모 목록을 보여줍니다.
* 정렬 순서와 방식을 변경할 수 있습니다.
<p align="center">
  <img src="https://user-images.githubusercontent.com/76734678/177277986-8ed5f365-2bf3-49dd-9b19-e2920a7ad873.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177278055-a6640ef9-3576-46c5-a4a7-99b19857e126.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177278137-f6c07d43-4f1b-4c58-866c-9166b44907f3.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177278240-8a3c1c8b-25b9-45a1-a89e-e5931217d225.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177278300-e7764280-9a11-4d97-913d-0d78a3aa92a9.png" width="270" height="480">
  <img src="https://user-images.githubusercontent.com/76734678/177278321-c521634c-9765-46a5-9acf-8cbb0d29bd2b.png" width="270" height="480">
</p>
