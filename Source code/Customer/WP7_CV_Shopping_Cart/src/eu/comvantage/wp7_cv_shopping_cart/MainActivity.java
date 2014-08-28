package eu.comvantage.wp7_cv_shopping_cart;

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
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private int active01, active02;
	
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.setThreadPolicy(policy);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		 
		active01 = ActiveShirt(1);
		active02 = ActiveShirt(2);
		
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
		
		 
		 //skrivanje vrstice v tabeli
		 TableRow tabR01 = (TableRow) findViewById(R.id.tableRow3);
		 TextView tv17 = (TextView)findViewById(R.id.textView17);
		 
		 TableRow tabR02 = (TableRow) findViewById(R.id.TableRow01);
		
		 
		 
		 if (active01 == 1 && active02 == 1) { //both shirts
			 tabR01.setVisibility(View.VISIBLE);
			 tabR02.setVisibility(View.VISIBLE);
			 tv17.setText("174,99 €");
		 }
		 else if (active01 == 1 && active02 == 0) {//shirt1
			 tabR01.setVisibility(View.VISIBLE);
			 tabR02.setVisibility(View.INVISIBLE);
			 tv17.setText("99,99 €");
		 }
		 else if (active01 == 0 && active02 == 1) {
			 tabR01.setVisibility(View.INVISIBLE);
			 tabR02.setVisibility(View.VISIBLE);
			 tv17.setText("75,00 €"); 
		 }
		 else {
			 tabR01.setVisibility(View.INVISIBLE);
			 tabR02.setVisibility(View.INVISIBLE);
			 tv17.setText("00,00 €");
		 }
		 
		
		Button bShopping = (Button) findViewById(R.id.bShopping);
		bShopping.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512214-DC21_Shop_Dashboard");
				//gotoApp.putExtra("login", extra1);
				startService(gotoApp);
            }
        
        });
		
		Button bCheckout = (Button) findViewById(R.id.bCheckout);
		bCheckout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	
            	Intent myIntent = new Intent(view.getContext(), CheckOut.class);
                startActivity(myIntent);
            	
            	
            }
        
        });
		
		Button bEditPrice = (Button) findViewById(R.id.bEditPrice);
		bEditPrice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512235-Price_and_delivery");
				//gotoApp.putExtra("login", extra1);
				startService(gotoApp);
            }
        
        });
		
		Button bDelete = (Button) findViewById(R.id.bDelete);
		bDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	new AlertDialog.Builder(MainActivity.this).setTitle("Delete Product")
            		.setMessage("Are you sure you want to delete selected product?")
            		.setCancelable(false)
            		.setPositiveButton("Yes", null)
            		.setNegativeButton("No", null)
            		.show();
            }
        
        });
		
		Spinner s_size1 = (Spinner) findViewById(R.id.s_size);
        ArrayAdapter<CharSequence> adapter01 = ArrayAdapter.createFromResource(
                this, R.array.ShirtSize, android.R.layout.simple_spinner_item);
        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_size1.setAdapter(adapter01);
        s_size1.setSelection(2);
        
        Spinner s_size2 = (Spinner) findViewById(R.id.Spinner01);
        ArrayAdapter<CharSequence> adapter02 = ArrayAdapter.createFromResource(
                this, R.array.ShirtSize, android.R.layout.simple_spinner_item);
        adapter02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_size2.setAdapter(adapter02);
        s_size2.setSelection(1);
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_menu, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_cart:
	    	Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
			gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
			gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512238-Shopping_cart");
			//gotoApp.putExtra("login", extra1);
			startService(gotoApp); 
	      break;
	    case R.id.action_filter:
	      Toast.makeText(this, "Filter selected.", Toast.LENGTH_SHORT)
	          .show();
	      break;
	    default:
	      break;
	    }

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
