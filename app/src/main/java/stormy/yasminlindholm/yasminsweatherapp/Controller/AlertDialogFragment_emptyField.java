package stormy.yasminlindholm.yasminsweatherapp.Controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import stormy.yasminlindholm.yasminsweatherapp.R;

/**
 * Created by yasmin.lindholm on 2015-07-31.
 */
public class AlertDialogFragment_emptyField extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Context context = getActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.error_oups))
                    .setMessage(context.getString(R.string.error_fillInAllFields))
                    .setPositiveButton(context.getString(R.string.error_OK_buttonText), null);
            AlertDialog dialog = builder.create();
            return dialog;
        }
    }


