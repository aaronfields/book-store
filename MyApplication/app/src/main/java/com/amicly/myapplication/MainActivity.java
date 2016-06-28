package com.amicly.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String forWhomTheBellTolls;
    private String eastOfEden;
    private String greatGatsby;
    private String dragonTattoo;
    private String crimeAndPunishment;
    private String fromRussia;
    private String harryPotter;
    private String lordOfTheRings;
    private String book1984;
    public static final String KEY_ID = "Item ID";
    private Helper helper;
    private ArrayList<Book> mBook = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forWhomTheBellTolls = "http://d28hgpri8am2if.cloudfront.net/book_images/cvr9780684830483_9780684830483_hr.jpg";
        eastOfEden = "http://d.gr-assets.com/books/1441547516l/4406.jpg";
        greatGatsby = "https://upload.wikimedia.org/wikipedia/en/thumb/f/f7/TheGreatGatsby_1925jacket.jpeg/190px-TheGreatGatsby_1925jacket.jpeg";
        dragonTattoo = "http://d.gr-assets.com/books/1327868566l/2429135.jpg";
        crimeAndPunishment = "http://d.gr-assets.com/books/1382846449l/7144.jpg";
        fromRussia = "http://tomsoter.com/sites/default/files/imagecache/medium_height_image/wysiwyg_imageupload/1/BOND%20BOOK%20COVERS_0016.jpg";
        harryPotter = "http://d.gr-assets.com/books/1361572757l/3.jpg";
        lordOfTheRings = "http://d.gr-assets.com/books/1411114164l/33.jpg";
        book1984 = "http://d.gr-assets.com/books/1348990566l/5470.jpg";

        helper = Helper.getInstance(MainActivity.this);

        new AsyncTask<Book, Void, String>() {
            @Override
            protected String doInBackground(Book... bookses) {
                Book book1 = new Book("Literature & Fiction", "Ernest Hemingway", "For Whom the Bell Tolls", 11.00, "1940", forWhomTheBellTolls);
                Book book2 = new Book("Literature & Fiction", "John Steinbeck", "East of Eden", 12.00, "1952", eastOfEden);
                Book book3 = new Book("Literature & Fiction", "F. Scott Fitzgerald", "The Great Gatsby", 12.50, "1925", greatGatsby);
                Book book4 = new Book("Mystery & Suspense", "Stieg Larsson", "The Girl with the Dragon Tattoo", 6.00, "2005", dragonTattoo);
                Book book5 = new Book("Mystery & Suspense", "Fyodor Dostoyevsky", "Crime and Punishment", 5.00, "1866", crimeAndPunishment);
                Book book6 = new Book("Mystery & Suspense", "Ian Fleming", "From Russia with Love", 8.00, "1957", fromRussia);
                Book book7 = new Book("Science Fiction & Fantasy", "J.K. Rowling", "Harry Potter and the Sorcerer's Stone", 7.00, "1997", harryPotter);
                Book book8 = new Book("Science Fiction & Fantasy", "J.R.R. Tolkien", "The Lord of the Rings", 15.00, "1954", lordOfTheRings);
                Book book9 = new Book("Science Fiction & Fantasy", "George Orwell", "1984", 6.00, "1949", book1984);


                Publishers publisher1 = new Publishers("Scriber", "For Whom the Bell Tools", 4.00);
                Publishers publisher2 = new Publishers("Penguin", "East of Eden", 5.00);
                Publishers publisher3 = new Publishers("Scribner", "The Great Gatsby", 6.00);
                Publishers publisher4 = new Publishers("Norstedts Förlag", "The Girl with the Dragon Tattoo", 3.00);
                Publishers publisher5 = new Publishers("The Russian Messenger", "Crime and Punishment", 2.00);
                Publishers publisher6 = new Publishers("Jonathan Cape", "From Russia with Love", 4.00);
                Publishers publisher7 = new Publishers("Scholastic", "Harry Potter and the Sorcerer's Stone", 3.50);
                Publishers publisher8 = new Publishers("Allen & Unwin", "The Lord of the Rings", 8.00);
                Publishers publisher9 = new Publishers("Harvill Secker", "1984", 2.50);

                helper.insertRow(book1);
                helper.insertRow(book2);
                helper.insertRow(book3);
                helper.insertRow(book4);
                helper.insertRow(book5);
                helper.insertRow(book6);
                helper.insertRow(book7);
                helper.insertRow(book8);
                helper.insertRow(book9);

                helper.insertRowPublishers(publisher1);
                helper.insertRowPublishers(publisher2);
                helper.insertRowPublishers(publisher3);
                helper.insertRowPublishers(publisher4);
                helper.insertRowPublishers(publisher5);
                helper.insertRowPublishers(publisher6);
                helper.insertRowPublishers(publisher7);
                helper.insertRowPublishers(publisher8);
                helper.insertRowPublishers(publisher9);

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                super.onPostExecute(s);
                //set to textViews etc...
            }
        }.execute();

        mBook.add(helper.getEverything());
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, mBook));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent (MainActivity.this, DetailActivity.class);
                intent.putExtra(KEY_ID, id);
                startActivity(intent);

//                Toast.makeText(MainActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Aaron's Bookstore");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cat_literature:
                helper.getLiterature();
                helper.close();
                break;
            case R.id.cat_mystery:
                helper.getMystery();
                helper.close();
                break;
            case R.id.cat_fantasy:
                helper.getFantasy();
                helper.close();
                break;
            case R.id.sort_author:
                helper.sortByAuthor();
                helper.close();
                break;
            case R.id.sort_date:
                helper.sortByDate();
                helper.close();
        }

        return super.onOptionsItemSelected(item);
    }
}
