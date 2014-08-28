package com.example.challenge_participate;

import java.io.BufferedReader;
import java.io.File;
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
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity 
{

	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		StrictMode.setThreadPolicy(policy); 

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
		
		EditText ev1 = (EditText) findViewById(R.id.edtTitle);
		ev1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
				TextView tv1 = (TextView) findViewById(R.id.lbltitle_red);
				tv1.setText(Integer.toString(50-s.toString().length()));
				
				
			}
		});
		
		EditText ev2 = (EditText) findViewById(R.id.edtIdea);		
		ev2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
				TextView tv1 = (TextView) findViewById(R.id.lblidea_red);
				tv1.setText(Integer.toString(400-s.toString().length()));								
			}
		});
		
		
		//Button1
		ImageView dirChooserButton1 = (ImageView) findViewById(R.id.btnUpload); 
		dirChooserButton1.setOnClickListener(new OnClickListener()
		{ 
			String m_chosen; 
			@Override 
			public void onClick(View v) 
			{ 
	
				SimpleFileDialog FileOpenDialog = new SimpleFileDialog(MainActivity.this, "FileOpen", new SimpleFileDialog.SimpleFileDialogListener()
				{
					@Override
					public void onChosenDir(String chosenDir)
					{
		
						m_chosen = chosenDir;

						TextView tv1 = (TextView) findViewById(R.id.edtImage);
						tv1.setText(m_chosen);								
						
						Toast.makeText(MainActivity.this, "Chosen file: " + m_chosen, Toast.LENGTH_LONG).show();
						
						ImageView iv = (ImageView) findViewById(R.id.imgContent);
						File imgFile = new File(m_chosen);
						
						if (imgFile.exists())
						{
							Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
							iv.setImageBitmap(myBitmap);														
						}
						
						
					}
				});
				
				FileOpenDialog.Default_File_Name = "";
				FileOpenDialog.chooseFile_or_Dir();
			}
		});		
		
		ImageView btnSubmit = (ImageView) findViewById(R.id.btnSubmitIdea);
		btnSubmit.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{

				// TODO: TUKAJ SE JE TREBA (PO INSERTU) VRNITI V APLIKACIJO 
				
				ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

				TextView ev1 = (TextView) findViewById(R.id.edtTitle);
				TextView ev2 = (TextView) findViewById(R.id.edtIdea);								
				
				String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
														
				String odakle ="Peter Novak";			// !"#!#"!#!"#!" PAZI !"#!"#!"#!#!"#"!#
				String imgHTML = "http://www.hornet.si/images/shirts2.png"; // !"#!#"!#!"#!" PAZI !"#!"#!"#!#!"#"!#
				
	            nameValuePairs.add(new BasicNameValuePair("u1","INSERT INTO cv_challangesparticipate(Naziv, Odakle, Datum, imgHTML, Description) VALUES ('" + ev1.getText() + "','" + odakle +"','"+ sdf.format(cal.getTime()) + "','" + imgHTML + "','" + ev2.getText() + "')"));
                try
                {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://www.hornet.si/update.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpclient.execute(httppost); 
                    HttpEntity entity = response.getEntity();

                    Log.e("log_tag", "connection success ");
                    
                    
                    
                   
							Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
							gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
							gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512244-Chalange_detail");
							//gotoApp.putExtra("login", Integer.toString(login));
							startService(gotoApp);
							finish();
						
                    
	                
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

	        //ScrollView scroller = new ScrollView(getActivity());
	        //TextView text = new TextView(getActivity());
	        //int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
	        //        4, getActivity().getResources().getDisplayMetrics());
	        //text.setPadding(padding, padding, padding, padding);
	        //scroller.addView(text);
	        //text.setText("");
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
              
              return result2HashTable(result);
              
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
