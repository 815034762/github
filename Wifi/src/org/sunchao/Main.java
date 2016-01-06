package org.sunchao;

import android.app.Activity;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiConfiguration.AuthAlgorithm;
import android.net.wifi.WifiConfiguration.KeyMgmt;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {

	private Button connect;
	private TextView scanResult;

	private String mScanResult;

	WifiAdmins mWifiAdmin;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	/**
	 * 按钮等控件的初始化
	 */
	public void init() {

		mWifiAdmin = new WifiAdmins(this);

		connect = (Button) findViewById(R.id.connect);

		connect.setOnClickListener(Main.this);
	}

	public void checkNetCardState() {
		mWifiAdmin.checkState();
	}

	public void connect() {

		mWifiAdmin.openWifi();

		// 已经开启Wifi的时候,返回的是3
		// 如果还没有开启wifi的时候,返回的是2

		new Thread(new Runnable() {

			@Override
			public void run() {

				// WifiManager.WIFI_STATE_ENABLING 表示正在开启WIFI
				while (WifiManager.WIFI_STATE_ENABLED != mWifiAdmin
						.checkState())// 表示当前wifi可用
				{

				}
				mWifiAdmin.addNetwork(mWifiAdmin.CreateWifiInfo("Wifi",
						"10101030", 3));
			}
		}).start();

		// startActivityForResult(new Intent(
		// android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
		// startActivity(new
		// Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.connect:
			connect();
			break;
		default:
			break;
		}
	}
}
