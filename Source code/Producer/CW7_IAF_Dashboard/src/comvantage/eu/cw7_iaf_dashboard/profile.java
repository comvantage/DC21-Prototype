package comvantage.eu.cw7_iaf_dashboard;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;

public class profile extends Activity  {

    public void onCreate(Bundle savedInstanceState) {
        
    	final Context context = this;
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        Button bCcancel = (Button) findViewById(R.id.bCancel);
        bCcancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(myIntent, 0);
            }
        });
        
        
        
        Button bSave = (Button)findViewById(R.id.bSave);
        bSave.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(context);

				builder.setTitle("Save Changes");
				
				builder
				.setMessage("Are you sure you want to save all changes?")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						profile.this.finish();
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});

				AlertDialog dialog = builder.create();
				
				dialog.show();
				
			}
		});
        
                
    }

   
}
