package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentActivity;


public class ge100 extends FragmentActivity
{
    private ListView lv2;
    ArrayAdapter<String> adapter2;
    String str_text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ge100);
        String options[] = {"Option 1", "Option 2", "Option 3", "Option 4", "Option 5",
                "Option 6", "Option 7",
                "Option 8", "Option 9", "Option 10", "Option 11"};
        adapter2 = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, options);
        lv2 = (ListView) findViewById(R.id.list_view_two);
        lv2.setAdapter(adapter2);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position,
                                    long id) {
                Object o = adapter.getItemAtPosition(position);
                str_text = o.toString();
                str_text=str_text.substring(7);
                //*finish();
                Intent i = new Intent(getApplicationContext(), option.class);
                i.putExtra("option", str_text);
                startActivity(i);
            }
        });
    }
    public int optionChosen()
    {
        int result=0;
        if(str_text!=null)
            result= Integer.parseInt(str_text);
        return result;
    }

}