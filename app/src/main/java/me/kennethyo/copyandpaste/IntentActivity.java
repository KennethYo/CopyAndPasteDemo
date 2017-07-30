package me.kennethyo.copyandpaste;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnCopy;
    private Button mBtnPaste;
    private ClipboardManager mClipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        setTitle("Copy Intent");
        initView();
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    private void initView() {
        mBtnCopy = (Button) findViewById(R.id.btn_copy);
        mBtnPaste = (Button) findViewById(R.id.btn_paste);

        mBtnCopy.setOnClickListener(this);
        mBtnPaste.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnCopy) {
            Intent intent = new Intent(this, TextActivity.class);
            ClipData clipData = ClipData.newIntent("copy from demo", intent);
            mClipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "copy success", Toast.LENGTH_SHORT).show();
        }
        if (v == mBtnPaste) {
            if (mClipboardManager.hasPrimaryClip()
                    && mClipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_INTENT)) {
                ClipData.Item item = mClipboardManager.getPrimaryClip().getItemAt(0);
                Intent intent = item.getIntent();
                if (intent == null) {
                    return;
                }
                startActivity(intent);
                Toast.makeText(this, "paste success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
