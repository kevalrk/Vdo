package uvdos.vdo;

/**
 * Created by  on 9/22/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class UrlProvider extends Activity {

    private EditText editText;

    private Button button;


    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.url_link);



        editText=(EditText) findViewById(R.id.url_name);
        button = (Button) findViewById(R.id.btn_clik);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OnlineStreaming.class);
                intent.putExtra("URI",editText.getText().toString() );
                startActivity(intent);

            }
        } );

    }
}


