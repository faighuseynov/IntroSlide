package com.example.faig.quizaz;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.faig.quizaz.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /*private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";*/

    // TODO: Rename and change types of parameters
   // private String mParam1;
    //private String mParam2;

    public static final String DATA_URL = "https://api.myjson.com/bins/1ger21";
    //  public int position;
    // public Context context;

    //Tag values to read from json
    public static final String TAG_IMAGE_URL = "category_url";
    public static final String TAG_NAME = "category_name";

    //GridView Object
    private GridView gridView;

    private TextView textView;

    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> names;






    private OnFragmentInteractionListener mListener;

    public CategoryFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();

        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_category, container, false);

        gridView = (GridView)view.findViewById(R.id.gridView);
        images = new ArrayList<>();
        names = new ArrayList<>();
        textView = view.findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
            }
        });

       return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
       makeRequest();
        super.onStart();
    }

    @Override
    public void onStop() {

        //  ((FrameLayout)getView().findViewById(R.id.category_fragment)).removeView(getView().findViewById(R.id.category_fragment));
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


public void makeRequest(){

    final ProgressDialog loading = ProgressDialog.show(getContext(), "Gözləyin...", "Yüklənir...", false, false);

    //Creating a json array request to get the json from our api
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DATA_URL,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    //Dismissing the progressdialog on response
                    loading.dismiss();

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
    RequestQueue requestQueue = Volley.newRequestQueue(getContext());
    //Adding our request to the queue
    requestQueue.add(jsonArrayRequest);


}

    private void showGrid(JSONArray jsonArray) {

        //Looping through all the elements of json array
        for (int i = 0; i < jsonArray.length(); i++) {
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);

                //getting image url and title from json object
                images.add(obj.getString(TAG_IMAGE_URL));
                names.add(obj.getString(TAG_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Creating GridViewAdapter Object
        final GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(), images, names);

        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        SubjectFragment subjectFragment = new SubjectFragment();
                        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_container, subjectFragment)
                                .addToBackStack(null)
                                .commit();
                        // ((FrameLayout)getView().findViewById(R.id.category_fragment)).removeView(getView().findViewById(R.id.category_fragment));
                        break;
                }


            }


        });

   /* private boolean loadFragment(android.support.v4.app.Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;

    }*/

        }

}
