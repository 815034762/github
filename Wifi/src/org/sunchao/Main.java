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
	 * ��ť�ȿؼ��ĳ�ʼ��
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

		// �Ѿ�����Wifi��ʱ��,���ص���3
		// �����û�п���wifi��ʱ��,���ص���2

		new Thread(new Runnable() {

			@Override
			public void run() {

				// WifiManager.WIFI_STATE_ENABLING ��ʾ���ڿ���WIFI
				while (WifiManager.WIFI_STATE_ENABLED != mWifiAdmin
						.checkState())// ��ʾ��ǰwifi����
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
