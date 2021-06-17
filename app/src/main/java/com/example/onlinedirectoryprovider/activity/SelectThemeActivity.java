package com.example.onlinedirectoryprovider.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinedirectoryprovider.R;
import com.example.onlinedirectoryprovider.adapter.SelectThemeAdapter;
import com.example.onlinedirectoryprovider.databinding.ActivitySelectThemeBinding;
import com.example.onlinedirectoryprovider.model.SelectThemeListModel;
import com.example.onlinedirectoryprovider.utils.ProgressBarUtils;
import com.example.onlinedirectoryprovider.utils.Util;
import com.example.onlinedirectoryprovider.viewmodel.SelectThemeViewModel;

import java.util.ArrayList;
import java.util.List;

public class SelectThemeActivity extends AppCompatActivity implements View.OnClickListener {

    SelectThemeViewModel selectThemeViewModel;
    ActivitySelectThemeBinding binding;
    String Authorization;
    private RecyclerView recycler1;
    SelectThemeAdapter selectThemeAdapter;
    private List<SelectThemeListModel> listModelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_theme);
        selectThemeViewModel = new ViewModelProvider(this).get(SelectThemeViewModel.class);
        binding.btnsetuptheme.setOnClickListener(this);


        recycler1 = findViewById(R.id.recycler1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recycler1.setLayoutManager(gridLayoutManager);
        listModelList.clear();
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));
        listModelList.add(new SelectThemeListModel(R.drawable.frame1));

          if (listModelList.size() > 0) {
            recycler1.setAdapter(new SelectThemeAdapter(this, listModelList));


        }
      //  getSelectTheme();

    }

    private void getSelectTheme() {
        if (Util.checkConnection(this)) {
            ProgressBarUtils.showProgressDialog(this);
            selectThemeViewModel.selecttheme(Authorization)
                    .observe(this,
                            selectThemeResponse -> {
                                Log.i("shubham", "success");
                               ProgressBarUtils.hideProgressDialog();
                                if (selectThemeResponse != null && !selectThemeResponse.getError()){




                                }
                                else
                                    {
                                    Log.i("shubham", "failure");
                                    Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
                                    }
                            });
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        if (v == binding.btnsetuptheme) {
            startActivity(new Intent(SelectThemeActivity.this, AdditionalDetailsActivity.class));
        }
    }
        @Override
        public void onBackPressed() {
            startActivity(new Intent(SelectThemeActivity.this,AddDomain.class));
        }
    }
