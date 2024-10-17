package com.practice.myapplication;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    private CustomAdapter customAdapter;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;
    int lastExpandedPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareListData();

        expandableListView = findViewById(R.id.expandableListViewId);
        customAdapter = new CustomAdapter(MainActivity.this,listDataHeader,listDataChild);
        expandableListView.setAdapter(customAdapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {

                if (lastExpandedPosition !=-1 && lastExpandedPosition != i){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;

            }
        });
    }




    public void prepareListData(){

        final String[] headerString = getResources().getStringArray(R.array.abbreviation_HeaderStringArray_List);
        final String[] childString = getResources().getStringArray(R.array.abbreviation_ChildStringArray_List);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        for (int i=0;i<headerString.length;i++){

            //adding header data
            listDataHeader.add(headerString[i]);

            List<String> child = new ArrayList<>();
            child.add(childString[i]);


            listDataChild.put(listDataHeader.get(i),child);


        }

    }
}