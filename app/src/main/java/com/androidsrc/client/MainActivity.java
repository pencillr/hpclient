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
	EditText editTextName, editWandId;
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
		editWandId = (EditText) findViewById(R.id.wandEditText);
		lumos = (Switch) findViewById(R.id.lumoSwitch);
		stupor = (Switch) findViewById(R.id.stuporSwitch);
		aloho = (Switch) findViewById(R.id.alohoSwitch);
        buttonRegister = (Button) findViewById(R.id.registerButton);
		response = (TextView) findViewById(R.id.responseOneTextView);

		buttonRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String regMessage = registerMessage(editTextName.getText().toString(),
                        editWandId.getText().toString(),
						lumos.isChecked(), stupor.isChecked(), aloho.isChecked());
				Client myClient = new Client(regMessage, response);
				myClient.execute();
                Intent intent = new Intent(MainActivity.this, SpellActivity.class);
                startActivity(intent);
			}
		});
	}

	protected String registerMessage(String name, String wand, boolean lumos, boolean stupor, boolean aloho) {
		String regMessage = name;
		String wandId = wand;
		regMessage = "reg_" + regMessage;
		regMessage += '_';
		regMessage += wandId;
		regMessage += '_';
		if(lumos) {
			regMessage += 'l';
		}
		else{
		    regMessage += '0';
        }
		if(stupor) {
			regMessage += 's';
		}
        else{
            regMessage += '0';
        }
		if(aloho) {
			regMessage += 'a';
		}
        else{
            regMessage += '0';
        }
		return regMessage;
	}

}
