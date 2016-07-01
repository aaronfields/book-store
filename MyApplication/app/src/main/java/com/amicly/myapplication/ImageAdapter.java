package com.amicly.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by aaronfields on 6/24/16.
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Book> mBooks;
    private DecimalFormat df;

    // Constructor for ImageAdapter
    public ImageAdapter(Context c, ArrayList<Book> b) {
        mContext = c;
        mBooks = b;
    }

    public int getCount() {
        return mBooks.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return mBooks.get(position).getID();
    }

    // Set database items to layout
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

            gridView = inflater.inflate(R.layout.grid_item, null);

            imageView = (ImageView) gridView.findViewById(R.id.grid_image);
            TextView gridTitle = (TextView) gridView.findViewById(R.id.grid_title);
            TextView gridAuthor = (TextView) gridView.findViewById(R.id.grid_author);
            TextView gridPrice = (TextView) gridView.findViewById(R.id.grid_price);

            Book Bookb = mBooks.get(position);
            df = new DecimalFormat("0.00");

            String title = Bookb.getTitle();
            String author = Bookb.getAuthor();
            String year = Bookb.getDate();
            double price = Bookb.getPrice();
            String priceDouble = Double.toString(price);
            String imageURI = Bookb.getImageURI();

            gridTitle.setText(title);
            gridAuthor.setText(author);
            gridPrice.setText("$"+df.format(price));

            Picasso.with(mContext).load(imageURI).into(imageView);

        return gridView;
    }

}

