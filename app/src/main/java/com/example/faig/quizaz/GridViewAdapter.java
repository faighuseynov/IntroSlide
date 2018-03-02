package com.example.faig.quizaz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by faig on 2/11/18.
 */

public class GridViewAdapter extends BaseAdapter {
    //Imageloader to load images
    private ImageLoader imageLoader;


    //Context
    private Context context;

    //Array List that would contain the urls and the titles for the images
    private ArrayList<String> images;
    private ArrayList<String> names;

    public GridViewAdapter (Context context, ArrayList<String> images, ArrayList<String> names){
        //Getting all the values
        this.context = context;
        this.images = images;
        this.names = names;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            final LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.category_item,null);
        }

        //Creating a linear layout
        // ConstraintLayout constraintLayout = new ConstraintLayout(context);
        //NetworkImageView
        NetworkImageView networkImageView =(NetworkImageView)convertView.findViewById(R.id.category_icon);
        TextView textView = (TextView)convertView.findViewById(R.id.category_desc);
        //Initializing ImageLoader
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(images.get(position), ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        //Setting the image url to load
        networkImageView.setImageUrl(images.get(position),imageLoader);

        //Creating a textview to show the title
        // TextView textView = new TextView(context);

        textView.setText(names.get(position));

        //Scaling the imageview
        //networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
     //   networkImageView.setLayoutParams(new ConstraintLayout.LayoutParams(250,250));



        //Returning the layout
        return convertView;
    }
}
