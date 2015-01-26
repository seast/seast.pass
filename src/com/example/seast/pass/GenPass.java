package com.example.seast.pass;

import android.os.Bundle;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GenPass extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final Button generateButton = (Button) findViewById(R.id.generateButton);
    final EditText pass = (EditText) findViewById(R.id.pass);
    final EditText domain = (EditText) findViewById(R.id.domain);
    final TextView textView = (TextView) findViewById(R.id.textView);
    generateButton.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        String text = 
            pass.getText().toString() + ":" + domain.getText().toString();
        textView.setText(genStr(text));
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }


  public static String genStr(String original)
  {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("SHA1");
    } catch (NoSuchAlgorithmException e) {
      return "";
    }

    md.reset();
    md.update(original.getBytes());

    byte byteData[] = md.digest();

    //convert the byte to hex format method 1
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    } 
    String result = sb.toString().substring(0,8)+"1aA!";
    return result;
  }
}

