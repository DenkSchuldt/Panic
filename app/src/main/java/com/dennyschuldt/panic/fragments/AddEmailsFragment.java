package com.dennyschuldt.panic.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennyschuldt.panic.R;

/**
 * Created by denny on 2/6/16.
 */
public class AddEmailsFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(
        R.layout.fragment_add_emails, container, false);
    return rootView;
  }

  /**
   *
   */
  public void addEmail() {
    System.out.println("ADD");
  }

}
