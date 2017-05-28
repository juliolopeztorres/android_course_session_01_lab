package oob.myform;


import android.content.Context;
import android.widget.Toast;

class Utils {
    static final int ID_NOT_FOUND = -1;

    /**
     * @param context Context
     * @param message String
     */
    static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * @param string String
     * @return boolean
     */
    static boolean checkValidString(String string) {
        return (string != null && !string.isEmpty());
    }
}
