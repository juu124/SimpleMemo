# 안드로이드 샘플 프로젝트 

기본 기능 : RxJava + RxAndroid + Retrofit
  
  
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
## 목표  
  
메모장  
=> 표출 : 날짜별 / 시간별   
=> 컨텐츠 : 텍스트 , 이미지   
=> 기타 : PDF 내려받기  
  
