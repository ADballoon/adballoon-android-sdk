package kr.co.adballoon.sample;

import kr.adballoon.ABBannerView;
import kr.adballoon.ABView.OnABListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class BannerCodeActivity extends Activity {

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
	    
	    // 광고뷰를 넣을 부모 뷰
	    FrameLayout adBodyLayout = new FrameLayout(getApplicationContext());
//	    adBodyLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	    
	    // 띠배너광고 객체 할당
	    mABBannerView = new ABBannerView(this);
	    // 띠배너광고 LayoutParams 설정
	    mABBannerView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    // 띠배너광고 InventoryCode 설정
	    mABBannerView.setInventoryCode("freebok-NSU3QzElN0M2NDAlN0MxMDA=");
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
	    // 광고 노출
	    mABBannerView.requestAD();
	    
	    adBodyLayout.addView(mABBannerView);
	    
	    setContentView(adBodyLayout);
	}
}
