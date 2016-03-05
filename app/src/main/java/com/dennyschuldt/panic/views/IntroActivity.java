package com.dennyschuldt.panic.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.dennyschuldt.panic.R;
import com.dennyschuldt.panic.adapters.IntroViewPagerAdapter;
import com.dennyschuldt.panic.fragments.AddEmailsFragment;

/**
 * Created by denny on 2/6/16.
 */
public class IntroActivity  extends AppCompatActivity {

    private Menu menu;
    private ViewHolder viewHolder;
    private IntroViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        viewHolder = new ViewHolder();
        viewHolder.buildLayout();

        viewPagerAdapter = new IntroViewPagerAdapter(getSupportFragmentManager());
        viewHolder.viewPager.setAdapter(viewPagerAdapter);
        viewHolder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                AddEmailsFragment fragment = (AddEmailsFragment) viewPagerAdapter.getRegisteredFragment(1);
                fragment.updateBackground(positionOffset);
            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        menu.getItem(0).setVisible(true);
                        break;
                    case 1:
                        menu.getItem(0).setVisible(false);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu _menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.intro_menu, _menu);
        menu = _menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.next:
                viewHolder.viewPager.setCurrentItem(viewHolder.viewPager.getCurrentItem() + 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (viewHolder.viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewHolder.viewPager.setCurrentItem(viewHolder.viewPager.getCurrentItem() - 1);
        }
    }

    /**
     *
     */
    public class ViewHolder {

        public ViewPager viewPager;

        public void buildLayout() {
            findViews();
        }

        /**
         *
         */
        public void findViews() {
            viewPager = (ViewPager) findViewById(R.id.intro_viewpager);
        }

    }

}
