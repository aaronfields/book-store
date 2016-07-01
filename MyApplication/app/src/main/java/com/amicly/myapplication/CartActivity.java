package com.amicly.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private int position;
    Helper helper;
    private Book mBook;
    private ArrayList<Book> mBookList = new ArrayList<>();
    GridView cartView;
    private Button purchase;
    private Button backtoShopping;
    private int mIndex;
    public static final String KEY_ID = "Item ID";
    ImageAdapter mImageAdapter;
    double total;
    double priceToAdd;
    private String myTotal;
    private DecimalFormat df;
    TextView totalCost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mBookList = Singleton.getInstance().getBooks();
        totalCost = (TextView) findViewById(R.id.total);
        cartView= (GridView) findViewById(R.id.cart_gridview);

        mImageAdapter = new ImageAdapter(this, mBookList);
        cartView.setAdapter(mImageAdapter);

        df = new DecimalFormat("0.00");

        total = 0;
        totalCost.setText("Total: $" + df.format(total));
        for(int mIndex = 0; mIndex < mBookList.size(); mIndex++) {
            total += mBookList.get(mIndex).getPrice();
            totalCost.setText("Total: $"+df.format(total));

        }

        purchase = (Button) findViewById(R.id.purchase_button);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        cartView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {
                final int mItem = i;

                AlertDialog.Builder deleteItem = new AlertDialog.Builder(CartActivity.this);
                deleteItem.setTitle("Remove from cart?");

                deleteItem.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Singleton.getInstance().getBooks().remove(mItem);
                        mImageAdapter.notifyDataSetChanged();
                        total = 0;
                        if(mBookList.size()>0){
                        for(int mIndex = 0; mIndex < mBookList.size(); mIndex++) {
                            total += mBookList.get(mIndex).getPrice();
                            totalCost.setText("Total: $" + df.format(total));
                        }
                            }else {
                            total = 0;
                            totalCost.setText("Total: $" + df.format(total));
                        }
                    }
                });
                deleteItem.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                deleteItem.show();
            }
        });

//        cartView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent (CartActivity.this, DetailActivity.class);
//                intent.putExtra(KEY_ID, mBookList.get(i).getID());
//                startActivity(intent);
//            }
//        });

//        cartView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(CartActivity.this, "It works!", Toast.LENGTH_SHORT).show();
//
//                AlertDialog.Builder deleteItem = new AlertDialog.Builder(CartActivity.this);
//                deleteItem.setTitle("Remove from cart?");
//
//                deleteItem.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Singleton.getInstance().getBooks().remove(i);
//                    }
//                });
//                deleteItem.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.cancel();
//                    }
//                });
//                deleteItem.show();
//                return false;
//            }
//        });


    }
}
