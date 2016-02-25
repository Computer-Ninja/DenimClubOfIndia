package extras;

import android.R.color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPageAdapter extends FragmentPagerAdapter{

//	public TabPageAdapter(FragmentManager fm) {
//		super(fm);
//		
//	}

	int totalNoOfPage=0;
	public TabPageAdapter(FragmentManager supportFragmentManager, int totalNoOfPage) {
		// TODO Auto-generated constructor stub
		super(supportFragmentManager);
		this.totalNoOfPage = totalNoOfPage;
	}

	@Override
	public Fragment getItem(int arg0) {
		Bundle bundle = new Bundle();
		
		
		bundle.putInt("page",arg0);
//		bundle.putInt("color", colorResId);
		
		SwipeTabFragment swipeTabFragment = new SwipeTabFragment();
		swipeTabFragment.setArguments(bundle);
		return swipeTabFragment;
		
	
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return totalNoOfPage;
	}

}
