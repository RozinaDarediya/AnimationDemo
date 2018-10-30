package com.theta.animationdemo.retrofit.global;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Window;

import com.theta.animationdemo.R;

/**
 * Created by ashish on 26/4/18.
 */

public class AppDialog {

    private static Dialog d;


    public static void noNetworkDialog(Context _context,
                                       DialogInterface.OnClickListener _onClick) {
        AlertDialog dialog = new AlertDialog.Builder(_context).create();

        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setMessage(_context.getString(R.string.txt_no_network));
        dialog.setButton(Dialog.BUTTON_POSITIVE,
                _context.getString(R.string.txt_ok), _onClick);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showProgressDialog(Context context) {
        d = new Dialog(context, R.style.ProgressDialogAnimation);
        d.getWindow().getAttributes().windowAnimations = R.style.ProgressDialogAnimation;
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d.setCancelable(false);
        d.setCanceledOnTouchOutside(false);
        d.setContentView(R.layout.app_progress_dialog);
        d.show();
    }

    public static void dismissProgressDialog() {
        if (d != null && d.isShowing()) {
            d.dismiss();
        }
    }
}
