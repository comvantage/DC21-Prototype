package comvantage.eu.cw7_iaf_dashboard;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private int login = 0, active01, active02, status01, status02, newOrder, assembling, embroidery, delivering;
	private String extra1;
	private TextView newOrders, tvAss, tvEmb, tvDel, tvName;
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.setThreadPolicy(policy);
		
		Bundle extraBundle = getIntent().getExtras();
        
        if (extraBundle != null) {
        	extra1 = extraBundle.getString("login");
        }
        else extra1 = "0";
        
        login = Integer.parseInt(extra1.toString());
        
        newOrders = (TextView)findViewById(R.id.tvNewOrder);
        tvAss = (TextView)findViewById(R.id.tvAss);
        tvEmb = (TextView)findViewById(R.id.tvEmb);
        tvDel = (TextView)findViewById(R.id.tvDel);
        tvName = (TextView)findViewById(R.id.tvName);
        
        if (login == 1) tvName.setText("Name: ProShirt Inc.");
        else if (login == 2) tvName.setText("Name: StickIt GmbH");
        else tvName.setText("Name: Dresscode21");
        
        Shirt1(); Shirt2(); Refresh();
        
        /*
        newOrder = 0; assembling = 0; embroidery = 0; delivering = 0;
        
        if (login == 0 ) {
        	if (active01 == 1 && status01 == 0) newOrder++;
        	if (active02 == 1 && status02 == 0) newOrder++;
        	
        	if (active01 == 1 && (status01 == 1 || status01 == 4)) assembling++;
        	if (active02 == 1 && (status02 == 1 || status02 == 4)) assembling++;
        	
        	if (active01 == 1 && (status01 == 2 || status01 == 3)) embroidery++;
        	if (active02 == 1 && (status02 == 2 || status02 == 3)) embroidery++;
        	
        	if (active01 == 1 && status01 == 5) delivering++;
        	if (active02 == 1 && status02 == 5) delivering++;
        	
        	
        	newOrders.setText("New orders: " + Integer.toString(newOrder));
        	tvAss.setText("Assembling: " + Integer.toString(assembling));
        	tvEmb.setText("Supplementing: " + Integer.toString(embroidery));
        	tvDel.setText("Delivering: " + Integer.toString(delivering));
    	}
    	else if (login == 1) {
    		if (active01 == 1 && status01 == 0) newOrder++;
        	if (active02 == 1 && status02 == 0) newOrder++;
        	
        	if (active01 == 1 && (status01 == 1 || status01 == 4)) assembling++;
        	if (active02 == 1 && (status02 == 1 || status02 == 4)) assembling++;
        	
        	if (active01 == 1 && (status01 == 2 || status01 == 3)) embroidery++;
        	if (active02 == 1 && (status02 == 2 || status02 == 3)) embroidery++;
        	
        	if (active01 == 1 && status01 == 5) delivering++;
        	if (active02 == 1 && status02 == 5) delivering++;
        	
        	
        	newOrders.setText("New orders: " + Integer.toString(newOrder));
        	tvAss.setText("Assembling: " + Integer.toString(assembling));
        	tvDel.setText("Delivering: " + Integer.toString(delivering));
        	
    	}
    	else if (login == 2) {
    		
    		if (active01 == 1 && (status01 == 2 || status01 == 3)) { embroidery++; newOrder++; }
        	if (active02 == 1 && (status02 == 2 || status02 == 3)) { embroidery++; newOrder++; }
        	
        	newOrders.setText("New orders: " + Integer.toString(newOrder));
        	tvEmb.setText("Supplementing: " + Integer.toString(embroidery));
        	tvAss.setText("Assembling: " + Integer.toString(assembling));
        	tvDel.setText("Delivering: " + Integer.toString(delivering));
 
    	}
        */
        ImageButton ibRefreash = (ImageButton)findViewById(R.id.ibRefreash);
        ibRefreash.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Shirt1(); Shirt2(); Refresh();
			}
		});
        
        ImageButton ibOrderMng = (ImageButton)findViewById(R.id.ibKpi01);
		ibOrderMng.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512048-Order_list");
				gotoApp.putExtra("login", extra1);
				startService(gotoApp);
				finish();
			}
		});
		
		ImageButton ibKpiMng = (ImageButton)findViewById(R.id.ibKpi02);
		ibKpiMng.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 //Intent myIntent = new Intent(v.getContext(), kpi_managment.class);
				 //startActivity(myIntent);
				Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512060-KPI");
				startService(gotoApp);
			}
		});
		
		ImageButton ibStockMng = (ImageButton)findViewById(R.id.ibKpi03);
		ibStockMng.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512045-Stock_list");
				gotoApp.putExtra("login", extra1);
				startService(gotoApp);
			}
		});
		
		ImageButton ibProfileMng = (ImageButton)findViewById(R.id.ibKpi04);
		ibProfileMng.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), profile.class);
				 startActivity(myIntent);
			}
		});
		
		
		
		ImageButton ibNews = (ImageButton)findViewById(R.id.ibKpi07);
		ibNews.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), News.class);
				 startActivity(myIntent);
			}
		});
	} 

	public boolean goToNextApp() {
		Intent gotoNextApp = new Intent("eu.comvantage.iaf.NEXT");
		gotoNextApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
		gotoNextApp.putExtra("comingFrom", getString(R.string.app_name));
		gotoNextApp.putExtra("login", extra1);
		startService(gotoNextApp);
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void Shirt1 () {		
		String result = null;
        InputStream is = null;
        
   		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("s1","select * from cv_p_orders where id = 1"));
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

                result=sb.toString();
                Hashtable h1 = result2HashTable(result);
                
                active01 = Integer.parseInt(h1.get("Active").toString());
                status01 = Integer.parseInt(h1.get("Status").toString());
            }
            catch(Exception e)
            {
               Log.e("log_tag", "Error converting result "+e.toString());

            }
            
            
	}
	
	private void Shirt2 () {
		String result = null;
        InputStream is = null;
        
   		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("s1","select * from cv_p_orders where id = 2"));
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

                result=sb.toString();
                Hashtable h1 = result2HashTable(result);
                
                active02 = Integer.parseInt(h1.get("Active").toString());
                status02 = Integer.parseInt(h1.get("Status").toString());
            }
            catch(Exception e)
            {
               Log.e("log_tag", "Error converting result "+e.toString());

            }
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
	
	private void Refresh () {
		newOrder = 0; assembling = 0; embroidery = 0; delivering = 0;
        
        if (login == 0 ) {
        	if (active01 == 1 && status01 == 0) newOrder++;
        	if (active02 == 1 && status02 == 0) newOrder++;
        	
        	if (active01 == 1 && (status01 == 1 || status01 == 4)) assembling++;
        	if (active02 == 1 && (status02 == 1 || status02 == 4)) assembling++;
        	
        	if (active01 == 1 && (status01 == 2 || status01 == 3)) embroidery++;
        	if (active02 == 1 && (status02 == 2 || status02 == 3)) embroidery++;
        	
        	if (active01 == 1 && status01 == 5) delivering++;
        	if (active02 == 1 && status02 == 5) delivering++;
        	
        	
        	newOrders.setText("New orders: " + Integer.toString(newOrder));
        	tvAss.setText("Assembling: " + Integer.toString(assembling));
        	tvEmb.setText("Supplementing: " + Integer.toString(embroidery));
        	tvDel.setText("Delivering: " + Integer.toString(delivering));
    	}
    	else if (login == 1) {
    		if (active01 == 1 && status01 == 0) newOrder++;
        	if (active02 == 1 && status02 == 0) newOrder++;
        	
        	if (active01 == 1 && (status01 == 1 || status01 == 4)) assembling++;
        	if (active02 == 1 && (status02 == 1 || status02 == 4)) assembling++;
        	
        	if (active01 == 1 && (status01 == 2 || status01 == 3)) embroidery++;
        	if (active02 == 1 && (status02 == 2 || status02 == 3)) embroidery++;
        	
        	if (active01 == 1 && status01 == 5) delivering++;
        	if (active02 == 1 && status02 == 5) delivering++;
        	
        	
        	newOrders.setText("New orders: " + Integer.toString(newOrder));
        	tvAss.setText("Assembling: " + Integer.toString(assembling));
        	tvDel.setText("Delivering: " + Integer.toString(delivering));
        	
    	}
    	else if (login == 2) {
    		
    		if (active01 == 1 && (status01 == 2 || status01 == 3)) { embroidery++; newOrder++; }
        	if (active02 == 1 && (status02 == 2 || status02 == 3)) { embroidery++; newOrder++; }
        	
        	newOrders.setText("New orders: " + Integer.toString(newOrder));
        	tvEmb.setText("Supplementing: " + Integer.toString(embroidery));
        	tvAss.setText("Assembling: " + Integer.toString(assembling));
        	tvDel.setText("Delivering: " + Integer.toString(delivering));
 
    	}
	}

}
