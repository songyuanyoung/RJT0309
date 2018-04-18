package com.ZachYang.rjt0309;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by zhangwenpurdue on 6/27/2017.
 */

public class FragHomeAdapterTest extends RecyclerView.Adapter<FragHomeAdapterTest.MyViewHolder> {

    private Context mContext;
    private List<SubCategory> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.category_name);
            count = (TextView) view.findViewById(R.id.category_id);
            thumbnail = (ImageView) view.findViewById(R.id.category_image);
        }
    }


    public FragHomeAdapterTest(Context mContext, List<SubCategory> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final SubCategory subCategory = albumList.get(position);

        holder.title.setText(subCategory.getGrocerySubCategoryName());
        holder.count.setText(subCategory.getGrocerySubCategoryId());

        // loading album cover using Glide library
        Picasso.with(mContext).load(subCategory.getGroceryThumb()).into(holder.thumbnail);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frag_Product frag_product = Frag_Product.newInstance("category_id=" + subCategory.getGroceryCategoryId() + "&sub_category_id=" + subCategory.getGrocerySubCategoryId());
                ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, frag_product).commit();
            }
        });

/*
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
        */
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    /*
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }
*/
    /**
     * Click listener for popup menu items
     */
    /*
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
*/
    @Override
    public int getItemCount() {
        return albumList.size();
    }
}

