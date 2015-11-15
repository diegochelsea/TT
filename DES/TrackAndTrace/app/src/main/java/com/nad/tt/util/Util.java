package com.nad.tt.util;

import android.app.Activity;
import android.content.Intent;
import android.media.effect.EffectUpdateListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nad.tt.activity.login.R;
import com.nad.tt.activity.status.StatusActivity;
import com.nad.tt.activity.user.UserActivity;
import com.nad.tt.comun.enumeration.ElementDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Diego on 22/10/2015.
 */
public final class Util {


    private transient static Pattern pattern;
    private transient static Matcher matcher;

    /**
     * Called when the user touch some item menu
     */
    public static void startActivityByClass(Class<?> classs, Activity activity) {
        Intent intent = new Intent(activity.getApplicationContext(), classs);
        activity.startActivity(intent);
    }

    /**
     * Called when the user close some activity
     */
    public static void cerrarView(Activity activity) {
        activity.finish();
    }

    /**
     * Show contextual menu
     *
     * @param id       menu id
     * @param activity current activity
     * @return boolean
     */
    public static boolean showMenu(int id, Activity activity) {
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_catalog_status:
                Util.startActivityByClass(StatusActivity.class, activity);
                return true;
            case R.id.action_catalog_user:
                Util.startActivityByClass(UserActivity.class, activity);
                return true;
            default:
//                Util.startActivityByClass();

                return false;
        }
    }

    private static boolean notIsEmpty(EditText editText) {
        return editText != null && !getString(editText).isEmpty();
    }


    private static String getString(EditText editText) {
        return editText.getText().toString();
    }

    public static boolean isValidComponent(ElementDTO elementDTO) {
        boolean result =  true;
        Log.d(Constants.LOG_NAD, "isValidComponent");
        Log.d(Constants.LOG_NAD, "Values: " + elementDTO.isRequired() + ", "
                + elementDTO.getLblNameElement() + ", " + elementDTO.getRegexp() + ", "
                + elementDTO.getEditText().getText().toString());
        if (elementDTO.isRequired()) {
            Log.d(Constants.LOG_NAD, "1");
            if (!notIsEmpty(elementDTO.getEditText())) {
                Log.d(Constants.LOG_NAD, "2");
                elementDTO.getTxtMsgError().setText(getMsgRequiredError(elementDTO.getLblNameElement()));
                elementDTO.getTxtMsgError().setVisibility(View.VISIBLE);
                result = false;
            } else {
                Log.d(Constants.LOG_NAD, "3");
                if (!isValidRegexp(getString(elementDTO.getEditText()), elementDTO.getRegexp())) {
                    Log.d(Constants.LOG_NAD, "4");
                    elementDTO.getTxtMsgError().setText(getMsgInvalidError(elementDTO.getLblNameElement()));
                    elementDTO.getTxtMsgError().setVisibility(View.VISIBLE);
                    result = false;
                } else {
                    resetLblError(elementDTO.getTxtMsgError());

                }
            }
        } else {
            Log.d(Constants.LOG_NAD, "5");
            if (notIsEmpty(elementDTO.getEditText()) && !isValidRegexp(getString(elementDTO.getEditText()), elementDTO.getRegexp())) {
                Log.d(Constants.LOG_NAD, "6");
                elementDTO.getTxtMsgError().setText(getMsgInvalidError(elementDTO.getLblNameElement()));
                elementDTO.getTxtMsgError().setVisibility(View.VISIBLE);
                result = false;
            }
        }
        return  result;
    }

    private static String getMsgRequiredError(String value) {
        return new StringBuilder(Constants.THE).append(value).append(Constants.FIELD_REQUIRED).toString();
    }

    private static String getMsgInvalidError(String value) {
        return new StringBuilder(Constants.THE).append(value).append(Constants.FIELD_INVALID_FORMAT).toString();
    }

    public static boolean isValidRegexp(String string, String regexp) {
        pattern = Pattern.compile(regexp);
        matcher = pattern.matcher(string);
        return matcher.find();
    }

    private static void resetLblError(TextView textView){
        textView.setText(Constants.EMPTY_STRING);
        textView.setVisibility(View.GONE);
    }
}
