package com.luohong.phoneobserver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luohong.phoneobserver.R;
import com.luohong.phoneobserver.bean.AppInfo;

import java.util.List;

/**
 * 应用启动时间ListView的适配器
 */
public class StatsTimeListViewAdapter extends BaseAdapter {

    private List<AppInfo> infos;
    private Context context;

    public StatsTimeListViewAdapter(Context context, List<AppInfo> infos) {
        this.context = context;
        this.infos = infos;

    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.stats_lv_item, null);

            holder.AppIcon = (ImageView) convertView.findViewById(R.id.iv_stas_AppIcon);
            holder.AppName = (TextView) convertView.findViewById(R.id.iv_stats_AppName);
            holder.AppTime = (TextView) convertView.findViewById(R.id.iv_stats_AppTime);

            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();

        holder.AppIcon.setImageDrawable(infos.get(position).getAppIcon());
        holder.AppName.setText(infos.get(position).getAppName());

        return convertView;
    }

    public class ViewHolder {
        private ImageView AppIcon;
        private TextView AppName;
        private TextView AppTime;

    }
}
