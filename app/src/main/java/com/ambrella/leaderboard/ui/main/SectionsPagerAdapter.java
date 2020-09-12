package com.ambrella.leaderboard.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
	    private static int NUM_ITEMS = 2;
		
        public SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        
        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
 
        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
            case 0: // Fragment # 0 - This will show Learning Hours Leaders
                return LearningLeadersFragment.newInstance();
            case 1: // Fragment # 0 - This will show Skilled IQ Leaders
                return SkillIQLeadersFragment.newInstance();
            default:
            	return null;
            }
        }
        
        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return "Learning Leaders";
            }else if(position == 1){
                return "Skill IQ Leaders ";
            }
            return "Other";

        }
        
    }