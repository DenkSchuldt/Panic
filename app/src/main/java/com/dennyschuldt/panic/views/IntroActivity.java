package com.dennyschuldt.panic.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;


import com.dennyschuldt.panic.Constants;
import com.dennyschuldt.panic.R;
import com.dennyschuldt.panic.adapters.IntroViewPagerAdapter;
import com.dennyschuldt.panic.fragments.AddEmailsFragment;

/**
 * Created by denny on 2/6/16.
 */
public class IntroActivity  extends AppCompatActivity {

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
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    viewHolder.fab.show();
                } else {
                    viewHolder.fab.hide();
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewHolder.viewPager.getCurrentItem() != 1) {
            viewHolder.fab.hide();
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
    public void addEmail() {
        if (viewHolder.viewPager.getCurrentItem() == Constants.ADD_EMAILS_FRAGMENT) {
            AddEmailsFragment fragment = (AddEmailsFragment)
                    viewPagerAdapter.getRegisteredFragment(Constants.ADD_EMAILS_FRAGMENT);
            fragment.addEmail();
        }
    }

    /**
     *
     */
    public class ViewHolder implements View.OnClickListener {

        public TextView skip;
        public TextView next;
        public ViewPager viewPager;
        public FloatingActionButton fab;

        public void buildLayout() {
            findViews();
            setOnClickListeners();
        }

        /**
         *
         */
        public void findViews() {
            viewPager = (ViewPager) findViewById(R.id.intro_viewpager);
            skip = (TextView) findViewById(R.id.intro_skip);
            next = (TextView) findViewById(R.id.intro_next);
            fab = (FloatingActionButton) findViewById(R.id.intro_fab);
        }

        /**
         *
         */
        public void setOnClickListeners() {
            skip.setOnClickListener(this);
            next.setOnClickListener(this);
            fab.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.intro_skip:
                    break;
                case R.id.intro_next:
                    break;
                case R.id.intro_fab:
                    addEmail();
                    break;
            }
        }

    }

}
