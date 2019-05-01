package myhealth.ufscar.br.myhealth.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.InputType;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.data.collect.frequency.DayOfWeek;
import myhealth.ufscar.br.myhealth.data.collect.frequency.Frequency;
import myhealth.ufscar.br.myhealth.data.collect.frequency.FrequencyType;
import myhealth.ufscar.br.myhealth.ui.RegisterActivity;

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
    private TextInputEditText txtTimesADay;
    private TextInputEditText txtHoursOfDay[];
    private Frequency frequency;

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
        String[] arraySpinner = new String[] {getString(R.string.settings_item_days), getString(R.string.settings_item_weeks)};
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
        txtTimesADay = view.findViewById(R.id.txt_times_a_day);

        frequency = ((RegisterActivity) Objects.requireNonNull(getActivity())).getFrequency();
        initFieldsListeners();
    }


    public void initFieldsListeners() {
        rdDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    layoutCustom.setVisibility(View.GONE);
                    layoutWeekly.setVisibility(View.GONE);

                    frequency.setFrequencyType(FrequencyType.DAILY);
                }
            }
        });
        rdWeekly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    layoutCustom.setVisibility(View.GONE);
                    layoutWeekly.setVisibility(View.VISIBLE);

                    frequency.setFrequencyType(FrequencyType.WEEKLY);
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
                    frequency.setFrequencyType(FrequencyType.CUSTOM_DAYS);
                } else {
                    layoutWeekly.setVisibility(View.VISIBLE);
                    frequency.setFrequencyType(FrequencyType.CUSTOM_WEEKS);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtCustomTimes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                    frequency.setCustomEvery(Integer.parseInt(txtCustomTimes.getText().toString()));
            }
        });
        txtTimesADay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    frequency.setTimesADay(Integer.parseInt(Objects.requireNonNull(txtTimesADay.getText()).toString()));
                    if (layoutTimesADay.getChildCount() > 0)
                        layoutTimesADay.removeAllViews();
                    frequency.setHoursOfDay(new Date[frequency.getTimesADay()]);
                    txtHoursOfDay = new TextInputEditText[frequency.getTimesADay()];
                    for(int i=0; i<frequency.getTimesADay(); i++){
                        frequency.getHoursOfDay()[i] = new Date();
                        txtHoursOfDay[i] = new TextInputEditText(Objects.requireNonNull(getActivity()));
                        txtHoursOfDay[i].setInputType(InputType.TYPE_DATETIME_VARIATION_TIME);
                        txtHoursOfDay[i].setText("12:00");
                        layoutTimesADay.addView(txtHoursOfDay[i],i);
                        txtHoursOfDay[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            private int i;
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {
                                if(!hasFocus) {
                                    try {
                                        frequency.getHoursOfDay()[i] = DateFormat.getDateInstance().parse(Objects.requireNonNull(txtHoursOfDay[i].getText()).toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            View.OnFocusChangeListener init(int i){
                                this.i = i;
                                return this;
                            }
                        }.init(i));
                    }
                }
            }
        });

        chkSunday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                frequency.getDaysOfWeek()[DayOfWeek.SUNDAY.getDay()] = isChecked;
            }
        });
        chkMonday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                frequency.getDaysOfWeek()[DayOfWeek.MONDAY.getDay()] = isChecked;
            }
        });
        chkTuesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                frequency.getDaysOfWeek()[DayOfWeek.TUESDAY.getDay()] = isChecked;
            }
        });
        chkWednesday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                frequency.getDaysOfWeek()[DayOfWeek.WEDNESDAY.getDay()] = isChecked;
            }
        });
        chkThursday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                frequency.getDaysOfWeek()[DayOfWeek.THURSDAY.getDay()] = isChecked;
            }
        });
        chkFriday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                frequency.getDaysOfWeek()[DayOfWeek.FRIDAY.getDay()] = isChecked;
            }
        });
        chkSaturday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                frequency.getDaysOfWeek()[DayOfWeek.SATURDAY.getDay()] = isChecked;
            }
        });
    }
}
