package com.amicly.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private int position;
    Helper helper;
    private Book mBook;
    private ArrayList<Book> mBookList = new ArrayList<>();
    GridView cartView;
    private Button purchase;
    private Button backtoShopping;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//        position = 0;
//        Intent intent = getIntent();
//        if(intent != null){
//            position = intent.getIntExtra(MainActivity.KEY_ID, position);
//        }

        mBookList = Singleton.getInstance().getBooks();

//        helper = Helper.getInstance(CartActivity.this);
//        mBook = helper.getMyBook(position);
        //mBooks.add(mBook);

        cartView= (GridView) findViewById(R.id.cart_gridview);
        cartView.setAdapter(new ImageAdapter(this, mBookList));

        purchase = (Button) findViewById(R.id.purchase_button);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.getInstance().addBook(mBook);
                Toast.makeText(CartActivity.this, "Purchased!", Toast.LENGTH_SHORT).show();

            }
        });

        backtoShopping = (Button) findViewById(R.id.continue_shopping);
        backtoShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
