package com.amicly.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleText;
    private TextView authorText;
    private TextView publisherText;
    private TextView dateText;
    private TextView priceText;
    private String category;
    private String author;
    private String title;
    private String date;
    private String image;
    private double price;
    private String priceDouble;
    private String company;
    private double wholesalePrice;
    private String wholesaleDouble;
    private Button sendToCart;
    public int position;
    Helper helper;
    private Book mBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        position = 0;
        Intent intent = getIntent();
        if(intent != null){
            position = intent.getIntExtra(MainActivity.KEY_ID, position);
        }

        helper = Helper.getInstance(DetailActivity.this);
        mBook = helper.getMyBook(position);

        //mBook = new Book(category, author, title, price, date, image);
        String category = mBook.getCategory();
        String title = mBook.getTitle();
        String author = mBook.getAuthor();
        String date = mBook.getDate();
        double price = mBook.getPrice();
        String priceDouble = Double.toString(price);
        String imageURI = mBook.getImageURI();


        titleText = (TextView) findViewById(R.id.detail_title);
        titleText.setText(title);

        authorText = (TextView) findViewById(R.id.detail_author);
        authorText.setText(author);

        dateText = (TextView) findViewById(R.id.detail_date);
        dateText.setText(date);

        priceText = (TextView) findViewById(R.id.detail_price);
        priceText.setText(priceDouble);

        imageView = (ImageView) findViewById(R.id.detail_image);
        Picasso.with(DetailActivity.this).load(imageURI).into(imageView);

        sendToCart = (Button) findViewById(R.id.detail_cartButton);
        sendToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DetailActivity.this, Cart.class);
                startActivity(intent);

            }
        });


    }
}
