package com.example.onlinedirectoryprovider.activity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.databinding.ActivityTandCBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.TermsAndConditionsViewModel;

public class TandCActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityTandCBinding binding;
    String Authorization;
    TermsAndConditionsViewModel termsAndConditionsViewModel;
    String data="";
    String TAG=TandCActivity.class.getSimpleName();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_tand_c);
       // binding.toolbar.txtToolBar.setText("Terms & Conditions");
        termsAndConditionsViewModel = new ViewModelProvider(this).get(TermsAndConditionsViewModel.class);
       // binding.toolbar.imgback.setOnClickListener(this);
        Authorization = CommonMethod.getPreference(TandCActivity.this, "token");
        Log.i("Authorization",Authorization);

        getTandC();


    }

    @Override
    public void onClick(View v) {
//        if (v == binding.toolbar.imgback) {
//            finish();
//        }
    }


    private void getTandC() {
        if (Util.checkConnection(this)) {
            termsAndConditionsViewModel.tandc(Authorization)
                    .observe(this, termsAndConditionsResponse -> {
                        Log.e(TAG,"laxman");
                                if (termsAndConditionsResponse != null && !termsAndConditionsResponse.getError()) {
                                    data=termsAndConditionsResponse.getResult().getDescription();
                                    data.replace("&lt;","<");
                                    data.replace("&gt;",">");
                                    Log.e(TAG,data);
                                    String unencodedHtml = "<html><body>"+ data+ "</body></html>";
                                    String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(), Base64.NO_PADDING);
                                    binding.webView.loadData(encodedHtml, "text/html", "base64");
                                }
                                else {
                                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
                                }
                            });
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }
}

