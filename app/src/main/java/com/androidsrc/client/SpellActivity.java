package com.androidsrc.client;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SpellActivity extends Activity {

	TextView response;
	EditText editTextMessage, editCastWandId;
	Button buttonConnect, buttonConnectTwo;
	RadioGroup radioSpellGroup;
	String myCast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spell);

		editTextMessage = (EditText) findViewById(R.id.messageEditText);
		editCastWandId = (EditText) findViewById(R.id.castWandEditText);
		buttonConnect = (Button) findViewById(R.id.connectButton);
		buttonConnectTwo = (Button) findViewById(R.id.connectButton2);
		response = (TextView) findViewById(R.id.responseTextView);
		radioSpellGroup = (RadioGroup) findViewById(R.id.radioSpell);

		radioSpellGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
		{
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch(checkedId){
					case R.id.radioLumos:
						myCast = "spell_" + editCastWandId.getText().toString() + "_lumos";
						break;
					case R.id.radioStupor:
						myCast = "spell_" + editCastWandId.getText().toString() + "_stupor";
						break;
					case R.id.radioAloho:
						myCast = "spell_" + editCastWandId.getText().toString() + "_alohomora";
						break;
				}
			}
		});

		buttonConnect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Client myClient = new Client(editTextMessage.getText().toString(), response);
				myClient.execute();
			}
		});
		buttonConnectTwo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Client myClient = new Client(myCast, response);
				myClient.execute();
			}
		});

	}
}
