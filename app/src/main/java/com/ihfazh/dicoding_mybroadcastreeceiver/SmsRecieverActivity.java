package com.ihfazh.dicoding_mybroadcastreeceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SmsRecieverActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnClose;
    private TextView tvSender, tvMessage;

    public static String NOMOR = "nomor";
    public static String MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_reciever);

        setTitle("Incoming Message");

        btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        tvSender = findViewById(R.id.tv_receiver);
        tvMessage = findViewById(R.id.tv_message);

        String no = getIntent().getStringExtra(NOMOR);
        tvSender.setText(no);

        String message = getIntent().getStringExtra(MESSAGE);
        tvMessage.setText(message);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_close){
            finish();
        }

    }
}