package com.callisto.d5proj.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.method.DigitsKeyListener;
import android.widget.EditText;

import com.callisto.d5proj.R;
import com.callisto.d5proj.interfaces.OnInputClickListener;

/**
 * Created by Crux on 08/02/2015.
 */
public class InputDialog {

    private final Context context;
    private final String title;
    private final String message;

    private EditText input;
    private OnInputClickListener mOnInputClickListener;

    public InputDialog(Context context, String title, String message, OnInputClickListener onInputClickListener) {
        this.context = context;
        this.title = title;
        this.message = message;
        this.mOnInputClickListener = onInputClickListener;
    }

    private String getText() {
        Editable editable = input.getText();
        return editable == null ? "" : editable.toString();
    }

    public void show() {
        input = new EditText(context);
        input.setKeyListener(DigitsKeyListener.getInstance());
        AlertDialog.Builder alert = new AlertDialog.Builder(context);

        alert.setTitle(title);
        alert.setMessage(message);

        // Set an EditText view to get user input
        alert.setView(input);

        alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mOnInputClickListener.onInputClickOk(getText());
            }
        });
        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                mOnInputClickListener.onInputClickCancel(getText());
            }
        });
        alert.show();
    }
}
