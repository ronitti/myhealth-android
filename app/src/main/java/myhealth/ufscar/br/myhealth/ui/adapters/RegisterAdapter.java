package myhealth.ufscar.br.myhealth.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import myhealth.ufscar.br.myhealth.R;
import myhealth.ufscar.br.myhealth.data.collect.Cardiac;
import myhealth.ufscar.br.myhealth.data.collect.Glycemic;
import myhealth.ufscar.br.myhealth.data.collect.Obesity;
import myhealth.ufscar.br.myhealth.data.collect.Register;

public class RegisterAdapter extends RecyclerView.Adapter<RegisterAdapter.ViewHolderRegister> {

    private List<Pair<Integer, Register>> list;
    private LayoutInflater layoutInflater;
    private Context context;


    public RegisterAdapter(Context context, List<Pair<Integer, Register>> list) {
        this.list = list;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @NonNull
    @Override
    public RegisterAdapter.ViewHolderRegister onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_list_register, viewGroup, false);
        ViewHolderRegister holderRegister = new ViewHolderRegister(view);
        return holderRegister;
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterAdapter.ViewHolderRegister viewHolder, int i) {
        Log.i("LOG", "onBindViewHolder");
        Register register = list.get(i).second;
        int status = list.get(i).first;

        if (register != null) {
            String txt_title = "";

            if (register instanceof Cardiac) {
                Log.i("LogRegister: ", "is instance of cardiac" + i);
                txt_title += ("Sys: " + ((Cardiac) register).getSystolic());
                txt_title += (" Dia: " + ((Cardiac) register).getDiastolic());
                txt_title += (" Pulses: " + ((Cardiac) register).getHeartBeats());
                txt_title += (" Weight: " + ((Cardiac) register).getWeight());
            } else if (register instanceof Glycemic) {
                txt_title += ("Glycemic Rate: " + ((Glycemic) register).getGlycemicRate());

            } else if (register instanceof Obesity) {
                txt_title += ("Weight: " + ((Obesity) register).getWeight());
                txt_title += (" Fat Rate: " + ((Obesity) register).getBodyfat());
            }


            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            viewHolder.txt_view_title.setText(txt_title);
            if(register.getTimestamp() != null) {
                viewHolder.txt_view_date.setText(dateFormat.format(register.getTimestamp()));
                viewHolder.txt_view_time.setText(timeFormat.format(register.getTimestamp()));
            }
            Log.i("RegisterAdapter", "Register Status " + status);
            if (status == 1) {

                viewHolder.img_sync.setBackgroundResource(R.drawable.ic_check_circle);
            } else {
                viewHolder.img_sync.setBackgroundResource(R.drawable.ic_sync);
            }
            viewHolder.img_sync.getBackground().setColorFilter(new PorterDuffColorFilter(context.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN));
        }


    }

    @Override
    public int getItemCount() {
        Log.i("Log: ", "itemCount " + list.size());
        return list.size();
    }

    public class ViewHolderRegister extends RecyclerView.ViewHolder {

        public TextView txt_view_title;
        public TextView txt_view_date;
        public TextView txt_view_time;
        public ImageView img_sync;

        public ViewHolderRegister(View itemView) {
            super(itemView);

            txt_view_title = (TextView) itemView.findViewById(R.id.txt_view_title);
            txt_view_date = (TextView) itemView.findViewById(R.id.txt_view_date);
            txt_view_time = (TextView) itemView.findViewById(R.id.txt_view_time);
            img_sync = (ImageView) itemView.findViewById(R.id.img_sync);


        }
    }

    public void setList(List<Pair<Integer, Register>> list) {
        this.list = list;
    }
}
