package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import myhealth.ufscar.br.myhealth.R;

public class MonitoringSettingsFragment extends Fragment {
    private RadioButton rdDaily;
    private RadioButton rdWeekly;
    private RadioButton rdCustom;

    private LinearLayout layoutCustom;

    private EditText txtCustomTimes;
    private Spinner spnCustomType;

    private LinearLayout layoutWeekly;
    private CheckBox chkSunday;
    private CheckBox chkMonday;
    private CheckBox chkTuesday;
    private CheckBox chkWednesday;
    private CheckBox chkThursday;
    private CheckBox chkFriday;
    private CheckBox chkSaturday;

    private LinearLayout layoutTimesADay;

    public MonitoringSettingsFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monitoring_settings, container, false);
        initializeComponents(view);
        return view;
    }

    private void initializeComponents(View view){
        rdDaily = view.findViewById(R.id.rd_daily);
        rdWeekly = view.findViewById(R.id.rd_weekly);
        rdCustom = view.findViewById(R.id.rd_custom);

        layoutCustom = view.findViewById(R.id.layout_custom);

        txtCustomTimes = view.findViewById(R.id.txt_custom_times);

        spnCustomType = view.findViewById(R.id.spn_custom_type);
        String[] arraySpinner = new String[] {"dias", "semanas"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCustomType.setAdapter(adapter);

        layoutWeekly = view.findViewById(R.id.layout_weekly);
        chkSunday = view.findViewById(R.id.chk_sunday);
        chkMonday = view.findViewById(R.id.chk_monday);
        chkTuesday = view.findViewById(R.id.chk_tuesday);
        chkWednesday = view.findViewById(R.id.chk_wednesday);
        chkThursday = view.findViewById(R.id.chk_thursday);
        chkFriday = view.findViewById(R.id.chk_friday);
        chkSaturday = view.findViewById(R.id.chk_saturday);

        layoutTimesADay = view.findViewById(R.id.layout_times_a_day);
        initFieldsListeners();
    }


    public void initFieldsListeners() {
        rdDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    layoutCustom.setVisibility(View.GONE);
                    layoutWeekly.setVisibility(View.GONE);
                }
            }
        });
        rdWeekly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    layoutCustom.setVisibility(View.GONE);
                    layoutWeekly.setVisibility(View.VISIBLE);
                }
            }
        });
        rdCustom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    layoutCustom.setVisibility(View.VISIBLE);
                    layoutWeekly.setVisibility(View.GONE);
                }
            }
        });
        spnCustomType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (id == 0) {
                    layoutWeekly.setVisibility(View.GONE);
                } else {
                    layoutWeekly.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
