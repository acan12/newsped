package support.component;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by arysuryawan on 8/10/17.
 */

public class KeyboardComponent {

    public static void hideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if(view == null){ view = new View(activity);}

        imm.hideSoftInputFromWindow(view.getWindowToken(),0);

    }
}
