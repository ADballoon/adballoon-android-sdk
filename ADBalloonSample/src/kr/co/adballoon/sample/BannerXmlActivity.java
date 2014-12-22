package kr.co.adballoon.sample;

import kr.adballoon.ABBannerView;
import kr.adballoon.ABView.OnABListener;
import android.app.Activity;
import android.os.Bundle;

public class BannerXmlActivity extends Activity {

	// 띠배너광고 객체 생성
	private ABBannerView mABBannerView;
	
	@Override
	protected void onResume() {
		//  onResume() 시 광고수신 재개 
		if(mABBannerView != null)
			mABBannerView.resume();
		
		super.onResume();
	}

	@Override
	protected void onPause() {
		// onPause() 시 광고수신 정지
		if(mABBannerView != null)
			mABBannerView.pause();
		
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// onDestroy() 시 광고수신 종료, 할당 받은 객체 반납
		if(mABBannerView != null)
		{
			mABBannerView.destroy();
			mABBannerView = null;
		}
		
		super.onDestroy();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_banner);
	
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
	}
}
