package com.androidsrc.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.AsyncTask;
import android.widget.TextView;

public class Client extends AsyncTask<Void, Void, Void> {

	String dstAddress;
	int dstPort;
	String Message;
	String response = "";
	TextView textResponse;


	Client(String message, TextView textResponse) {
		dstAddress = "192.168.1.27";
		dstPort = 5000;
		Message = message;
		this.textResponse=textResponse;
	}

	@Override
	protected Void doInBackground(Void... arg0) {

		Socket socket = null;

		try {

			socket = new Socket(dstAddress, dstPort);
			//ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
			//		1024);
			//byte[] buffer = new byte[1024];
			byte[] byteArray = null;
			byteArray = Message.getBytes();

			byte[] bytesRead = new byte[1024];

			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(byteArray);

			InputStream inputStream = socket.getInputStream();
			inputStream.read(bytesRead,0, 100);
			response = new String(bytesRead, "UTF-8");;
			/*
			 * notice: inputStream.read() will block if no data return
			 */
			//while ((bytesRead = inputStream.read(buffer)) != -1) {
			//	byteArrayOutputStream.write(buffer, 0, bytesRead);
			//	response += byteArrayOutputStream.toString("UTF-8");
			//}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = "UnknownHostException: " + e.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = "IOException: " + e.toString();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		textResponse.setText(response);
		super.onPostExecute(result);
	}

}
