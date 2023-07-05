package com.example.qlphontro;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btnNavi;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnNavi = findViewById(R.id.bottomNav);
        viewPager = findViewById(R.id.viewPager);
        //btn
        setupViewPager();

    }
