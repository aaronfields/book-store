package com.amicly.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
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
    private ArrayList<Book> mBooks = new ArrayList<>();
    private ArrayList<Book> mSearchBooks = new ArrayList<>();
    private ArrayList<Book> mLitBooks = new ArrayList<>();
    private ArrayList<Book> mMysBooks = new ArrayList<>();
    private ArrayList<Book> mFanBooks = new ArrayList<>();
    private ArrayList<Book> mSortAuthors = new ArrayList<>();
    private ArrayList<Book> mSortDate = new ArrayList<>();
    private ArrayList<Book> mGetAll = new ArrayList<>();
    private int ID;
    GridView gridview;
    GridView litview;
    GridView mysteryview;
    GridView fanview;
    GridView sortAuthorView;
    GridView searchview;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleIntent(getIntent());


        // Set the URLs for the images to a String
        forWhomTheBellTolls = "http://d28hgpri8am2if.cloudfront.net/book_images/cvr9780684830483_9780684830483_hr.jpg";
        eastOfEden = "http://d.gr-assets.com/books/1441547516l/4406.jpg";
        greatGatsby = "https://upload.wikimedia.org/wikipedia/en/thumb/f/f7/TheGreatGatsby_1925jacket.jpeg/190px-TheGreatGatsby_1925jacket.jpeg";
        dragonTattoo = "http://d.gr-assets.com/books/1327868566l/2429135.jpg";
        crimeAndPunishment = "http://d.gr-assets.com/books/1382846449l/7144.jpg";
        fromRussia = "http://tomsoter.com/sites/default/files/imagecache/medium_height_image/wysiwyg_imageupload/1/BOND%20BOOK%20COVERS_0016.jpg";
        harryPotter = "http://d.gr-assets.com/books/1361572757l/3.jpg";
        lordOfTheRings = "http://d.gr-assets.com/books/1411114164l/33.jpg";
        book1984 = "http://d.gr-assets.com/books/1348990566l/5470.jpg";

        gridview = (GridView) findViewById(R.id.gridview);

        helper = Helper.getInstance(MainActivity.this);
        helper.cleanDataBase();

        // Use AsyncTask to add data to the database

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


                Publisher publisher1 = new Publisher("Scribner", "For Whom the Bell Tolls", 4.00);
                Publisher publisher2 = new Publisher("Penguin", "East of Eden", 5.00);
                Publisher publisher3 = new Publisher("Scribner", "The Great Gatsby", 6.00);
                Publisher publisher4 = new Publisher("Norstedts Förlag", "The Girl with the Dragon Tattoo", 3.00);
                Publisher publisher5 = new Publisher("The Russian Messenger", "Crime and Punishment", 2.00);
                Publisher publisher6 = new Publisher("Jonathan Cape", "From Russia with Love", 4.00);
                Publisher publisher7 = new Publisher("Scholastic", "Harry Potter and the Sorcerer's Stone", 3.50);
                Publisher publisher8 = new Publisher("Allen & Unwin", "The Lord of the Rings", 8.00);
                Publisher publisher9 = new Publisher("Harvill Secker", "1984", 2.50);

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
                // Populate GridView with data
                super.onPostExecute(s);
                mBooks = helper.getBooks();
                gridview.setAdapter(new ImageAdapter(MainActivity.this, mBooks));

            }
        }.execute();

        // Set on-click to bring object clicked to details activity
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent (MainActivity.this, DetailActivity.class);
                intent.putExtra(KEY_ID, mBooks.get(position).getID());
                startActivity(intent);
            }
        });

        // Create  Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    // Create menu on Action Bar
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Create Search function
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search),
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        mSearchBooks = helper.searchBooks(query);
                        gridview.setAdapter(new ImageAdapter(MainActivity.this, mSearchBooks));
                        return true;
                    }

                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                       // helper.searchBooks(query);
                        searchview = (GridView) findViewById(R.id.gridview);
                        mBooks = helper.getBooks();
                        searchview.setAdapter(new ImageAdapter(MainActivity.this, mBooks));
                        return true;
                    }
                });

        return true;
    }
    //Set up query and OnClick for search
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
            mSearchBooks = helper.searchBooks(query);
            gridview.setAdapter(new ImageAdapter(this, mSearchBooks));
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(KEY_ID, mSearchBooks.get(position).getID());
                    startActivity(intent);
                }
            });
        }
    }

    // Switch case for what to do when each menu item is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cat_literature:
                litview = (GridView) findViewById(R.id.gridview);
                mLitBooks = helper.getLitBooks();
                //Toast.makeText(MainActivity.this, "It worked!", Toast.LENGTH_SHORT).show();
                litview.setAdapter(new ImageAdapter(this, mLitBooks));
                litview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        Intent intent = new Intent (MainActivity.this, DetailActivity.class);
                        intent.putExtra(KEY_ID, mLitBooks.get(position).getID());
                        startActivity(intent);
                    }
                });
                break;
            case R.id.cat_mystery:
                mysteryview = (GridView) findViewById(R.id.gridview);
                mMysBooks = helper.getMysBooks();
                mysteryview.setAdapter(new ImageAdapter(this, mMysBooks));
                mysteryview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        Intent intent = new Intent (MainActivity.this, DetailActivity.class);
                        intent.putExtra(KEY_ID, mMysBooks.get(position).getID());
                        startActivity(intent);
                    }
                });
                break;
            case R.id.cat_fantasy:
                fanview = (GridView) findViewById(R.id.gridview);
                mFanBooks = helper.getFanBooks();
                fanview.setAdapter(new ImageAdapter(this, mFanBooks));
                fanview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        Intent intent = new Intent (MainActivity.this, DetailActivity.class);
                        intent.putExtra(KEY_ID, mFanBooks.get(position).getID());
                        startActivity(intent);
                    }
                });
                break;
            case R.id.sort_author:
                sortAuthorView = (GridView) findViewById(R.id.gridview);
                mSortAuthors = helper.getsortAuthors();
                sortAuthorView.setAdapter(new ImageAdapter(this, mSortAuthors));
                sortAuthorView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        Intent intent = new Intent (MainActivity.this, DetailActivity.class);
                        intent.putExtra(KEY_ID, mSortAuthors.get(position).getID());
                        startActivity(intent);
                    }
                });
                break;
            case R.id.return_all:
                mBooks = helper.getBooks();
                gridview.setAdapter(new ImageAdapter(MainActivity.this, mBooks));
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        Intent intent = new Intent (MainActivity.this, DetailActivity.class);
                        intent.putExtra(KEY_ID, mBooks.get(position).getID());
                        startActivity(intent);
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
