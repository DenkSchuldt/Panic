package com.dennyschuldt.panic.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dennyschuldt.panic.R;
import com.dennyschuldt.panic.database.DBHelper;
import com.dennyschuldt.panic.database.db.EmailDB;
import com.dennyschuldt.panic.models.Email;
import com.dennyschuldt.panic.utils.Utils;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created by denny on 2/6/16.
 */
public class AddEmailsFragment extends Fragment {

  private EmailDB emailDB;
  private DBHelper helper;
  private ViewHolder viewHolder;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    viewHolder = new ViewHolder(inflater.inflate(R.layout.fragment_add_emails, container, false));
    viewHolder.buildLayout();
    helper = OpenHelperManager.getHelper(getActivity(), DBHelper.class);
    emailDB = new EmailDB(helper);
    return viewHolder.root;
  }

  /**
   *
   */
  public void addEmail() {
    View view = getActivity().getLayoutInflater()
            .inflate(R.layout.item_email_field, (ViewGroup)viewHolder.root, false);
    view.requestFocus();

    viewHolder.list.addView(view);
  }

  /**
   *
   */
  public void saveEmails() {
    int emailsCount = viewHolder.list.getChildCount();
    boolean correct = true;
    for (int i=0; i<emailsCount; i++) {
      EditText editText = (EditText) viewHolder.list.getChildAt(i);
      if (!Utils.isValidEmail(editText.getText().toString())) {
        correct = false;
      }
    }
    if (correct) {
      System.out.println("Correct");
      /*for (int i=0; i<emailsCount; i++) {
        EditText editText = (EditText) viewHolder.list.getChildAt(i);
        emailDB.saveEmail(new Email(editText.getText().toString()));
      }*/
    } else {
      System.out.println("Incorrect");
    }
  }

  /**
   *
   */
  private class ViewHolder implements View.OnClickListener {

    public View root;
    public FloatingActionButton add;
    public LinearLayout list;
    public Button save;

    public ViewHolder(View view) {
      root = view;
    }

    public void buildLayout() {
      findViews();
      setOnClicListeners();
    }

    public void findViews() {
      add = (FloatingActionButton) root.findViewById(R.id.emails_add);
      list = (LinearLayout) root.findViewById(R.id.emails_list);
      save = (Button) root.findViewById(R.id.emails_save);
    }

    public void setOnClicListeners() {
      add.setOnClickListener(this);
      save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()) {
        case R.id.emails_add:
          addEmail();
          break;
        case R.id.emails_save:
          saveEmails();
          break;
      }
    }
  }

}
