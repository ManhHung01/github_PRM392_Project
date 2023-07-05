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


        btnNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.support:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.support1:
                        viewPager.setCurrentItem(2);
                        break;

                }
                return true;
            }
        });
    }

}
