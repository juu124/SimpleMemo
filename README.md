# 안드로이드 샘플 프로젝트 

기본 기능 : RxJava + RxAndroid + Retrofit
  
참고할 사이트  
=> ReactiveX 사이트 https://reactivex.io/  
=> RxAndroid 사이트 https://github.com/ReactiveX/RxAndroid  
=> RxJava 사이트 https://github.com/ReactiveX/RxJava  
  
=> Retrofit 사이트 https://square.github.io/retrofit/  
=> Maven 저장소 https://mvnrepository.com/  

=> Google Maps 사이트 https://developers.google.com/maps/documentation/android-sdk?hl=ko  
=> Google Maps Android Rx 사이트 https://github.com/googlemaps/android-maps-rx  
  
## Implemetations  
  
implementation 'io.reactivex.rxjava3:rxkotlin:3.0.0'  
implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'  
  
implementation 'com.squareup.retrofit2:retrofit:2.9.0'  
implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'  
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'  //  converter-jackson   
  
## Gradle 설정  
<pre>
</code>
allprojects {  
    repositories {  
        maven { url "https://oss.jfrog.org/libs-snapshot" }  
    }  
}  
  
dependencies {  
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'  
    // Because RxAndroid releases are few and far between, it is recommended you also  
    // explicitly depend on RxJava's latest version for bug fixes and new features.  
    // (see https://github.com/ReactiveX/RxJava/releases for latest 3.x.x version)  
    implementation 'io.reactivex.rxjava3:rxjava:3.0.0'  
}  
</code>
</pre>  

## Gradle 버전 오류 해결
1. c:\user\user\AndroidStudioProjects....에 gradle파일이 없다면 추가하기 (*.gradle 아니고 그냥 gradle)
2. gradle/wrapper/gradle-wrapper.properties 생성하기
3. gradle-wrapper.properties 파일에distributionUrl=https://services.gradle.org/distributions/gradle-원하는 버전-all.zip 라고 적기
4. File->Project Structure->Project에 Android Gradle Plugin Version은 그냥 두고 Gradle Version을 원하는 버전 (예시: 7.3.3)으로 변경한다음 OK누르기

## 목표  
메모장  
=> 화면 : 목록 화면, 작성 화면, 상세 화면  
=> 데이터 : 모든 메모   
=> 메모 컨텐츠 : 텍스트 , 이미지 , 지도  
=> 기능 : 데이터 관리 , 메모 저장, 사진 저장, 지도 api를 이용한 위치 입력
  
작업 진행 순서  
=> 1. 화면 구성  
=> 2. 기능 구현  
=> 2-1. 데이터 처리  
=> 2-2. UI 처리   
=> 3. 테스트  
