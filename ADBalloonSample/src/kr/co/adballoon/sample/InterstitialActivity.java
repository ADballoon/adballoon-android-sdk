package kr.co.adballoon.sample;

import kr.adballoon.ABInterstitialController;
import kr.adballoon.ABInterstitialController.OnABInterstitialListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class InterstitialActivity extends Activity {

	// 전면광고 객체 생성
	private ABInterstitialController mABInterstitial;
	
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    // 전면광고 객체 할당
	    mABInterstitial = new ABInterstitialController(this);
	    // 전면광고 인벤토리 코드 설정
	    mABInterstitial.setInventoryCode("test_bluerain1-MSU3QzIlN0M2NDAlN0M5NjA=");
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
			public void onAdFailedToLoad(int error) {
				// 광고수신 실패 알림
				Log.i("ADballoonLog", "onAdFailedToLoad, errorCode = "+error);
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
	}
}
