package com.callisto.d5proj.activities;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.callisto.d5proj.R;
import com.callisto.d5proj.db.tables.CharacterClassesHelper;
import com.callisto.d5proj.db.tables.ExperienceLevels;
import com.callisto.d5proj.enums.BaseStatistic;
import com.callisto.d5proj.fragments.BaseStatsFragment;
import com.callisto.d5proj.fragments.DerivedStatsFragment;
import com.callisto.d5proj.fragments.NavigationDrawerFragment;
import com.callisto.d5proj.fragments.dialogs.SelectClassDialogFragment;
import com.callisto.d5proj.interfaces.OnStatChangeListener;
import com.callisto.d5proj.pojos.CharacterClass;
import com.callisto.d5proj.pojos.Level;
import com.callisto.d5proj.xml.ExperienceTableXMLHandler;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class CharacterCreationActivity extends ActionBarActivity
    implements NavigationDrawerFragment.NavigationDrawerCallbacks,
    OnStatChangeListener {

    /**
     * Database stuff
     */
    private CharacterClassesHelper dbCharacterClasses;
    private ExperienceLevels dbCharacterLevels;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private BaseStatsFragment baseStatsFragment;
    private DerivedStatsFragment derivedStatsFragment;
    private SelectClassDialogFragment selectClassDFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
            getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
            R.id.navigation_drawer,
            (DrawerLayout) findViewById(R.id.drawer_layout));

        baseStatsFragment = BaseStatsFragment.newInstance(1);
        derivedStatsFragment = DerivedStatsFragment.newInstance(2);
        selectClassDFragment = SelectClassDialogFragment.newInstance(3);

        dbCharacterClasses = new CharacterClassesHelper(this);
        dbCharacterLevels = new ExperienceLevels(this);

//        parseXML();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if (baseStatsFragment == null) {
            baseStatsFragment = BaseStatsFragment.newInstance(1);
        }

        if (derivedStatsFragment == null) {
            derivedStatsFragment = DerivedStatsFragment.newInstance(2);
        }

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch(position) {
        case 0:
            fragmentManager.beginTransaction()
                .replace(R.id.container, baseStatsFragment)
                .commit();
            break;
        case 1:
            fragmentManager.beginTransaction()
                .replace(R.id.container, derivedStatsFragment)
                .commit();
            break;
        case 2:
            fragmentManager.beginTransaction()
                .replace(R.id.container, selectClassDFragment)
                .commit();
            break;
        default:
            break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
        case 1:
            mTitle = getString(R.string.base_stats);
            break;
        case 2:
            mTitle = getString(R.string.derived_stats);
            break;
        case 3:
            mTitle = getString(R.string.title_section3);
            break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStatChange(BaseStatistic stat, int value) {
        if (derivedStatsFragment != null) {
            derivedStatsFragment.setStatFromBuilder(stat, value);
        } else {
            Toast.makeText(this, "Derived stats fragment has not been initialized", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<Level> getExperienceTable() {
        ArrayList<Level> result = null;

        AssetManager assetManager = getBaseContext().getAssets();
        try {
            InputStream is = assetManager.open("ExperienceTable.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();

            ExperienceTableXMLHandler tableXMLHandler = new ExperienceTableXMLHandler();
            xr.setContentHandler(tableXMLHandler);
            InputSource inStream = new InputSource(is);
            xr.parse(inStream);

            result = tableXMLHandler.getLevels();

            is.close();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<CharacterClass> getCharacterClasses() {
        return dbCharacterClasses.getArrayList();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.frag_placeholder, container, false);
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((CharacterCreationActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
}
