package com.theta.animationdemo.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.theta.animationdemo.R;
import com.theta.animationdemo.retrofit.model.MlsDataItem;

import java.util.ArrayList;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w150";

    private List<MlsDataItem> mlsDataItemsList;
    private Context context;
    private boolean isLoadingAdded = false;

    public PaginationAdapter( Context context) {
        this.context = context;
        mlsDataItemsList = new ArrayList<>();
    }

    /*public List<MlsDataItem> getMovies() {
        return movieResults;
    }

    public void setMovies(List<MlsDataItem> movieResults) {
        this.movieResults = movieResults;
    }*/

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingVH(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.mls_data_row, parent, false);
        viewHolder = new MyViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MlsDataItem MlsDataItem = mlsDataItemsList.get(position); // data
        switch (getItemViewType(position)) {
            case ITEM:
                final MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.product_name.setText(MlsDataItem.getProductName());
                myViewHolder.product_price.setText(String.valueOf(MlsDataItem.getMrp()));
                myViewHolder.product_desc.setText(MlsDataItem.getDescription());
                Glide.with(context)
                        .load(MlsDataItem.getImage())
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                myViewHolder.product_progress.setVisibility(View.GONE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                myViewHolder.product_progress.setVisibility(View.GONE);
                                return false;
                            }
                        })
                        .diskCacheStrategy(DiskCacheStrategy.ALL)   // cache both original & resized image
                        .centerCrop()
                        .crossFade()
                        .into(myViewHolder.product_poster);
                break;
            case LOADING:
//                Do nothing
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mlsDataItemsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mlsDataItemsList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

     /*
   Helpers
   _________________________________________________________________________________________________
    */

    public void add(MlsDataItem r) {
        mlsDataItemsList.add(r);
        notifyItemInserted(mlsDataItemsList.size() - 1);
    }

    public void addAll(List<MlsDataItem> moveResults) {
        for (MlsDataItem MlsDataItem : moveResults) {
            add(MlsDataItem);
        }
    }

    public void remove(MlsDataItem r) {
        int position = mlsDataItemsList.indexOf(r);
        if (position > -1) {
            mlsDataItemsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new MlsDataItem());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mlsDataItemsList.size() - 1;
        MlsDataItem MlsDataItem = getItem(position);

        if (MlsDataItem != null) {
            mlsDataItemsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public MlsDataItem getItem(int position) {
        return mlsDataItemsList.get(position);
    }

     /*
   View Holders
   _________________________________________________________________________________________________
    */

    /**
     * Main list's content ViewHolder
     */

    protected class MyViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar product_progress;
        private ImageView product_poster;
        private TextView product_price;
        private TextView product_name;
        private TextView product_desc;

        public MyViewHolder(View itemView) {
            super(itemView);

            product_progress = itemView.findViewById(R.id.product_progress);
            product_poster = itemView.findViewById(R.id.product_poster);
            product_price = itemView.findViewById(R.id.product_price);
            product_name = itemView.findViewById(R.id.product_name);
            product_desc = itemView.findViewById(R.id.product_desc);
        }
    }

    protected class LoadingVH extends RecyclerView.ViewHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }
}
