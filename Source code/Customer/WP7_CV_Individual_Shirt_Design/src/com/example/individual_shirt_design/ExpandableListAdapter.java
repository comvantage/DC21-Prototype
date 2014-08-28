package com.example.individual_shirt_design;

import com.example.individual_shirt_design.*;

import java.util.HashMap;
import java.util.List;
 

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;


public class ExpandableListAdapter extends BaseExpandableListAdapter 
{
	public static class myItem
	{
		public int rID;
		public String naziv;
		public int tip = 0;
		
		public myItem(int RID, String text, int Tip)
		{
			rID = RID;
			naziv = text;		
			tip = Tip;
		}		
		
		
	}
	
	public static class myGroup
	{
		public String Header;
		public String subHeader;
		
		public myGroup(String _Header, String _subHeader)
		{
			Header=_Header;
			subHeader=_subHeader;			
		}
		
	}
		private int lastViewType = 0;
		private Context _context;
	    private List<myGroup> _listDataHeader; // header titles
	    // child data in format of header title, child title
	    private HashMap<myGroup, List<myItem>> _listDataChild;
	 
	    public ExpandableListAdapter(Context context, List<myGroup> listDataHeader,
	            HashMap<myGroup, List<myItem>> listChildData) {
	        this._context = context;
	        this._listDataHeader = listDataHeader;
	        this._listDataChild = listChildData;
	    }
	 
	    @Override
	    public Object getChild(int groupPosition, int childPosititon) {
	        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
	                .get(childPosititon);
	    }
	 
	    @Override
	    public long getChildId(int groupPosition, int childPosition) {
	        return childPosition;
	    }
	 
	    @Override
	    public View getChildView(int groupPosition, final int childPosition,
	            boolean isLastChild, View convertView, ViewGroup parent) 
	    	{
	 
	        final myItem child = (myItem) getChild(groupPosition, childPosition);
	        
	        
	        //if (convertView == null) 
	        //{

        		LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	        	if (child.tip == 0)
	        		convertView = infalInflater.inflate(R.layout.grid_item, null);
	        	else
	        		convertView = infalInflater.inflate(R.layout.grid_item_2, null);
	        	
	        //}	        


        	if (child.tip == 0)
        	{
        		TextView txtListChild = (TextView) convertView.findViewById(R.id.title_item);
   	    	 
    	        txtListChild.setText(child.naziv);
    	        
    	        ImageView imgView = (ImageView) convertView.findViewById(R.id.img_item);
    	        imgView.setImageResource(child.rID);	   
    	        lastViewType = child.tip;
    	        
	        }
        	else if (child.tip == 1)
        	{	
        		Spinner spinner = (Spinner) convertView.findViewById(R.id.spinner1);
        		
        		lastViewType = child.tip;
        		
        		if (spinner.getAdapter() == null)
        		{

        		final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this._context,R.array.shirt_sizes, android.R.layout.simple_spinner_item);
        		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);        
        		
        		spinner.setAdapter(adapter);
        		
        		}
        		
        		final TextView txtListChild = (TextView) convertView
    	                .findViewById(R.id.editKolicina);
        						
				
        		Button btnplus = (Button) convertView.findViewById(R.id.button_plus);
        		Button btnminus = (Button) convertView.findViewById(R.id.button_minus);
        		
        		btnplus.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						int i = Integer.parseInt(txtListChild.getText().toString()) ;
						i++;
						txtListChild.setText(Integer.toString(i));
						
					}
				});
        		
        		btnminus.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						int i = Integer.parseInt(txtListChild.getText().toString()) ;
						if (i > 1)
						{
							i--;
							txtListChild.setText(Integer.toString(i));
						}

						
					}
				});
        		
	        
        	}
	        	        
	        return convertView;	        	       
	        
	    }
	 
	    @Override
	    public int getChildrenCount(int groupPosition) {
	        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
	                .size();
	    }
	 
	    @Override
	    public Object getGroup(int groupPosition) {
	        return this._listDataHeader.get(groupPosition);
	    }
	 
	    @Override
	    public int getGroupCount() {
	        return this._listDataHeader.size();
	    }
	 
	    @Override
	    public long getGroupId(int groupPosition) {
	        return groupPosition;
	    }
	 
	    @Override
	    public View getGroupView(int groupPosition, boolean isExpanded,
	            View convertView, ViewGroup parent) {
	        
	    	myGroup headerTitle = (myGroup) getGroup(groupPosition);
	        
	        if (convertView == null) {
	            LayoutInflater infalInflater = (LayoutInflater) this._context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = infalInflater.inflate(R.layout.group_list, null);
	        }
	 
	        TextView lblListHeader = (TextView) convertView
	                .findViewById(R.id.group_title);
	        lblListHeader.setText(headerTitle.Header);
	 
	        TextView lblListSubHeader = (TextView) convertView
	                .findViewById(R.id.group_subtitle);
	        
	        lblListSubHeader.setText(headerTitle.subHeader);
	        
	        return convertView;
	    }
	 
	    @Override
	    public boolean hasStableIds() {
	        return false;
	    }
	 
	    @Override
	    public boolean isChildSelectable(int groupPosition, int childPosition) {
	        return true;
	    }
			
}


