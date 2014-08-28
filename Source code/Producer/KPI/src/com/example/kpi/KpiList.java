package com.example.kpi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.kpi.MainActivity.DetailsFragment;
import com.example.kpi.MainActivity.TabListener;



















import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.OnNavigationListener;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class KpiList extends Activity {

	private ListView list;
	private ListView list_head;
	private ListView list_add;
	private static String filter1 = "All";
	private static String filter2 = "All";
	private static String filter3 = "All";
	
	myAdapterHeader mah;
	myAdapter ma;
	myAdapterAdd maa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kpilist);
		

		ActionBar actionBar = getActionBar();
		//actionBar.setDisplayHomeAsUpEnabled(true);		
		actionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_CUSTOM);
		//actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setCustomView(R.layout.custom_actionbar);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);		
		
        myDataHeader mdh [] = {new myDataHeader()};
        myDataAdd mda [] = {new myDataAdd()};
        
        myData md []={
        		new myData("1","Customers Aquired (customers/month)", "Marketing", "32", "30", "106 %", "View", R.drawable.arrowup),				
        		new myData("2","Customers Orders (orders/month)", "Marketing", "19", "25", "76 %", "View", R.drawable.arrowdown),        		
        		new myData("3","Effectiveness / Cycle Time (hours/product)", "Manufacturing", "6", "5", "85 %", "View", R.drawable.arrowdown),
        		new myData("4","Rejection rate of orders", "Manufacturing", "1", "0", "1 %", "View", R.drawable.arrowdown),
        		new myData("5","Carbon footprint (km/production)", "Sustainability", "853", "1000", "85 %", "View", R.drawable.arrowup),
        		new myData("6","Sales forecast", "Turnover/Revenue", "145.000", "250.000", "58 %", "View", R.drawable.arrowdown),
        		new myData("7","Procurement of materials", "Turnover/Revenue", "120", "100", "120 %", "View", R.drawable.arrowup)};

        list_head = (ListView) findViewById(R.id.listView1);        
        list = (ListView) findViewById(R.id.listView2); 
        list_add = (ListView) findViewById(R.id.listView3);

        Spinner sp1 = (Spinner) this.findViewById(R.id.abspinner1);
        Spinner sp2 = (Spinner) this.findViewById(R.id.abspinner2);
        Spinner sp3 = (Spinner) this.findViewById(R.id.abspinner3);           
        
        mah = new myAdapterHeader(this, mdh);
        ma = new myAdapter(this, md);
        maa = new myAdapterAdd(this, mda);
        
        Bundle b = getIntent().getExtras();
        int i = b.getInt("pos"); 
        if (i != 0)
        {
        	sp1.setSelection(i);
        	filter1 = (String) sp1.getSelectedItem().toString();
        	ma.getFilter().filter(filter1);
        	
        }    
        
    	switch (i)
    	{
			case 0: actionBar.setTitle("All KPIs");    	
    		case 1: actionBar.setTitle("Marketing KPIs");
    		case 2: actionBar.setTitle("Manufacturing KPIs");
    		case 3: actionBar.setTitle("Turnover / Revenue KPIs");
    		case 4: actionBar.setTitle("Sustainability KPIs");
    	}		
        
        
        
        list_head.setAdapter(mah);
        list.setAdapter(ma);
        list_add.setAdapter(maa);        
        
     
               
        sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
        	
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				filter1 = (String) parent.getItemAtPosition(position);
				if (filter1.equalsIgnoreCase("all"))
					ma.getFilter().filter(null);
				else
					ma.getFilter().filter(filter1);
				//ma.notifyDataSetChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}        	
        	
		});
        
            
        
        
        sp2.setOnItemSelectedListener(new OnItemSelectedListener() {
        	
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				filter2 = (String) parent.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}        	
        	
		});

        sp3.setOnItemSelectedListener(new OnItemSelectedListener() {
        	
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				filter3 = (String) parent.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}        	
        	
		});
        

	}		


