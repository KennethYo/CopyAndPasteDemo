package me.kennethyo.copyandpaste;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TextActivity extends AppCompatActivity implements View.OnClickListener {
    private ClipboardManager mClipboardManager;
    private EditText mEtCopy;
    private Button mBtnCopy;
    private EditText mEtPaste;
    private Button mBtnPaste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Copy Text");
        setContentView(R.layout.activity_text);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        initView();
    }

    private void initView() {
        mEtCopy = (EditText) findViewById(R.id.et_copy);
        mBtnCopy = (Button) findViewById(R.id.btn_copy);
        mBtnCopy.setOnClickListener(this);
        mEtPaste = (EditText) findViewById(R.id.et_paste);
        mBtnPaste = (Button) findViewById(R.id.btn_paste);
        mBtnPaste.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == mBtnCopy) {
            ClipData clipData = ClipData.newPlainText("copy from demo", mEtCopy.getText());
            mClipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "copy success", Toast.LENGTH_SHORT).show();
        }
        if (v == mBtnPaste) {
            // 粘贴板有数据，并且是文本
            if (mClipboardManager.hasPrimaryClip()
                    && mClipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                ClipData.Item item = mClipboardManager.getPrimaryClip().getItemAt(0);
                CharSequence text = item.getText();
                if (text == null) {
                    return;
                }
                mEtPaste.setText(text);
                Toast.makeText(this, "paste success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
