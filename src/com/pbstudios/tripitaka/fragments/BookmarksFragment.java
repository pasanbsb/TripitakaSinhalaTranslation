package com.pbstudios.tripitaka.fragments;

/**
 * Created by Pasan on 2/14/2016.
 */

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.pbstudios.tripitaka.R;
import com.pbstudios.tripitaka.db.DBHelper;
import com.pbstudios.tripitaka.model.Bookmark;

import java.util.ArrayList;
import java.util.List;

public class BookmarksFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnLongClickListener {

    public BookmarksFragment() {
    }
    ArrayAdapter arrayAdapter;
    View rootView;
    ListView listView;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_bookmarks, container, false);
        listView = (ListView)rootView.findViewById(R.id.bookmarksList);
        listView.setOnItemClickListener(this);
        listView.setOnLongClickListener(this);
        dbHelper = new DBHelper(getActivity());


        List<Bookmark> bookmarks = dbHelper.getAllBookmarks();
        List bookmarkNames = new ArrayList<>();

        for(Bookmark b : bookmarks) {
            bookmarkNames.add(b.getBookmarkName());
        }

        arrayAdapter = new ArrayAdapter(getActivity(), R.layout.bookmark_row_layout, bookmarkNames);

        if(arrayAdapter != null){
            listView.setAdapter(arrayAdapter);
        }
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bookmark bookmark = dbHelper.readBookmark(i);
        if (bookmark != null) {
            Fragment newFragment = new HomeFragment(bookmark.getUrl());
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Error occurred while loading bookmark!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        Toast.makeText(getActivity().getApplicationContext(), "long clicked", Toast.LENGTH_SHORT).show();
        return true;
    }
}