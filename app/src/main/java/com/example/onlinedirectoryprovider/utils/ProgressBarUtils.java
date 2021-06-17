package com.example.onlinedirectoryprovider.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.onlinedirectoryprovider.R;


public class ProgressBarUtils {

    static Dialog dialogBuilder = null;

    public static void showProgressDialog(Context context) {
        if (dialogBuilder != null)
            dialogBuilder.dismiss();
        dialogBuilder = new Dialog(context, R.style.MaterialSearch);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View dialogView = inflater.inflate(R.layout.activity_progress_bar, null);
        dialogBuilder.setContentView(dialogView);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setCanceledOnTouchOutside(false);
        dialogBuilder.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        try {
            dialogBuilder.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void hideProgressDialog() {
        try {
            if (dialogBuilder != null)
                dialogBuilder.dismiss();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setVisibility(int visible) {
    }

    public boolean isLoading() {
        return dialogBuilder != null && dialogBuilder.isShowing();
    }
}