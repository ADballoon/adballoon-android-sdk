package kr.co.adballoon.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button mBannerXmlView;
	private Button mBannerCodeView;
	
	private Button mFloatingXmlView;
	private Button mFloatingCodeView;
	
	private Button mInterstitialView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	
	    mBannerXmlView = (Button)findViewById(R.id.banner_xml_view);
	    mBannerXmlView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, BannerXmlActivity.class));
			}
		});
	    
	    mBannerCodeView = (Button)findViewById(R.id.banner_code_view);
	    mBannerCodeView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, BannerCodeActivity.class));
			}
		});
	    
	    mFloatingXmlView = (Button)findViewById(R.id.floating_xml_view);
	    mFloatingXmlView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, FloatingXmlActivity.class));
			}
		});
	    
	    mFloatingCodeView = (Button)findViewById(R.id.floating_code_view);
	    mFloatingCodeView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, FloatingCodeActivity.class));
			}
		});
	    
	    mInterstitialView = (Button)findViewById(R.id.interstitial_view);
	    mInterstitialView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this, InterstitialActivity.class));
			}
		});
	}

}
