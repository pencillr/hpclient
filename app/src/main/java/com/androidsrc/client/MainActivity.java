package com.androidsrc.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView response;
	EditText editTextName;
	Button buttonRegister;
	Switch lumos, stupor, aloho;
	String MyAddress;
	int port;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MyAddress = "192.168.1.27";
		port = 5000;
		editTextName = (EditText) findViewById(R.id.nameEditText);
		lumos = (Switch) findViewById(R.id.lumoSwitch);
		stupor = (Switch) findViewById(R.id.stuporSwitch);
		aloho = (Switch) findViewById(R.id.alohoSwitch);
        buttonRegister = (Button) findViewById(R.id.registerButton);
		response = (TextView) findViewById(R.id.responseOneTextView);

		buttonRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String regMessage = registerMessage(editTextName.getText().toString(),
						lumos.isChecked(), stupor.isChecked(), aloho.isChecked());
				Client myClient = new Client(regMessage);
				myClient.execute();
                Intent intent = new Intent(MainActivity.this, SpellActivity.class);
                startActivity(intent);
			}
		});
	}

	protected String registerMessage(String name, boolean lumos, boolean stupor, boolean aloho) {
		String regMessage = name;
		regMessage += '_';
		if(lumos) {
			regMessage += 'l';
		}
		if(stupor) {
			regMessage += 's';
		}
		if(aloho) {
			regMessage += 'a';
		}
		return regMessage;
	}

}
