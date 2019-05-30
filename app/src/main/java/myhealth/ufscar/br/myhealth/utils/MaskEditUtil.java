package myhealth.ufscar.br.myhealth.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public abstract class MaskEditUtil {
    public static final String FORMAT_CPF = "###.###.###-##";
    public static final String FORMAT_FONE = "(###)####-#####";
    public static final String FORMAT_CEP = "#####-###";
    public static final String FORMAT_SUS_NUMBER = "### #### #### ####";
    public static final String FORMAT_DATE = "##/##/####";
    public static final String FORMAT_HOUR = "##:##";

    /**
     * Método que deve ser chamado para realizar a formatação
     *
     * @param ediTxt
     * @param mask
     * @return
     */
    public static TextWatcher mask(final EditText ediTxt, final String mask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            @Override
            public void afterTextChanged(final Editable s) {}

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                final String str = MaskEditUtil.unmask(s.toString());
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (final char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (final Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }
        };
    }


    public static String maskString(String value, String mask ){
        String toReturn = "";
        int i = 0;
        for (char c: mask.toCharArray()) {
            if (c != '#') {
                toReturn += c;
                continue;
            }
            try {
                toReturn += value.charAt(i);
            } catch (Exception e) {
                Log.e("MaskEditUtil", "Error on format string");
            }
            i++;
        }

        return toReturn;
    }

    public static String unmask(final String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
    }

}
