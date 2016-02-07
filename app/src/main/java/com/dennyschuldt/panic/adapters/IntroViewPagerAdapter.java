package com.dennyschuldt.panic.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.dennyschuldt.panic.fragments.AddEmailsFragment;
import com.dennyschuldt.panic.fragments.SaveEmailsFragment;
import com.dennyschuldt.panic.fragments.WelcomeFragment;

/**
 * Created by denny on 2/6/16.
 */
public class IntroViewPagerAdapter extends FragmentStatePagerAdapter {

  private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

  public IntroViewPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public int getCount() {
    return 3;
  }

  @Override
  public Fragment getItem(int position) {
    Fragment fragment = new Fragment();
    switch (position) {
      case 0:
        fragment = new WelcomeFragment();
        break;
      case 1:
        fragment = new AddEmailsFragment();
        break;
      case 2:
        fragment = new SaveEmailsFragment();
        break;
    }
    return fragment;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    Fragment fragment = (Fragment) super.instantiateItem(container, position);
    registeredFragments.put(position, fragment);
    return fragment;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    registeredFragments.remove(position);
    super.destroyItem(container, position, object);
  }

  /**
   *
   * @param position
   * @return
   */
  public Fragment getRegisteredFragment(int position) {
    return registeredFragments.get(position);
  }


}
