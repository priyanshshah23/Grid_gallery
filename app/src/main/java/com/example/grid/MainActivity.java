package com.example.grid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.grid.model.Picsum;
import com.example.grid.network.APIclient;
import com.example.grid.network.APIinterface;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
   GridView gridView;
   CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView =findViewById(R.id.gridView);

        Call<List<Picsum>> call=APIclient.apIinterface().getPicsum();
        call.enqueue(new Callback<List<Picsum>>() {
            @Override
            public void onResponse(Call<List<Picsum>> call, Response<List<Picsum>> response) {
                if (response.isSuccessful()) {

                    customAdapter = new CustomAdapter(response.body(),MainActivity.this);
                    gridView.setAdapter(customAdapter);
                }else{
                    //if any error occurs toast msg is generated
                    Toast.makeText(getApplicationContext(),"An error occured",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Picsum>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"An error occured"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public class CustomAdapter extends BaseAdapter{

        public List<Picsum> picsumList;
        public Context context;

        public CustomAdapter(List<Picsum> picsumList, Context context) {
            this.picsumList = picsumList;
            this.context = context;
        }

        @Override
        public int getCount() {
            //size of list of images
            return picsumList.size();
        }

        @Override
        public Object getItem(int position) {

            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(context).inflate(R.layout.row_data, null);
            //find view
            ImageView download_url = view.findViewById(R.id.imageView);

            //set image

           if (position < 10) {


                Glide.with(context).load(picsumList.get(position).getDownload_url()).into(download_url);
                //set page number
                Toast.makeText(getApplicationContext(),"Page1",Toast.LENGTH_SHORT).show();
            }
            else if (position > 10) {

                 Glide.with(context).load(picsumList.get(position).getDownload_url()).into(download_url);
                Toast.makeText(getApplicationContext(),"Page2",Toast.LENGTH_SHORT).show();
            }
           else{

                Glide.with(context).load(picsumList.get(position).getDownload_url()).into(download_url);
                Toast.makeText(getApplicationContext(),"Page3",Toast.LENGTH_SHORT).show();
            }
            return view;
        }


    }
}
