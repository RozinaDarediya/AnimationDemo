package com.theta.animationdemo.ExpandableView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theta.animationdemo.R;

import java.util.ArrayList;

/**
 * Created by ashish on 6/12/17.
 */

public class ExpandableAdapter extends RecyclerView.Adapter<ExpandableAdapter.MyHolder> {
    Context context;
    ArrayList<Data> dataList;
    Animation animFadein;
    OnExpandableClickListener listener;


    public ExpandableAdapter(Context context, ArrayList<Data> dataList, OnExpandableClickListener listener) {
        this.context = context;
        this.dataList = dataList;
        this.listener = listener;
        animFadein = AnimationUtils.loadAnimation(context,
                R.anim.bottom_up);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_row, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        Data dataItem = dataList.get(position);
        holder.txtLable.setText(dataItem.getLable());
        holder.txtDetail.setText(dataItem.getDetail());
        holder.txtLable.setTag(position);


        holder.txtLable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               int tag = (int) view.getTag();
                Toast.makeText(context, "You have clicked on item : " + tag , Toast.LENGTH_SHORT).show();
               listener.onExpandableClick(holder.imageView, context.getResources().getString(R.string.ivTransition), tag);
              /* if (tag == position) {
                   if (holder.txtDetail.getVisibility() == View.GONE) {
                       animFadein = AnimationUtils.loadAnimation(context,
                               R.anim.bottom_up);
                       holder.txtDetail.setVisibility(View.VISIBLE);
                       holder.txtDetail.startAnimation(animFadein);

                       //Global.showAnimation(holder.txtDetail);
                   } else {
                       animFadein = AnimationUtils.loadAnimation(context,
                               R.anim.bottom_down);
                       holder.txtDetail.startAnimation(animFadein);
                       holder.txtDetail.setVisibility(View.GONE);

                        // Global.hideCircularReveal(holder.txtDetail);
                   }
               }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView txtLable;
        TextView txtDetail;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            txtLable = (TextView) itemView.findViewById(R.id.txtLable);
            txtDetail = (TextView) itemView.findViewById(R.id.txtDetail);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
