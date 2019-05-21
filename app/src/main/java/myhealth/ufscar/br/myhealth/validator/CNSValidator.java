package myhealth.ufscar.br.myhealth.validator;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class CNSValidator implements TextWatcher {

    private EditText editText;

    public CNSValidator(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i("CNSValidator: ", s.toString());
        String cns = s.toString();
        cns = cns.replace(" ", "");
        Log.i("CNSValidator", "Length CNS: " + cns.length() + " CNS: " + cns);
        if(cns.length() >= 15 && !isValid(cns)) {
            editText.setError("SUS number is invalid");

        } else {
            editText.setError(null);

        }


    }

    private boolean isValid(String s) {
        if (s.matches("[1-2]\\d{10}00[0-1]\\d") || s.matches("[7-9]\\d{14}")) {
            return somaPonderada(s) % 11 == 0;
        }
        return false;
    }

    private int somaPonderada(String s) {
        char[] cs = s.toCharArray();
        int soma = 0;
        for (int i = 0; i < cs.length; i++) {
            soma += Character.digit(cs[i], 10) * (15 - i);
        }
        return soma;
    }
}
