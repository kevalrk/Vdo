package uvdos.vdo;

/**
 * Created by  on 9/22/2016.
 */

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
public class SearchableActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //query to search your data somehow

        }
    }

}

