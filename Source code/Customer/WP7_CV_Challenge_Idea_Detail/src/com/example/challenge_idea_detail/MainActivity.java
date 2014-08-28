package com.example.challenge_idea_detail;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

	

public class MainActivity extends Activity {

	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.setThreadPolicy(policy); 

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//actionBar.setDisplayShowTitleEnabled(false);
		
		// dodajanje 3 tabov v actionbar
		Tab tab = actionBar.newTab();
		tab.setText(R.string.tab1);
		tab.setTabListener(new TabListener<DetailsFragment>(this, "Naziv", DetailsFragment.class)); // listenerji so dummy
		actionBar.addTab(tab);
		
		tab = actionBar.newTab();
		tab.setText(R.string.tab2);
		tab.setTabListener(new TabListener<DetailsFragment>(this, "Naziv", DetailsFragment.class));	// listenerji so dummy	
		actionBar.addTab(tab);

		tab = actionBar.newTab();
		tab.setText(R.string.tab3);
		tab.setTabListener(new TabListener<DetailsFragment>(this, "Naziv", DetailsFragment.class)); // listenerji so dummy
		actionBar.addTab(tab);		
		
		Button bClose = (Button) findViewById(R.id.btnClose);
		bClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512244-Chalange_detail");
				//gotoApp.putExtra("login", extra1);
				startService(gotoApp);
				finish();
                	
            }
        
        });
		
		
		ImageButton btnVoteup = (ImageButton) findViewById(R.id.btnVoteUp);
		btnVoteup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

				TextView txtVotes = (TextView) findViewById(R.id.txtThumbsCount);
				int i = Integer.parseInt(txtVotes.getText().toString());
				i++;
				txtVotes.setText(Integer.toString(i));
				
	            nameValuePairs.add(new BasicNameValuePair("u1","UPDATE cv_challangesparticipate SET Votes = " + Integer.toString(i) + ";"));
	                try
	                {
	                    HttpClient httpclient = new DefaultHttpClient();
	                    HttpPost httppost = new HttpPost("http://www.hornet.si/update.php");
	                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	                    HttpResponse response = httpclient.execute(httppost); 
	                    HttpEntity entity = response.getEntity();

	                    Log.e("log_tag", "connection success ");
	                
	                }
	            catch(Exception e)
	                {
	                    Log.e("log_tag", "Error in http connection "+e.toString());
	                    Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

	                }
				
			}		
			
		});
		
		
		refreshData();
		
	}
	
	private void refreshData()
	{
		Hashtable ht = fetchData("select * from cv_challangesparticipate order by id desc LIMIT 1");			
		
		if (!ht.isEmpty())			
		{
			
			// datumi
			String PHPFormat = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(PHPFormat, Locale.getDefault());
			Date dateObj = null;
			try {
				dateObj = sdf.parse(ht.get("Datum").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
			
			String MyFormat = "dd.MM.yyyy";
			SimpleDateFormat sdf2 = new SimpleDateFormat(MyFormat, Locale.getDefault());						
			
			TextView txt = (TextView) findViewById(R.id.tekst);
			TextView txtheader = (TextView) findViewById(R.id.txtTitleDesc);
			TextView txtdesc = (TextView) findViewById(R.id.txtDesc);
			TextView txtfrom = (TextView) findViewById(R.id.txtFromDesc);
			TextView txtVotes = (TextView) findViewById(R.id.txtThumbsCount);
			TextView txtAbout = (TextView) findViewById(R.id.lblAbout);
			
			txt.setText(ht.get("Naziv").toString());
			txtheader.setText(ht.get("Naziv").toString());
			txtdesc.setText(ht.get("Description").toString());
			txtfrom.setText("from " + ht.get("Odakle").toString() + ", " + sdf2.format(dateObj));
			txtVotes.setText(ht.get("Votes").toString());
			txtAbout.setText ("About " + ht.get("Odakle").toString());
		}						
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{	
		getMenuInflater().inflate(R.menu.action_menu, menu);
		return true;
	}

	//
	// tablistener template class 
	//
	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
	    private Fragment mFragment;
	    private final Activity mActivity;
	    private final String mTag;
	    private final Class<T> mClass;

	    public TabListener(Activity activity, String tag, Class<T> clz) 
	    {
	        mActivity = activity;
	        mTag = tag;
	        mClass = clz;
	    }

	    public void onTabSelected(Tab tab, FragmentTransaction ft) 
	    {
	        if (mFragment == null) 
	        {
	            mFragment = Fragment.instantiate(mActivity, mClass.getName());
	            ft.add(android.R.id.content, mFragment, mTag);
	        } else 
	        {
	            ft.attach(mFragment);
	        }
	    }

	    public void onTabUnselected(Tab tab, FragmentTransaction ft) 
	    {
	        if (mFragment != null) 
	            ft.detach(mFragment);
	    }

	    public void onTabReselected(Tab tab, FragmentTransaction ft) 
	    {
	    }
	}	
	
	//
	// Fragment za actionbar tab
	//
	public static class DetailsFragment extends Fragment 
	{
	    public static DetailsFragment newInstance(int index) {
	        DetailsFragment f = new DetailsFragment();

	        Bundle args = new Bundle();
	        args.putInt("index", index);
	        f.setArguments(args);

	        return f;
	    }

	    public int getShownIndex() {
	        return getArguments().getInt("index", 0);
	    }

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        if (container == null) {
	            return null;
	        }

	        return null;
	    }
	}		

	@SuppressWarnings("rawtypes")
	private Hashtable fetchData(String SelectStatement)
	{
           String result = null;
           InputStream is = null;

          
     		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
     		nameValuePairs.add(new BasicNameValuePair("s1",SelectStatement));
              try
              {
                  HttpClient httpclient = new DefaultHttpClient();
                  HttpPost httppost = new HttpPost("http://www.hornet.si/select.php");
                  httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                  HttpResponse response = httpclient.execute(httppost); 
                  HttpEntity entity = response.getEntity();
                  is = entity.getContent();

                  Log.e("log_tag", "connection success ");
              
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
                  
              }
              catch(Exception e)
              {
                 Log.e("log_tag", "Error converting result "+e.toString());

              }
              
              if (result.contains("null"))
            	  return new Hashtable();
              else
            	  return result2HashTable(result);
              
	}
	
	private Hashtable result2HashTable(String result)
	{
		Hashtable ht = new Hashtable();
				
		String tmp = result.substring(2, result.length());
		tmp = tmp.substring(0, tmp.length() - 4);
		
		String [] separated = tmp.split("\",\"");
		
		for (int i = 0; i < separated.length; i++)
		{
			String [] separated2 = separated[i].split("\":\"");
			
			separated2[0] = separated2[0].replace("\"", "");
			if (separated2.length > 1)
			{
				separated2[1] = separated2[1].replace("\"", "");			
				ht.put(separated2[0], separated2[1]);
			}
		}
				 		
		return ht;
	}		

	
}
