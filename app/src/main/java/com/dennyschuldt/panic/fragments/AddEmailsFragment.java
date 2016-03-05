package com.dennyschuldt.panic.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dennyschuldt.panic.R;
import com.dennyschuldt.panic.database.DBHelper;
import com.dennyschuldt.panic.database.db.EmailDB;
import com.dennyschuldt.panic.models.Email;
import com.dennyschuldt.panic.utils.Utils;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLOutput;

/**
 * Created by denny on 2/6/16.
 */
public class AddEmailsFragment extends Fragment {

  private int alpha;
  private EmailDB emailDB;
  private DBHelper helper;
  private ViewHolder viewHolder;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    alpha = 0;
    viewHolder = new ViewHolder(inflater.inflate(R.layout.fragment_add_emails, container, false));
    viewHolder.buildLayout();
    helper = OpenHelperManager.getHelper(getActivity(), DBHelper.class);
    emailDB = new EmailDB(helper);
    return viewHolder.root;
  }

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      if (viewHolder.list.getChildCount() == 0) {
        addEmail();
      }
    }
  }

  /**
   *
   * @param offset
   */
  public void updateBackground(float offset) {
    viewHolder.header.setBackgroundColor(Color.argb(mapOffset(offset), 96, 125, 139));
  }

  /**
   *
   * @param offset
   * @return
   */
  public int mapOffset(float offset) {
    int newAlpha = (int)Math.floor(offset*256);
    if (alpha > 250 && newAlpha == 0) {
      newAlpha = 255;
    }
    alpha = newAlpha;
    return alpha;
  }

  /**
   *
   */
  public void addEmail() {
    View view = getActivity().getLayoutInflater()
            .inflate(R.layout.item_email_field, (ViewGroup)viewHolder.root, false);
    view.requestFocus();
    final ImageView delete = (ImageView) view.findViewById(R.id.email_delete);
    delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        viewHolder.list.removeView((View)delete.getParent());
      }
    });
    viewHolder.list.addView(view, 0);
  }

  /**
   *
   */
  public void saveEmails() {
    int emailsCount = viewHolder.list.getChildCount();
    boolean correct = true;
    for (int i=0; i<emailsCount; i++) {
      RelativeLayout layout = (RelativeLayout) viewHolder.list.getChildAt(i);
      EditText editText = (EditText) layout.findViewById(R.id.email_text);
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
    public TextView header;
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
      header = (TextView) root.findViewById(R.id.emails_header);
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
