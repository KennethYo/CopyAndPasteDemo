package me.kennethyo.copyandpaste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mBtnText;
    private Button mBtnUri;
    private Button mBtnIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    @Override
    public void onClick(View v) {
        if (v == mBtnText) {
            startActivity(new Intent(this, TextActivity.class));
        }
        if (v == mBtnUri) {
            startActivity(new Intent(this, URIActivity.class));
        }
        if (v == mBtnIntent) {
            startActivity(new Intent(this, IntentActivity.class));
        }
    }

    private void initView() {
        mBtnText = (Button) findViewById(R.id.btn_text);
        mBtnText.setOnClickListener(this);
        mBtnUri = (Button) findViewById(R.id.btn_uri);
        mBtnUri.setOnClickListener(this);
        mBtnIntent = (Button) findViewById(R.id.btn_intent);
        mBtnIntent.setOnClickListener(this);
    }
}
