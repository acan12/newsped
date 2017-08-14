package com.toped.app.newsped.ui.component;

import android.app.ProgressDialog;
import android.content.Context;

import com.toped.app.newsped.R;

/**
 * Created by arysuryawan on 8/12/17.
 */

public class DialogComponent {

    public static ProgressDialog setupLoadingProgress(Context context) {

        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(context.getResources().getString(R.string.loading));
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        return dialog;

    }
}
