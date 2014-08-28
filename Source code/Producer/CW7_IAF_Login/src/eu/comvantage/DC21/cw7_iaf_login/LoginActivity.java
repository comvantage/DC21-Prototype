package eu.comvantage.DC21.cw7_iaf_login;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	private EditText username;
	private String login = "0";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		
		
		Button bNext = (Button)findViewById(R.id.bLogin);
		
        bNext.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				username = (EditText)findViewById(R.id.etUsername);
				
				if ( username.getText().toString().equals("proshirt") )
					{
						login = "1";
					}
				else if ( username.getText().toString().equals("stickit") )
				{
					login = "2";
				}
					
					
	                goToNextApp();
	                //finish();
			}
		});
		
	}

	
	public boolean goToNextApp() {
		
		Intent gotoApp = new Intent("eu.comvantage.iaf.APP_URI");
		gotoApp.addCategory("eu.comvantage.iaf.category.WORKFLOW");
		gotoApp.putExtra("uri", "http://www.comvantage.eu/dc21#Mobile_IT_support_feature_G-512054-Dashboard");
		gotoApp.putExtra("login", login);
		startService(gotoApp);
		return true;
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}
