package co.logicart.modernartui;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.app.Dialog;
import android.content.Intent;
import android.app.AlertDialog;
import android.os.Bundle;
import android.net.Uri;

/**
 * Created by Athens on 4/3/15.
 */
public class MoreInformationDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.more_information_dilog_title)
                .setMessage(R.string.more_information_dilog_message)
                .setPositiveButton(R.string.more_information_dilog_OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final String url = "http://www.moma.org";
                        final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);
                    }
                })
                .setNegativeButton(R.string.more_information_dilog_Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
