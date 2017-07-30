package me.kennethyo.copyandpaste;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class URIActivity extends AppCompatActivity implements View.OnClickListener {
    private String uriStr = "https://www.baidu.com";
    private TextView mTvCopy;
    private Button mBtnCopy;
    private TextView mTvScheme;
    private TextView mTvUri;
    private Button mBtnPaste;
    private ClipboardManager mClipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Copy URI");
        setContentView(R.layout.activity_uri);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        initView();
    }

    private void initView() {
        mTvCopy = (TextView) findViewById(R.id.tv_copy);
        mTvCopy.setText(uriStr);
        mBtnCopy = (Button) findViewById(R.id.btn_copy);
        mBtnCopy.setOnClickListener(this);
        mTvScheme = (TextView) findViewById(R.id.tv_scheme);
        mTvUri = (TextView) findViewById(R.id.tv_uri);
        mBtnPaste = (Button) findViewById(R.id.btn_paste);
        mBtnPaste.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnCopy) {
            Uri uri = Uri.parse(uriStr);
            ClipData clipData = ClipData.newUri(getContentResolver(), "copy from demo", uri);
            mClipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "copy success", Toast.LENGTH_SHORT).show();
        }
        if (v == mBtnPaste) {
            if (mClipboardManager.hasPrimaryClip()
                    && mClipboardManager.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_URILIST)) {
                ClipData.Item item = mClipboardManager.getPrimaryClip().getItemAt(0);
                Uri uri = item.getUri();
                if (uri == null) {
                    return;
                }
                mTvScheme.setText(uri.getScheme());
                mTvUri.setText(uri.toString());
                Toast.makeText(this, "paste success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
