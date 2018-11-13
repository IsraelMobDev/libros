package com.apps.mastin.libros.Custom;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.apps.mastin.libros.DataBase.DataBaseHelper;
import com.apps.mastin.libros.R;

public class CustomAlertAddAuthor {

    DataBaseHelper dataBaseHelper;
    public void showDialog(final Context activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_alert_author_add);
        dataBaseHelper = new DataBaseHelper(activity.getApplicationContext());

        final TextView text = (TextView) dialog.findViewById(R.id.newAuthorName);

        Button dialogButton_ok = (Button) dialog.findViewById(R.id.btn_dialog_ok);
        dialogButton_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text.getText().length() > 0){
                    dataBaseHelper.insertAuthor(text.getText().toString());
                    dialog.dismiss();
                }else{
                    Toast.makeText(activity.getApplicationContext(),"Ingrese el nombre de un autor",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button dialogButton_cancel = (Button) dialog.findViewById(R.id.btn_dialog_cancel);
        dialogButton_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
