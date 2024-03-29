# Section 02
[AndroidDeveloper](https://developer.android.com/)
## 액티비티
### 어플리케이션 Component
+ 안드로이드 삼대장
  * ContentProvider
  * Service
  * BroadcastReceiver
+ 원수
  * Activity
### 액티비티는?
+ 액티비티 = 알파, 오메가
  * 윈도우의 '창'
  * 맥의 'finder'
  * 아이콘 누르면 뜨는 것 = 액티비티
+ 액티비티 = 화면
  * UI를 보여주고
  * 행동까지 한다
  * 어플리케이션에서 하는 일은 다 한다
### 액티비티의 생명주기
**외우자**    
on이 붙은 것은 인터페이스의 콜백.
### onCreate()
* onCreate()에서 액티비티 작성을 시작하자
## Intent
* Intent는 의사소통 담당.
* Activity는 Intent를 통해 의사소통. BroadcastReceiver 나 Service도 Intent를 통해 Activity에 전달.
### intent는 우편배달부
* 액티비티 - 액티비티 (서비스, Broadcast Receiver) 간 의사소통
* 앱 - 앱 간 의사소통
* int, 배열, ... 등 다양한 자료를 넣어 보낼 수 있다.
#### 1. 명시적 Intent
* 보낸이와 받는이가 명시되어있다.
* 한 어플리케이션 안에서 액티비티 간 의사소통할 때 자주 쓰임.
* 다른 Activity를 시작할 수 있게 도와준다.
``` java
Intent intent = new Intent(this, NewActivity.class);
startActivity(intent);
```
 #### 2. 암시적 Intent
 * 해당된다 싶으면 다 부른다.
 * 앱 밖에서 앱 간 의사소통할 때 자주 쓰임.
 * 다른 앱을 실행할 수 있다.
 ``` java
 Intent intent = new Intent();
 sendIntent.setAction(Intent.ACTION_CALL);
 startActivity(sendIntent);
 ```
## Android Studio
* Logcat 화면에서 에러를 확인할 수 있다.
* 에러가 나면 Logcat 확인 후 구글에 검색해보자.
* 공식홈페이지에서 영상과 번역 문서를 읽어보자.
