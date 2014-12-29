# ADBalloon Android SDK GUIDE

### Copyright (c) 2014 By ADBalloon All rights reserved.
---

### ADBalloon SDK 구성
* ADBalloon.jar : ADBalloon 광고 라이브러리
* Sample/
	- BannerActivity.java : 띠배너 광고 샘플
	- FloatingActivity.java : 플로팅배너 광고 샘픙 
	- InterstitialActivity.java : 전면 광고 샘플

### ADBalloon SDK 사용 순서
* Step 1 : InventoryCode 발급
* Step 2 : 프로젝트에 라이브러리 Import
* Step 3 : AndroidManifest.xml 설정
```XML
	<!-- 광고 수신을 위한  permission -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <!-- 사용자 배너 저장을 위한 permission -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <!-- 네트워크 상태 확인을 위한 permission -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
```
* Step 4 : google_play_service_lib 추가

	2014년 8월 1일부터 Play 스토어에 업로드되는 모든 업데이트 및 새 앱은 광고가 목적인 경우 다른 기기 식별자 대신 광고 ID를 사용해야 한다. [Android 광고 ID 이용 약관](https://play.google.com/about/developer-content-policy.html#ADID)
	
	ADballoon의 경우 사용자 통계를 위해 기기 식별자를 사용하는데, 이때 Google에서 약관으로 명시한 Android 광고 ID를 사용한다.
	
	이에 따라, 광고 ID를 얻기 위해 google_play_services_lib를 추가하고 설정이 필요하며 추가하는 방법은 아래와 같다.
	
	1) Google Play Services Library Project Import
	가지고있는 Android SDK에 포함되어있는 Library를 Import한다.
	
	File -> New -> Android Project from Existing Code -> Root Directory(경로 설정) -> Finish
	
	Library경로는 아래와 같다.
	
	Android SDK/extras/google/google_play_services/libproject/google-play-services_lib/
	
	2) Project에 Library 지정
	개발중인 프로젝트에 추가한 Google Play Services Library를 지정해 주어야 한다.
	
	Project -> Properties -> Android -> Library(Add) -> google_play_services_lib 선택
	
	3) AndroidManifest.xml에 meta-data 태그 추가
	AndroidManifest.xml에 meta-data 태그를 추가해 주어야 한다.
	
	위치는 AndroidManifest.xml <application> </application> 안에 포함해야 하며, 태그 형식은 아래와 같다.
	```
	<meta-data android:name="com.google.android.gms.version"
		android:value="@integer/google_play_services_version" />
	```
	
	보다 자세한 사항은 아래 사이트를 참조한다
	
	[Setting Up Google Play Services](https://developer.android.com/google/play-services/setup.html?hl=ko)
* Step 5 : 프로젝트에 코드 적용

### ADBalloon SDK 에러코드 구성

#### [에러코드(리스너) 종류] ####
###### ABError.ERROR_CODE_ERROR_CONNECTION : 서버와의 연결이 되지 않을 경우 발생
###### ABError.ERROR_CODE_ERROR_IO : 서버와의 연결이 원활하지 않을 경우 발생
###### ABError.ERROR_CODE_ERROR_NO_DATA : 광고 데이터가 없을 경우 발생
###### ABError.ERROR_CODE_NETWORK_ERROR : 단말기의 네트워크 연결이 되어있지 않을때 발생
###### ABError.ERROR_CODE_FAILE_TYPE : 발급 받은 InventoryCode와 생성한 객체의 타입이 다를 경우 발생
###### ABError.ERROR_CODE_ERROR_ETC : 그외의 상황으로 인해 발생

### onResume(), onPause(), onDestroy() 구성

##### [onResume(), onPause(), onDestroy() 사용 (띠배너, 플로팅배너에서만 유효함. 전면배너에서는 사용하지 않음)]

```JAVA
@Override
protected void onResume() {
	//  onResume() 시 광고수신 재개 
	if(mABView != null)
		mABView.resume();
	
	super.onResume();
}

@Override
protected void onPause() {
	// onPause() 시 광고수신 정지
	if(mABView != null)
		mABView.pause();
	
	super.onPause();
}

@Override
protected void onDestroy() {
	// onDestroy() 시 광고수신 종료, 할당 받은 객체 반납
	if(mABView != null)
	{
		mABView.destroy();
		mABView = null;
	}
	
	super.onDestroy();
}
```

### 코드 적용

#### [띠배너]
* XML 사용시

(1) user.xml 파일에 ABBannerView 생성
```XML
<!-- 1. 발급받은 inventory_code 입력 2. request_interval 미입력시 default 10초 -->
<kr.adballoon.ABBannerView
    android:id="@+id/abview"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    inventory_code="user_inventory_code"
    request_interval="10000"/>
```

(2) user.java 파일에 등록
```JAVA
// 띠배너광고 객체 할당
mABBannerView = (ABBannerView)findViewById(R.id.abview);
// 띠배너광고 리스너
mABBannerView.setABListener(new OnABListener() {
	@Override
	public void onAdLoaded() {
		// 광고수신 완료 알림
	}
	
	@Override
	public void onAdLeftApplication() {
		// 광고클릭 이벤트 알림
	}
	
	@Override
	public void onAdFailedToLoad(int arg0) {
		// 광고수신 실패 알림
	}
	
	@Override
	public void onAdClosed() {
		// 광고노출 종료 알림
	}
});
// 광고 노출
mABBannerView.requestAD();
```
* Java Code 사용

(1) 객체 생성후 설정값 셋팅
```JAVA
// 띠배너광고 객체 할당
mABBannerView = new ABBannerView(this);
// 띠배너광고 LayoutParams 설정
mABBannerView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
// 띠배너광고 InventoryCode 설정
mABBannerView.setInventoryCode("user_inventory_code");
// 띠배너광고 재수신 시간 설정
mABBannerView.setRequestInterval(10000);
// 띠배너광고 리스너
mABBannerView.setABListener(new OnABListener() {
  @Override
  public void onAdLoaded() {
    // 광고수신 완료 알림
  }
			
  @Override
  public void onAdLeftApplication() {
    // 광고클릭 이벤트 알림
  }
			
  @Override
  public void onAdFailedToLoad(int arg0) {
    // 광고수신 실패 알림
  }
			
  @Override
  public void onAdClosed() {
    // 광고노출 종료 알림
  }
});
```

(2) 광고 호출
```JAVA
mABBannerView.requestAD();
```

#### [플로팅배너]

* XML 사용시

(1) user.xml 파일에 ABBannerView 생성
```XML
<!-- 1. inventory_code 필수입력 2. request_interval 미입력시 default 10초 3. button_visible 미입력시 true-->
<kr.adballoon.ABFloatingView
    android:id="@+id/abview"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    inventory_code="freebok-NSU3QzIlN0M2NDAlN0M5NjA="
    request_interval="10000"
    button_visible="true"/>
```

(2) user.java 파일에 등록
```JAVA
// 플로팅배너광고 객체 생성
private ABFloatingView mABFloatingView; 
// 플로팅배너광고 객체 할당
mABFloatingView = (ABFloatingView)findViewById(R.id.abview);
// 플로팅배너광고 리스너
mABFloatingView.setABListener(new OnABListener() {
  @Override
  public void onAdLoaded() {
    // 광고수신 완료 알림
  }
			
  @Override
  public void onAdLeftApplication() {
    // 광고클릭 이벤트 알림
  }
			
  @Override
  public void onAdFailedToLoad(int arg0) {
    // 광고수신 실패 알림
  }
			
  @Override
  public void onAdClosed() {
    // 광고노출 종료 알림
  }
});
// 광고 노출
mABFloatingView.requestAD();
```
* JAVA CODE 사용

(1) 객체 생성후 설정값 셋팅
```JAVA
// 광고뷰를 넣을 부모 뷰
FrameLayout adBodyLayout = new FrameLayout(getApplicationContext());
adBodyLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    
// 띠배너광고 객체 할당
mABFloatingView = new ABFloatingView(this);
// 띠배너광고 LayoutParams 설정
mABFloatingView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
// 띠배너광고 InventoryCode 설정
mABFloatingView.setInventoryCode("freebok-NSU3QzElN0M2NDAlN0MxMDA=");
// 띠배너광고 재수신 시간 설정
mABFloatingView.setRequestInterval(10000);
// 띠배너광고 리스너
mABFloatingView.setABListener(new OnABListener() {
  @Override
  public void onAdLoaded() {
    // 광고수신 완료 알림
  }
			
  @Override
  public void onAdLeftApplication() {
    // 광고클릭 이벤트 알림
  }
			
  @Override
  public void onAdFailedToLoad(int arg0) {
    // 광고수신 실패 알림
  }
			
  @Override
  public void onAdClosed() {
    // 광고노출 종료 알림
  }
});
```

(2) 광고 호출
```JAVA
// 광고 노출
mABFloatingView.requestAD();
```

#### [전면배너]
- 전면배너의 경우 일반적인 뷰의 형태가 아니기 때문에 뷰를 생성, addView() 하는 기존의 방식으로 사용 불가능 합니다.

(1) 객체 생성후 설정값 셋팅
```JAVA
// 전면광고 객체 생성
private ABInterstitialController mABInterstitial;
// 전면광고 객체 할당
mABInterstitial = new ABInterstitialController(this);
// 전면광고 인벤토리 코드 설정
mABInterstitial.setInventoryCode("freebok-NSU3QzIlN0M2NDAlN0M5NjA=");
// 전면광고 자동노출 설정
mABInterstitial.requestAndShow(true);
// 전면광고 리스너
mABInterstitial.setABInterstitialListener(new OnABInterstitialListener() {
  @Override
  public void onAdLoaded() {
    // 광고수신 완료 알림
  }
			
  @Override
  public void onAdLeftApplication() {
    // 광고클릭 이벤트 알림
  }
			
  @Override
  public void onAdFailedToLoad(int errorCode) {
    // 광고수신 실패 알림
  }
			
  @Override
  public void onAdClosed() {
    // 광고노출 종료 알림
  }
});
	    
// 자동노출을 하지 않을때 [requestAndShow(false)] 광고 노출에 사용 
//mABInterstitial.show();
	    
// 전면광고 버튼이 아닌 방법으로 종료시킬 때 사용
//mABInterstitial.remove();
```
(2) onBackPressed() 추가
```JAVA
@Override
public void onBackPressed() {
  // 전면광고 노출 중일때 백버튼 클릭시 광고종료, 할당 받은 객체 반납
  if(mABInterstitial != null && !mABInterstitial.isClosed())
  {
    mABInterstitial.close();
    mABInterstitial = null;
  }
  else
    super.onBackPressed();
}
```
