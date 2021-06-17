package com.example.onlinedirectoryprovider.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.adapter.FaqsAdapter;
import com.example.onlinedirectoryprovider.databinding.ActivityFAQBinding;
import com.example.onlinedirectoryprovider.utils.CommonMethod;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.FaqsViewModel;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityFAQBinding binding;
    FaqsViewModel faqsViewModel;
    String Authorization;
    private RecyclerView recycler1;
    FaqsAdapter faqsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_f_a_q);
        faqsViewModel = new ViewModelProvider(this).get(FaqsViewModel.class);
        binding.toolbar.txtToolBar.setText("FAQ");
        binding.toolbar.imgback.setOnClickListener(this);
        Authorization = CommonMethod.getPreference(FAQActivity.this, "token");
        getFaqs(Authorization);
        recycler1 = findViewById(R.id.recycler1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycler1.setLayoutManager(linearLayoutManager);


    }

    @Override
    public void onClick(View v) {
        if (v == binding.toolbar.imgback) {
            finish();
        }
    }

    private void getFaqs(String Authorization) {
        if (Util.checkConnection(this)) {
            faqsViewModel.faqs(Authorization).observe(this, faqsResponse -> {
                                if (faqsResponse != null && !faqsResponse.getError()) {
                                    faqsAdapter = new FaqsAdapter(this, faqsResponse.getResult());
                                    recycler1.setAdapter(faqsAdapter);
                                    Log.e("Code", faqsResponse.getResult().toString());
                                } else {
                                    ProgressBarUtils.hideProgressDialog();
                                    Toast.makeText(this, "Invalid token", Toast.LENGTH_SHORT).show();
                                }
                            });

        } else {

            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }


}

