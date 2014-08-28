package com.example.kpi;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

public class kpi_details extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kpi_details);
		

		ActionBar actionBar = getActionBar();
		//actionBar.setDisplayHomeAsUpEnabled(true);		
		actionBar.setNavigationMode(ActionBar.DISPLAY_SHOW_CUSTOM);
		//actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setCustomView(R.layout.custom_actionbar2);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowHomeEnabled(true);		
		
        Spinner sp2 = (Spinner) this.findViewById(R.id.abspinner4);
        Spinner sp3 = (Spinner) this.findViewById(R.id.abspinner5);             
        
        
        
        
        
	}
	
}