/*		mygridItemClick = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String str = new String ("saso");
				// TODO Auto-generated method stub
				
			}};
		
		GridView myGrid = (GridView)findViewById(R.id.gridView1);
		
		
		myData md []={
		new myData("Customers Acquired:", "Customers Orders:", "32", "19", R.drawable.marketing),				
		new myData("Effectiveness:", "Rejection Rate:", "85 %", "1 %", R.drawable.manufacturing),
		new myData("Sales Forecast:", "Materials Procurement:", "145.000 €", "120 units", R.drawable.revenue),
		new myData("Carbon Footprint:", "", "85 %", "", R.drawable.sustainability),
		};
				
		myGrid.setAdapter(new myAdapter(this,md));
				*/
	//
	// class za addrow
	//
	private class myDataAdd
	{
		public String ID = "#";
		public String Name = "Name";		
		public String Category = "Category";
		public String Actual = "Actual";
		public String Target = "Target";
		public String PercentofTarget = "";
		public String Action = "Add";
	
	}			
	
	// wrapper za header
			private static class myAdapterAdd extends BaseAdapter {

			    Context context;
			    myDataAdd[] data;
			    private static LayoutInflater inflater = null;

			    public myAdapterAdd(Context context, myDataAdd[] data) 
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
			        if (vi == null)
			        {
			        		vi = inflater.inflate(R.layout.listitem_row_add, null);
			    	        
			        		TextView text = (TextView) vi.findViewById(R.id.txtadd1);
			    	        text.setText(data[position].ID);			    	        
			    	        EditText text2 = (EditText) vi.findViewById(R.id.txtadd2);
			    	        text2.setText(data[position].Name);
			    	        text2.setTextColor(Color.parseColor("#c9c9c9"));			    	        
			    	        EditText text3 = (EditText) vi.findViewById(R.id.txtadd3);
			    	        text3.setText(data[position].Category);
			    	        text3.setTextColor(Color.parseColor("#c9c9c9"));			    	        
			    	        EditText text4 = (EditText) vi.findViewById(R.id.txtadd4);
			    	        text4.setText(data[position].Actual);
			    	        text4.setTextColor(Color.parseColor("#c9c9c9"));			    	        
			    	        EditText text5 = (EditText) vi.findViewById(R.id.txtadd5);
			    	        text5.setText(data[position].Target);
			    	        text5.setTextColor(Color.parseColor("#c9c9c9"));
			    	        TextView text6 = (TextView) vi.findViewById(R.id.txtadd6);
			    	        text6.setText(data[position].PercentofTarget);
			    	        TextView text7 = (TextView) vi.findViewById(R.id.txtadd7);

			    	        SpannableString str = new SpannableString(data[position].Action);
			    	        str.setSpan(new UnderlineSpan(), 0, str.length(), Spanned.SPAN_PARAGRAPH);

			    	        text7.setText(str);		    	        
			        }        	        
			        
			        return vi;
			    }
			}		
			
	
	
	//
	// class za header
	//
	private class myDataHeader
	{
		public String ID = "ID";
		public String Name = "Name";		
		public String Category = "Category";
		public String Actual = "Actual";
		public String Target = "Target";
		public String PercentofTarget = "% of Target";
		public String Action = "Action";
	
	}	
	
	// wrapper za header
		private static class myAdapterHeader extends BaseAdapter {

		    Context context;
		    myDataHeader[] data;
		    private static LayoutInflater inflater = null;

		    public myAdapterHeader(Context context, myDataHeader[] data) 
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
		        if (vi == null)
		        {
		        		vi = inflater.inflate(R.layout.listitem_header, null);
		    	        
		        		TextView text = (TextView) vi.findViewById(R.id.txthdr1);
		    	        text.setText(data[position].ID);
		    	        TextView text2 = (TextView) vi.findViewById(R.id.txthdr2);
		    	        text2.setText(data[position].Name);
		    	        TextView text3 = (TextView) vi.findViewById(R.id.txthdr3);
		    	        text3.setText(data[position].Category);
		    	        TextView text4 = (TextView) vi.findViewById(R.id.txthdr4);
		    	        text4.setText(data[position].Actual);
		    	        TextView text5 = (TextView) vi.findViewById(R.id.txthdr5);
		    	        text5.setText(data[position].Target);
		    	        TextView text6 = (TextView) vi.findViewById(R.id.txthdr6);
		    	        text6.setText(data[position].PercentofTarget);
		    	        TextView text7 = (TextView) vi.findViewById(R.id.txthdr7);
		    	        text7.setText(data[position].Action);		    	        
		        }        	        
		        
		        return vi;
		    }
		}		
	
	
		
		
	//
	// class za listiteme
	//
	private static class myData
	{
		public String ID;
		public String Name;		
		public String Category;
		public String Actual;
		public String Target;
		public String PercentofTarget;
		public String Action;
		public int arrowID;
		
		
		public myData(String id, String name, String category, String actual, String target, String percentofTarget, String action, int ArrowID)
		{
			ID = id;
			Name = name;		
			Category = category;
			Actual = actual;
			Target = target;
			PercentofTarget = percentofTarget;
			Action = action;
			arrowID = ArrowID; 
		}
	
	}
	
	// wrapper za listiteme
	private static class myAdapter extends BaseAdapter implements Filterable {

	    static Context context;
	    static myData[] data;
	    static myData[] displayData;
	    private static LayoutInflater inflater = null;

		private class DataFilter extends Filter
		{

		    @Override
		    protected FilterResults performFiltering(CharSequence constraint) 
		    {
		        FilterResults result = new FilterResults();		        
		        if(constraint == null || constraint.length() == 0)
		        {
		            result.values = data;
		            result.count = data.length;
		        }
		        else
		        {
		        	ArrayList<myData> tmp = new ArrayList<myData>();
		         
		            for(myData j: data)
		            {
		                if(j.Category.contains(constraint))
		                {
		                	myData t = new KpiList.myData(j.ID, j.Name, j.Category, j.Actual, j.Target, j.PercentofTarget, j.Action, j.arrowID);
		                    tmp.add(t);
		                    
		                }
		            }
		            	            
		            myData[] tmp2 = tmp.toArray(new myData[tmp.size()]);
		           
		            result.values = tmp2;
		            result.count = tmp2.length;
		        }

		        return result;
		    }
		    
		    @SuppressWarnings("unchecked")
		    @Override
		    protected void publishResults(CharSequence constraint, FilterResults results) 
		    {
		        if (results.count == 0) 
		        {
		            notifyDataSetChanged();
		        } else 
		        {
		        	notifyDataSetChanged();
		        	displayData = null;	        			       		        	
		            displayData = (myData[]) results.values;
		            notifyDataSetInvalidated();
		            
		        }
		    }
		}
	    
	    
	    
	    public myAdapter(Context context, myData[] data) 
	    {
	        // TODO Auto-generated constructor stub
	        this.context = context;
	        this.data = data;
	        this.displayData = data;
	        inflater = (LayoutInflater) context
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	    @Override
	    public int getCount() 
	    {
	        // TODO Auto-generated method stub
	        return displayData.length;
	    }

	    @Override
	    public Object getItem(int position) 
	    {
	        // TODO Auto-generated method stub
	        return displayData[position];
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
	        if (vi == null)
	        	vi = inflater.inflate(R.layout.listitem_row, null);
	        //{
	        	//if ((filter1 == "All") && (filter2 == "All") && (filter3 == "All")) 
	        	//{
		        	
		        	
	        		TextView text = (TextView) vi.findViewById(R.id.txtrow1);
	    	        text.setText(displayData[position].ID);
	    	        TextView text2 = (TextView) vi.findViewById(R.id.txtrow2);
	    	        text2.setText(displayData[position].Name);
	    	        TextView text3 = (TextView) vi.findViewById(R.id.txtrow3);
	    	        text3.setText(displayData[position].Category);
	    	        TextView text4 = (TextView) vi.findViewById(R.id.txtrow4);
	    	        text4.setText(displayData[position].Actual);
	    	        EditText text5 = (EditText) vi.findViewById(R.id.txtrow5);
	    	        text5.setText(displayData[position].Target);
	    	        TextView text6 = (TextView) vi.findViewById(R.id.txtrow6);
	    	        text6.setText(displayData[position].PercentofTarget);
	    	        TextView text7 = (TextView) vi.findViewById(R.id.txtrow7);
	    	        
	    	        SpannableString str = new SpannableString(displayData[position].Action);
	    	        str.setSpan(new UnderlineSpan(), 0, str.length(), Spanned.SPAN_PARAGRAPH);
	    	        text7.setText(str);		
	    	        text7.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) 
						{
							// TODO Auto-generated method stub
							Intent intent = new Intent (context, kpi_details.class);
							context.startActivity(intent);													
							
						}
					});
	    	        
	    	        ImageView iv = (ImageView) vi.findViewById(R.id.imgArrow);
	    	        iv.setImageResource(displayData[position].arrowID);
	        	//}
	        	/*else if ((filter1 == data[position].Category) && (filter2 == "All") && (filter3 == "All"))
	        	{
		        	vi = inflater.inflate(R.layout.listitem_row, null);
		        	
	        		TextView text = (TextView) vi.findViewById(R.id.txtrow1);
	    	        text.setText(data[position].ID);
	    	        TextView text2 = (TextView) vi.findViewById(R.id.txtrow2);
	    	        text2.setText(data[position].Name);
	    	        TextView text3 = (TextView) vi.findViewById(R.id.txtrow3);
	    	        text3.setText(data[position].Category);
	    	        TextView text4 = (TextView) vi.findViewById(R.id.txtrow4);
	    	        text4.setText(data[position].Actual);
	    	        TextView text5 = (TextView) vi.findViewById(R.id.txtrow5);
	    	        text5.setText(data[position].Target);
	    	        TextView text6 = (TextView) vi.findViewById(R.id.txtrow6);
	    	        text6.setText(data[position].PercentofTarget);
	    	        TextView text7 = (TextView) vi.findViewById(R.id.txtrow7);
	    	        
	    	        SpannableString str = new SpannableString(data[position].Action);
	    	        str.setSpan(new UnderlineSpan(), 0, str.length(), Spanned.SPAN_PARAGRAPH);
	    	        text7.setText(str);		
	    	        
	    	        ImageView iv = (ImageView) vi.findViewById(R.id.imgArrow);
	    	        iv.setImageResource(data[position].arrowID);	        			        			        		
	        	}*/
	        		
	        //}        	        
	        
	        return vi;
	    }

		@Override
		public Filter getFilter() {
			// TODO Auto-generated method stub
			return new DataFilter();
		}
			
	}		
		
	
}
