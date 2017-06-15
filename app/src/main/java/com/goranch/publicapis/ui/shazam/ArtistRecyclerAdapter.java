package com.goranch.publicapis.ui.shazam;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.goranch.publicapis.R;
import com.goranch.publicapis.api.model.shazam.Item;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistRecyclerAdapter.ViewHolder>{
    private final TrendingPresenter presenter;
    private ArrayList<Item> items = new ArrayList<>();

    ArtistRecyclerAdapter(TrendingPresenter presenter, ArrayList<Item> items) {
        this.items = items;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Item item = items.get(position);
        holder.mItem = item;
        holder.title.setText(item.getHeading().getTitle());
        holder.subtitle.setText(item.getHeading().getSubtitle());

        String imageUrl = item.getImages().getDefault();
        if (imageUrl == null)
            imageUrl = ""; //fresco Crashes with a null object

        holder.image.setImageURI(Uri.parse(imageUrl));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_title)
        public TextView title;

        @BindView(R.id.tv_subtitle)
        public TextView subtitle;

        @BindView(R.id.iv_art_image)
        public SimpleDraweeView image;

        Item mItem;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            presenter.onItemClicked(mItem);

        }
    }
}
