package com.example.faig.quizaz;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */


public class SubjectFragment extends Fragment {


    public static final String URL = "https://api.myjson.com/bins/1ger21";


    //Tag values to read from json
    public static final String TAG_IMAGE_URL = "subject_url";
    public static final String TAG_NAME = "subject_name";

    //GridView Object
    private GridView subjectGridView;

    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> names;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_subject,container,false);


        subjectGridView = view.findViewById(R.id.subjectGridView);

        images = new ArrayList<>();
        names = new ArrayList<>();

        getData();


        return view;
    }

        //Calling the getData method
     //   getData();
    private void getData() {
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching data...", false, false);
        //Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();
                        //  System.out.println(response);
                        //Displaying our grid
                         showGrid(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //error

                    }
                }
        );
        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }

    private void showGrid(JSONArray jsonArray){
        try {
            JSONObject jsonObject= jsonArray.getJSONObject(0);
            JSONArray subject = jsonObject.getJSONArray("abituriyent_subject");
            for (int i = 0;i<subject.length();i++){

                JSONObject object = subject.getJSONObject(i);
                images.add(object.getString(TAG_IMAGE_URL));
                names.add(object.getString(TAG_NAME));
            }


        }catch (JSONException e){
            e.printStackTrace();
        }
        final GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity(),images,names);

        //Adding adapter to gridview
        subjectGridView.setAdapter(gridViewAdapter);

    }

}
