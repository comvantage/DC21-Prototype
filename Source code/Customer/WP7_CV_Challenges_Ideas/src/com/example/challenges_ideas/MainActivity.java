package com.example.challenges_ideas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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

import com.example.challenges_ideas.R.id;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private static OnClickListener dodajIdejo, podrobnosti, voteUp;
	private boolean expand;
	private int initial_height; // za linear layout
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		StrictMode.setThreadPolicy(policy); 
		
		
		podrobnosti = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ------------------------
				// TODO KLIK NA DODAJ IDEJO
				// ------------------------
				
				Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512247-Chalange_idea_detail");
				//gotoApp.putExtra("login", extra1);
				startService(gotoApp);
				finish();
				
			}
		};
		
		
		dodajIdejo = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ------------------------
				// TODO KLIK NA DODAJ IDEJO
				// ------------------------
				
				Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
				gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
				gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21shop#Mobile_IT_support_feature_G-512241-Chalange_submit_idea");
				//gotoApp.putExtra("login", extra1);
				startService(gotoApp);
				finish();
				
			}
		};

		voteUp = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ----------------------------				
				// TODO VOTE UP EVENT
				// ----------------------------	
				
				TextView text4 = (TextView) ((View)v.getTag()).findViewById(R.id.txtThumbsCount);

				Integer i = new Integer(text4.getText().toString());
				i++;
								
				text4.setText(i.toString());
    	        
			}
		};
		
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		expand = true;
		
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

		
		// popravilo teksta 
		TextView tv1 = (TextView) findViewById(id.txtShirtSelection);
        SpannableString ss = new SpannableString(tv1.getText().toString());

    	ss.setSpan(new ForegroundColorSpan(0xff2266cc), ss.toString().indexOf("Kastner"), ss.toString().indexOf("Kastner") + "Kastner".length(), 0);
    	ss.setSpan(new ForegroundColorSpan(0xff2266cc), ss.toString().lastIndexOf("Medwed"), ss.toString().lastIndexOf("Medwed") + "Medwed".length(), 0);
    	ss.setSpan(new ForegroundColorSpan(0xff2266cc), ss.toString().indexOf("SanjaV"), ss.toString().indexOf("SanjaV") + "SanjaV".length(), 0);
    	ss.setSpan(new ForegroundColorSpan(0xff2266cc), ss.toString().indexOf("Modewerkstatt"), ss.toString().indexOf("Modewerkstatt") + "Modewerkstatt".length(), 0);
    	tv1.setText(ss);
				
		tv1.invalidate();					
									

		LinearLayout view = (LinearLayout) findViewById(R.id.layAnim);		
		initial_height = view.getHeight();
		
		refreshGrid();
		
		//Button bClose = (Button)findViewById(R.id.btnClose);
		
	}
	
	private void refreshGrid()
	{
		
		Hashtable ht = fetchData("select * from cv_challangesparticipate order by id desc LIMIT 1");
		
		GridView myGrid = (GridView)findViewById(R.id.gridView1);			
		
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
			
			InputStream URLcontent;
			Drawable image = null;
			
			try {
				URLcontent = (InputStream) new URL(ht.get("imgHTML").toString().replace("\\", "")).getContent();
				image = Drawable.createFromStream(URLcontent, "novaIMG");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			
			myData md []={
			new myData(getString(R.string.grid2),"",R.drawable.add_design, R.drawable.imageselector_submit_idea, 2, 0,0,0),
			new myData(ht.get("Naziv").toString(), "from " + ht.get("Odakle").toString() + ", " + sdf2.format(dateObj), image , R.drawable.imageselector_vote_up, 1, 1,Integer.parseInt(ht.get("Votes").toString()),0),
			new myData("Moving white lines","from Andrej Kovac, 05.10.2014",R.drawable.shirt2, R.drawable.imageselector_vote_up, 1, 10,2,1),
			new myData("Growing waves - The expandation","from Doris Martinz, 04.10.2014",R.drawable.shirt1, R.drawable.imageselector_vote_up, 1,16,7,2),
			new myData("Growing waves","from Miha Kuntu, 04.10.2014",R.drawable.shirt3, R.drawable.imageselector_vote_up, 1,13,5,0),
			new myData("Free hand design","from Marlies Narat, 02.10.2014",R.drawable.shirt4, R.drawable.imageselector_vote_up, 1,21,9,3),		
			new myData("Colorful","from Janez Krajnc, 22.10.2014",R.drawable.shirt5, R.drawable.imageselector_vote_up, 1,25,12,4)		
			};
			
			myGrid.setAdapter(new myAdapter(this,md));
		}
		else
		{		
			myData md []={
			new myData(getString(R.string.grid2),"",R.drawable.add_design, R.drawable.imageselector_submit_idea, 2, 0,0,0),
			new myData("Moving white lines","from Andrej Kovac, 05.10.2014",R.drawable.shirt2, R.drawable.imageselector_vote_up, 1, 10,2,1),
			new myData("Growing waves - The expandation","from Doris Martinz, 04.10.2014",R.drawable.shirt1, R.drawable.imageselector_vote_up, 1,16,7,2),
			new myData("Growing waves","from Miha Kuntu, 04.10.2014",R.drawable.shirt3, R.drawable.imageselector_vote_up, 1, 13,5,0),
			new myData("Free hand designs","from Marlies Narat, 02.10.2014",R.drawable.shirt4, R.drawable.imageselector_vote_up, 1,21,9,3),		
			new myData("Colorful","from Janez Krajnc, 22.10.2014",R.drawable.shirt5, R.drawable.imageselector_vote_up, 1,25,12,4)		
			};			
			
			myGrid.setAdapter(new myAdapter(this,md));
		}	
	}
	
	
	// animacija layouta
	public class HeightAnimation extends Animation 
	{
	    protected final int originalHeight;
	    protected final View view;
	    protected float perValue;

	    public HeightAnimation(View view, int fromHeight, int toHeight) 
	    {
	        this.view = view;
	        this.originalHeight = fromHeight;
	        this.perValue = (toHeight - fromHeight);
	    }

	    @Override
	    protected void applyTransformation(float interpolatedTime, Transformation t) 
	    {
	        view.getLayoutParams().height = (int) (originalHeight + perValue * interpolatedTime);
	        view.requestLayout();
	    }

	    @Override
	    public boolean willChangeBounds() 
	    {
	        return true;
	    }
	}

	public void onClick(View v)
	{
		TextView tmp = (TextView) v;
	
		TextView v1 = (TextView)findViewById(R.id.txtTitle);
		TextView v2 = (TextView)findViewById(R.id.txtDate);
		TextView v3 = (TextView)findViewById(R.id.txtOwner);
		TextView v4 = (TextView)findViewById(R.id.txtLikes);
		TextView v5 = (TextView)findViewById(R.id.txtFeedback);
		TextView v6 = (TextView)findViewById(R.id.txtViews);
		
		v1.setTextColor(Color.rgb(255, 136, 0));
		v2.setTextColor(Color.rgb(255, 136, 0));
		v3.setTextColor(Color.rgb(255, 136, 0));
		v4.setTextColor(Color.rgb(255, 136, 0));
		v5.setTextColor(Color.rgb(255, 136, 0));
		v6.setTextColor(Color.rgb(255, 136, 0));
		
		tmp.setTextColor(Color.BLACK);
	}	
	
	public void onMoreInfo(View v) 
	{
					
		LinearLayout view = (LinearLayout) findViewById(R.id.layAnim);
		TextView tv = (TextView) findViewById(R.id.txtMore);
		HeightAnimation heightAnim;
		

		
		if (expand)
		{
			initial_height= view.getHeight();			
			
		    heightAnim = new HeightAnimation(view, view.getHeight(), view.getHeight() + 170);
		    tv.setText("Less info");
		}
		else
		{
			heightAnim = new HeightAnimation(view, view.getHeight(), initial_height);
			tv.setText("More info");
		}
		
		expand = !expand;
		//view.measure(MeasureSpec.makeMeasureSpec(LayoutParams.MATCH_PARENT, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(LayoutParams.WRAP_CONTENT, MeasureSpec.EXACTLY));
	    //final int targetedHeight = view.getMeasuredHeight();
		
	    heightAnim.setDuration(800);
		view.startAnimation(heightAnim);
	
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

	//
	// class za listiteme
	//
	private class myData
	{
		public String Naziv;
		public String Odakle;
		public int displayImageR;
		public int displayButtonR;
		public int seeCount;
		public int thumbsCount;
		public int commentCount;
		public int itemtype;
		public Drawable img;
		
		public myData(String Ime, String Cigavo, int displayImage, int displayButton, int ItemType, int SeeCount, int ThumbsCount, int Comments)
		{
			Naziv = Ime;
			Odakle = Cigavo;
			displayImageR = displayImage;
			displayButtonR = displayButton;
			img = null;
			seeCount = SeeCount;
			thumbsCount = ThumbsCount;
			commentCount = Comments;
			itemtype = ItemType;
		}
	
		public myData(String Ime, String Cigavo, Drawable displayImage, int displayButton, int ItemType, int SeeCount, int ThumbsCount, int Comments)
		{
			Naziv = Ime;
			Odakle = Cigavo;
			displayImageR = -1;
			img = displayImage;
			displayButtonR = displayButton;
			seeCount = SeeCount;
			thumbsCount = ThumbsCount;
			commentCount = Comments;
			itemtype = ItemType;
		}		
		
	}
	
	// wrapper za listiteme
	private static class myAdapter extends BaseAdapter {

	    Context context;
	    myData[] data;
	    private static LayoutInflater inflater = null;

	    public myAdapter(Context context, myData[] data) 
	    {
	        // TODO Auto-generated constructor stub
	        this.context = context;
	        this.data = data;
	        inflater = (LayoutInflater) context
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	    @Override
	    public int getCount() 
	    {
	        // TODO Auto-generated method stub
	        return data.length;
	    }

	    @Override
	    public Object getItem(int position) 
	    {
	        // TODO Auto-generated method stub
	        return data[position];
	    }

	    @Override
	    public long getItemId(int position) 
	    {
	        // TODO Auto-generated method stub
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
	    	
	        // TODO Auto-generated method stub
	        View vi = convertView;
	        //if (convertView == null)
	        //{	        	
	        	if (data[position].itemtype == 1)
	        		vi = inflater.inflate(R.layout.griditem, null);
	        	else
	        		vi = inflater.inflate(R.layout.griditem2, null);	        	 	        
	        		        	
	        //}
	        //else
	        //	vi = (View) convertView;
	        
        	if (data[position].itemtype == 1)
        	{
        		//vi = inflater.inflate(R.layout.griditem, null);
    	        TextView text = (TextView) vi.findViewById(R.id.txtNaziv);
    	        text.setText(data[position].Naziv);
    	        TextView text2 = (TextView) vi.findViewById(R.id.txtFrom);
    	        text2.setText(data[position].Odakle);
    	        
    	        ImageView img1 = (ImageView) vi.findViewById(R.id.imgDisplayed);
    	        if (data[position].img == null)
    	        	img1.setImageResource(data[position].displayImageR);
    	        else
    	        	img1.setImageDrawable(data[position].img);
    	        img1.setOnClickListener(podrobnosti);
    	        img1.setTag(vi);
    	        
    	        ImageView img2 = (ImageView) vi.findViewById(R.id.imgButton);
    	        img2.setImageResource(data[position].displayButtonR);
    	        img2.setOnClickListener(voteUp);
    	        img2.setTag(vi);
    	        
    	        TextView text3= (TextView) vi.findViewById(R.id.txtSeeCount);
    	        text3.setText(Integer.toString(data[position].seeCount));

    	        TextView text4 = (TextView) vi.findViewById(R.id.txtThumbsCount);
    	        text4.setText(Integer.toString(data[position].thumbsCount));
    	        TextView text5 = (TextView) vi.findViewById(R.id.txtCommentCount);
    	        text5.setText(Integer.toString(data[position].commentCount));		        			        			        	
        	}
        	else if (data[position].itemtype == 2)
        	{
        		//vi = inflater.inflate(R.layout.griditem2, null);
    	        TextView text = (TextView) vi.findViewById(R.id.txtNazivgrd2);
    	        
    	        SpannableString ss = new SpannableString(data[position].Naziv);
    	    	ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), ss.toString().indexOf(" "), ss.toString().indexOf(" ") + " ".length(), 0);	    	        
    	        text.setText(ss);
    	        
    	        ImageView img1 = (ImageView) vi.findViewById(R.id.imgDisplayed);
    	        img1.setImageResource(data[position].displayImageR);
    	        img1.setOnClickListener(dodajIdejo);
    	        ImageView img2 = (ImageView) vi.findViewById(R.id.imgButtongrd2);
    	        img2.setImageResource(data[position].displayButtonR);
	    	    img2.setOnClickListener(dodajIdejo);   
    	        
        	}        	        
	        
	        return vi;
	    }
	}	
	
	public class gridItem
	{
		TextView text;
		TextView text2;
		ImageView img1;
		ImageView img2;
		TextView text3;
		TextView text4;
		TextView text5;

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
