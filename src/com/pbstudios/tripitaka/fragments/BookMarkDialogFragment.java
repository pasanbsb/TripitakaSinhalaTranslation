package com.pbstudios.tripitaka.fragments;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.pbstudios.tripitaka.R;
import com.pbstudios.tripitaka.db.DBHelper;
import com.pbstudios.tripitaka.model.Bookmark;

/**
 * Created by Pasan on 2/16/2016.
 */
public class BookMarkDialogFragment extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bookmark_dialog_layout, container, false);
        getDialog().setTitle("Add Bookmark");

        Button btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        DBHelper dbHelper = new DBHelper(getActivity());
        Button btnSave = (Button) rootView.findViewById(R.id.btnSave);
        EditText editText = (EditText) rootView.findViewById(R.id.etBookMarkName);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bookmark bookmark = new Bookmark();
                bookmark.setBookmarkName(editText.getText().toString());
                bookmark.setUrl(HomeFragment.webView.getUrl());

                dbHelper.createBookmark(bookmark);
                Log.d("BookMarkDialogFragment", "bookmark added= " + bookmark.toString());

                Toast.makeText(getActivity().getApplicationContext(), "Bookmark Added.", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return rootView;
    }
}
