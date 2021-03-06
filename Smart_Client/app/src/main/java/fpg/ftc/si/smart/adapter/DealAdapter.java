/*
 * Copyright (c) 2014 FTC Inc. All rights reserved.
 */

package fpg.ftc.si.smart.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fpg.ftc.si.smart.model.AbnormalItem;
import fpg.ftc.si.smart.model.DealMethodItem;

import static fpg.ftc.si.smart.util.LogUtils.makeLogTag;

/**
 * 異常
 * This {@link android.widget.ArrayAdapter} is used to display all of the route on a user's
 * device for {@link }.
 *
 * @author MarlinJoe
 */
public class DealAdapter extends ArrayAdapter<DealMethodItem> {

    private static final String TAG = makeLogTag(DealAdapter.class);

    /**
     * Used to cache the data
     */
	private List<DealMethodItem> mDataSource;
    private Activity mContext;
    private int mLayoutId;//View資源

    /**
     * Constructor of <code>RouteListAdapter</code>
     *
     * @param context The {@link android.content.Context} to use.

     */
    public DealAdapter(final Activity context) {
        super(context, 0);
        mContext = context;
        mDataSource = new ArrayList<DealMethodItem>();
        mLayoutId = android.R.layout.simple_list_item_1;
    }



    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public DealMethodItem getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (mDataSource != null)
            return mDataSource.get(position).hashCode();
        return 0;
    }

    /**
     * 取得索引值的位置
     * @param key
     * @return
     */
    public int getPosition(String key)
    {
        int result = -1;
        int idx = 0;
        for(DealMethodItem dealMethodItem :mDataSource)
        {
            if(dealMethodItem.getDEALID().equals(key))
            {
                result = idx;
                break;
            }
            idx++;

        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Recycle ViewHolder's items
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mLayoutId, parent, false);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Retrieve the data holder
        final DealMethodItem dataHolder = mDataSource.get(position);

        holder.txtName.setText(dataHolder.getDEALNAME());
        return convertView;
    }


    public static class ViewHolder {

        public TextView txtName;

    }

    public void setItemList(List<DealMethodItem> itemList) {

        this.mDataSource = itemList;
        this.notifyDataSetChanged();

    }

    /**
     * 需要提供此方法才能有下拉
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getDropDownView(final int position, View convertView, final ViewGroup parent) {

        return getView(position,convertView,parent);
    }

}
