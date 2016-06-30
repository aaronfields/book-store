package com.amicly.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

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
    private String publisher;
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
    private Publisher mPublisher;
    public static final String KEY_ID2 = "Item ID2";
    private ArrayList<Book> mBooks = new ArrayList<>();
    private Button checkout;
    private DecimalFormat df;


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
        mPublisher = helper.getPublisher(position);


        //mBook = new Book(category, author, title, price, date, image);
        //String category = mBook.getCategory();
        String title = mBook.getTitle();
        String author = mBook.getAuthor();
        String publisher = mPublisher.getCompany();
        String date = mBook.getDate();
        final double price = mBook.getPrice();
        String priceDouble = Double.toString(price);
        String imageURI = mBook.getImageURI();

        df = new DecimalFormat("0.00");

        titleText = (TextView) findViewById(R.id.detail_title);
        titleText.setText(title);

        authorText = (TextView) findViewById(R.id.detail_author);
        authorText.setText(author);

        publisherText = (TextView) findViewById(R.id.detail_publisher);
        publisherText.setText("Publisher: "+publisher);

        dateText = (TextView) findViewById(R.id.detail_date);
        dateText.setText("Year Published: "+date);

        priceText = (TextView) findViewById(R.id.detail_price);
        priceText.setText("$"+df.format(price));

        imageView = (ImageView) findViewById(R.id.detail_image);
        Picasso.with(DetailActivity.this).load(imageURI).into(imageView);

        sendToCart = (Button) findViewById(R.id.detail_cartButton);
        sendToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance().addBook(mBook);
                Toast.makeText(DetailActivity.this, "Added to Cart!", Toast.LENGTH_SHORT).show();

            }
        });

        checkout = (Button) findViewById(R.id.checkout_button);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


    }
}
