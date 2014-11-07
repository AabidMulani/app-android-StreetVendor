package com.dap.hawkers.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dap.hawkers.BaseActivity;
import com.dap.hawkers.R;
import com.dap.hawkers.database.InformationTable;
import com.dap.hawkers.utils.Utils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by AABID on 07-11-2014.
 */
public class ListAllActivity extends BaseActivity {

    @InjectView(R.id.textView_nothing)
    TextView noDataTextView;

    @InjectView(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);
        ButterKnife.inject(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color
                .theme_main_color)));
        List<InformationTable> informationTables = InformationTable.listAll(InformationTable.class);
        if (informationTables.size() > 0) {
            listView.setVisibility(View.VISIBLE);
            noDataTextView.setVisibility(View.GONE);
            listView.setAdapter(new ShowDataAdaptor(activity, informationTables));
        } else {
            listView.setVisibility(View.GONE);
            noDataTextView.setVisibility(View.VISIBLE);
        }

    }


    class ShowDataAdaptor extends BaseAdapter {
        private Context context;
        private LayoutInflater inflater;
        public List<InformationTable> items;

        public ShowDataAdaptor(Context context, List<InformationTable> items) {
            this.context = context;
            this.items = items;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            final ViewHolder viewholder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.inflater_show_data, parent, false);
                viewholder = new ViewHolder(convertView);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolder) convertView.getTag();
            }
            InformationTable item = getItem(position);
            viewholder.nameTextView.setText(item.getName());
            viewholder.fatherTextView.setText(item.getFather_name());
            viewholder.addressTextView.setText(item.getPermanent_address());
            viewholder.mobileTextView.setText(item.getMobile_number());
            viewholder.ageTextView.setText(item.getAge());
            viewholder.genderTextView.setText(item.getGender());
            return convertView;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public InformationTable getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class ViewHolder {
            @InjectView(R.id.textView_name)
            TextView nameTextView;
            @InjectView(R.id.textView_father)
            TextView fatherTextView;
            @InjectView(R.id.textView_address)
            TextView addressTextView;
            @InjectView(R.id.textView_mobile)
            TextView mobileTextView;
            @InjectView(R.id.textView_age)
            TextView ageTextView;
            @InjectView(R.id.textView_gender)
            TextView genderTextView;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }

    }
}
