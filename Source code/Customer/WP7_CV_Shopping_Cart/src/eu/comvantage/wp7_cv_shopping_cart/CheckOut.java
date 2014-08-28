package eu.comvantage.wp7_cv_shopping_cart;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class CheckOut extends Activity {
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	private int active01, active02;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_out);
		
		StrictMode.setThreadPolicy(policy);
		
		Spinner s_address = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter01 = ArrayAdapter.createFromResource(
                this, R.array.Address, android.R.layout.simple_spinner_item);
        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_address.setAdapter(adapter01);
        
        active01 = ActiveShirt(1);
		active02 = ActiveShirt(2);

		
		
        Button bConfirm = (Button) findViewById(R.id.bConfirm);
        bConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        		Calendar cal = Calendar.getInstance();
        		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            	
           		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
           		
           		String u1 = "";
           		
           		if (active01 == 1 && active02 == 1) { //both shirts
           			u1 = "update cv_p_orders set Active = 1, Status = 0";
           		}
           		else if (active01 == 1 && active02 == 0) { //shirt1
           			u1 = "update cv_p_orders set Active = 1, Status = 0 where id = 1";
           		}
           		else if (active01 == 0 && active02 == 1) {
           			u1 = "update cv_p_orders set Active = 1, Status = 0 where id = 2";
           		}
           		else {
           			u1 = "update cv_p_orders set Active = 0, Status = 0";
           		}
           		
           		nameValuePairs.add(new BasicNameValuePair("u1", u1));
                    try
                    {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("http://www.hornet.si/update.php");
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        HttpResponse response = httpclient.execute(httppost); 
                        Log.e("log_tag", "connection success ");
                        
                        Intent myIntent = new Intent(view.getContext(), Confirmation.class);
                        startActivity(myIntent);
                    
                    }
                catch(Exception e)
                    {
                        Log.e("log_tag", "Error in http connection "+e.toString());
                        Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

                    }
            	
            	
                    nameValuePairs.add(new BasicNameValuePair("u1","update cv_shoppingcart set Active = 0"));
                    try
                    {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("http://www.hornet.si/update.php");
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        HttpResponse response = httpclient.execute(httppost); 
                        Log.e("log_tag", "connection success ");
                        
                        Intent myIntent = new Intent(view.getContext(), Confirmation.class);
                        startActivity(myIntent);
                    
                    }
                catch(Exception e)
                    {
                        Log.e("log_tag", "Error in http connection "+e.toString());
                        Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

                    }
            	
            	
            	
            	
            }
        
        });
		
		
		
        
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public int ActiveShirt (int i) {
		String s1 = "";
		int a = 0;
		
		switch(i) {
			case 1: s1 = "Select Active from cv_shoppingcart where ID = 1";
			break;
			case 2: s1 = "Select Active from cv_shoppingcart where ID = 2";
			break;
		}
		
		
		String result = null;
        InputStream is = null;
        
   		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("s1", s1));
            try
            {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://www.hornet.si/select.php");
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost); 
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
            }
        catch(Exception e)
            {
                Log.e("log_tag", "Error in http connection "+e.toString());
                Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
            }
                            
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) 
                {
                        sb.append(line + "\n");
                        
                }
                is.close();

                result = sb.toString();
                Hashtable h1 = result2HashTable(result);
                a = Integer.parseInt(h1.get("Active").toString());
               
            }
            catch(Exception e)
            {
               Log.e("log_tag", "Error converting result "+e.toString());

            }
            
            return(a);
	}
	
	private Hashtable result2HashTable(String result)
	{
		Hashtable ht = new Hashtable();
		
		
		String tmp = result.substring(2, result.length());
		tmp = tmp.substring(0, tmp.length() - 4);
		
		String [] separated = tmp.split(",");
		
		for (int i = 0; i < separated.length; i++)
		{
			separated[i] = separated[i].replace("\"", "");
			String [] separated2 = separated[i].split(":");
			
			ht.put(separated2[0], separated2[1]);			
		}
						
		return ht;
	}
	
}
