package myhealth.ufscar.br.myhealth.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

public abstract class EditTextUtils {

    public static final String FORMAT_CEP = "#####-###";
    public static final String FORMAT_SUS_NUMBER = "### #### #### ####";

    public static TextWatcher mask(final String mask) {
        return new TextWatcher() {
            private boolean isRunning = false;
            private boolean isDeleting = false;

            @Override
            public void afterTextChanged(final Editable s) {
                if(isDeleting){
                    int editableLength = s.length();
                    if (editableLength > 0 && mask.charAt(editableLength-1) != '#') {
                        s.delete(editableLength-1, editableLength);
                    }
                    isDeleting = false;
                    return;
                }

                if (isRunning) {
                    return;
                }
                isRunning = true;

                int editableLength = s.length();
                if (editableLength < mask.length() && editableLength > 0) {
                    if (mask.charAt(editableLength) != '#') {
                        s.append(mask.charAt(editableLength));
                    } else if (mask.charAt(editableLength-1) != '#') {
                        s.insert(editableLength-1, mask, editableLength-1, editableLength);
                    }
                }else if(editableLength > mask.length()){
                    s.delete(mask.length(), editableLength);
                }
                isRunning = false;
            }

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
                isDeleting = count > after;
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

            }
        };
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
    }


    public static boolean isValidEmail(CharSequence target) {
        if (!TextUtils.isEmpty(target)) {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
        return false;
    }
    public static boolean isValidPassword(CharSequence target){
        return !TextUtils.isEmpty(target);
    }
}