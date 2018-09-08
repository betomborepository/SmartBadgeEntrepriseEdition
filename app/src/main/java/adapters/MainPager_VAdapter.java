package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.smart.badge.StatistiqueEmploye;

import adapters.entity.Eleve;
import fragments.Employe;
import fragments.Home;
import fragments.HomeCantine;
import fragments.HomeEmploye;
import fragments.Pointage;
import fragments.PointageEmploye;
import fragments.Satistique;
import fragments.TransactionCantine;

/**
 * Created by hp on 21/05/2018.
 */

public class MainPager_VAdapter  extends FragmentStatePagerAdapter
{
    private CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when PermsPagerAdapter is created
    private int NumbOfTabs; // Store the number of tabs, this will also be passed when the PermsPagerAdapter is created
    private  String userType;
    CharSequence EmployeTitles[] = {"Home", "Pointage"};
    int EmployeNumboftabs = 2;
    CharSequence CantineTitles[] = {"Home", "Transaction"};
    int CantineNumboftabs = 2 ;

    public MainPager_VAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, String userType)
    {
        super(fm);
        if(userType.equals( "userEmploy")){
            this.NumbOfTabs = this.EmployeNumboftabs;
            this.Titles = EmployeTitles;
        }if (userType.equals( "cantine")){
        this.NumbOfTabs = this.CantineNumboftabs;
            this.Titles = CantineTitles;

        }else if( userType.isEmpty()){
        this.NumbOfTabs = this.EmployeNumboftabs;
        this.Titles = EmployeTitles;

    }else {
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

        this.userType = userType;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(userType.equals( "admin")){
            return getAdminItem(position);
        }else  if(userType.equals("cantine")){
            return  getCantineItem(position);
        }else  if(userType.equals("userEmploy")){
            return  getEmployeItem(position);
        }else {
            return  getEmployeItem(position);
        }
    }

    private  Fragment getAdminItem(int position){
        if (position == 0) { // if the position is 0 we are returning the First tab
            return new Home();
        } else if (position == 1) {
            return new Employe();
        } else if (position == 2) {
            return new Pointage();
        } else if (position == 3){
            return new Satistique();
        }else {
            return new Pointage();
        }
    }

    private  Fragment getEmployeItem(int position){
        if (position == 0) { // if the position is 0 we are returning the First tab
            return new HomeEmploye();
        } else if (position == 1) {
            return new PointageEmploye();
        }else {
            return new PointageEmploye();
        }
    }


    private  Fragment getCantineItem(int position){
        if (position == 0) { // if the position is 0 we are returning the First tab
            return new HomeCantine();
        } else if (position == 1) {
            return new TransactionCantine();
        }else {
            return new TransactionCantine();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
