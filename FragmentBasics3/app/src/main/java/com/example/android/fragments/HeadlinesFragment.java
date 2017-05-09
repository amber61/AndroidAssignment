/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.fragments;

import android.app.Activity;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class HeadlinesFragment extends ListFragment {
    OnHeadlineSelectedListener mCallback;

    CountriesDbAdapter dbHelper;
    private SimpleCursorAdapter dataAdapter;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /** Called by HeadlinesFragment when a list item is selected */
        public void onArticleSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;


        // Create an array adapter for the list view, using the Ipsum headlines array
       // setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Ipsum.Headlines));


        dbHelper = new CountriesDbAdapter(getActivity());
        dbHelper.open();
        //Clean all data
        dbHelper.deleteAllCountries();
        //Add some data
        dbHelper.insertSomeCountries();

        //Generate ListView from SQLite Database
        displayListView();

    }

    private void displayListView() {


        Cursor cursor = dbHelper.fetchAllCountries();

        // The desired columns to be bound
        String[] columns = new String[]{
                CountriesDbAdapter.KEY_CODE,
                CountriesDbAdapter.KEY_NAME,
                CountriesDbAdapter.KEY_CONTINENT,
                CountriesDbAdapter.KEY_REGION
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.code,
                R.id.name,
                R.id.continent,
                R.id.region,
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                getActivity(), R.layout.country_info,
                cursor,
                columns,
                to,
                0);

        //ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        setListAdapter(dataAdapter);
    }


    @Override
    public void onListItemClick(ListView listView, View view,
                                int position, long id) {
        // Get the cursor, positioned to the corresponding row in the result set
        Cursor cursor = (Cursor) listView.getItemAtPosition(position);

        // Get the state's capital from this row in the database.
        String countryCode =
                cursor.getString(cursor.getColumnIndexOrThrow(CountriesDbAdapter.KEY_CODE));
        Toast.makeText(getActivity(),
                countryCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        // Notify the parent activity of selected item
//        mCallback.onArticleSelected(position);
//
//        // Set the item as checked to be highlighted when in two-pane layout
//        getListView().setItemChecked(position, true);
//    }
}