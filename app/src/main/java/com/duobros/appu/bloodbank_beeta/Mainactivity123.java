package com.duobros.appu.bloodbank_beeta;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;

import com.duobros.appu.tabs.SlidingTabLayout;

public class Mainactivity123 extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager mpager;
    private SlidingTabLayout mtabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity123);

        toolbar=(Toolbar)findViewById(R.id.appb);
        toolbar.inflateMenu(R.menu.menu1);
        setSupportActionBar(toolbar);

        mpager=(ViewPager)findViewById(R.id.pager);
        mpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mtabs=(SlidingTabLayout)findViewById(R.id.tabs);
        mtabs.setDistributeEvenly(true);
        mtabs.setBackgroundColor(getResources().getColor(R.color.tabs));
        
        mtabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabs1);
            }


        });
        mtabs.setDistributeEvenly(true);
        mtabs.setCustomTabView(R.layout.custom_tab_view, R.id.tabText);
        //mtabs.setViewPager();
        mtabs.setViewPager(mpager);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.about){
            Uri uri = Uri.parse("https://www.linkedin.com/in/hafeez-shaik-540890122/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
    class MyPagerAdapter extends FragmentPagerAdapter{

        String[] tabs;
        int icons[]={R.drawable.ic_favorite_white_18dp,R.drawable.ic_invert_colors_white_18dp,R.drawable.ic_build_white_18dp};
        String[] tabText=getResources().getStringArray(R.array.tabs);
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

            tabs=getResources().getStringArray(R.array.tabs);

        }

        @Override
        public Fragment getItem(int position) {
            Fragment fm;
            if(position==0){
                fm=new Recyclerview_frag();
            }else if(position==1){
                fm=new BloodPost_frag();
            }else{
                fm=new Acc_frag();
            }
            return fm;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            Drawable drawable=getResources().getDrawable(icons[position]);
            drawable.setBounds(0,0,48,48);
            ImageSpan imageSpan=new ImageSpan(drawable);
            SpannableString spannableString=new SpannableString(" ");
            spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return  spannableString;
            //return tabs[position];
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
