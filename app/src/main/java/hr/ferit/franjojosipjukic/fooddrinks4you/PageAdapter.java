package hr.ferit.franjojosipjukic.fooddrinks4you;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;


public class PageAdapter  extends FragmentStatePagerAdapter {

    private List<String> names;
    private List<Fragment> fragmentList;

    public PageAdapter(FragmentManager fm, List<String> names) {
        super(fm);
        this.names = names;
        fragmentList = new ArrayList<>();
    }

    public void addFragment(Fragment fragment){
        this.fragmentList.add(fragment);
    }

    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }



    public int getCount() {
        return this.fragmentList.size();
    }

}