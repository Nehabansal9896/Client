package com.example.neha.client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button getBtn;
    private TextView result;
    private OkHttpClient client;
    public TextView ipadd;
    private EditText ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipadd=(TextView)findViewById(R.id.ipadd);
        ip=(EditText)findViewById(R.id.ip);
        getBtn =(Button)findViewById(R.id.Btn);
        result = (TextView)findViewById(R.id.textView);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWebService();
            }
        });

        client = new OkHttpClient();
    }

    private void getWebService(){
       final Request request = new Request.Builder().url("http://www.ssaurel.com/tmp/todos").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText("Failure!");
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response)  {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            result.setText(response.body().string());

                        }catch (IOException ioe){
                            result.setText("Error during get body");
                        }
                    }
                });

            }
        });
    }
}
