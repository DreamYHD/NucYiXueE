package androidlab.edu.cn.nucyixue.ui.teachPack;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import androidlab.edu.cn.nucyixue.R;
import androidlab.edu.cn.nucyixue.base.BaseFragment;
import androidlab.edu.cn.nucyixue.ui.common.live.LiveFragment;
import androidlab.edu.cn.nucyixue.ui.teachPack.source.TeachSourceFragment;
import androidlab.edu.cn.nucyixue.ui.teachPack.reward.RewardFragment;
import androidlab.edu.cn.nucyixue.utils.config.LiveFragmentType;
import butterknife.BindView;

/**
 * TeachFragment
 *
 * A simple {@link Fragment} subclass.
 */
public class TeachFragment extends BaseFragment {

    @BindView(R.id.teach_main_tablayout)
    TabLayout mTeachMainTablayout;
    @BindView(R.id.teach_main_viewpager)
    ViewPager mTeachMainViewpager;
    @BindView(R.id.toolbar_teach)
    Toolbar mToolbarTeach;

    private String[] mString = {"Live", "资源", "悬赏"};
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    public static TeachFragment getInstance() {
        return new TeachFragment();
    }

    @Override
    protected void init(View mView, Bundle mSavedInstanceState) {
        initToolbar();
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbarTeach);
        setHasOptionsMenu(true); // 设置 Fragment 标题
        mToolbarTeach.setTitle(getString(R.string.me_fragment_xuanshange));
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_teach;
    }

    @Override
    protected void logic() {
        LiveFragment mLive = LiveFragment.getInstance();
        Bundle bundle = new Bundle();
        bundle.putString(LiveFragmentType.getLIVE_FRAGMENT_TYPE(), LiveFragmentType.getCOMMON());
        mLive.setArguments(bundle);
        RewardFragment mReward = RewardFragment.getInstance();
        TeachSourceFragment mSource = TeachSourceFragment.getInstance();

        mFragmentList.add(mLive);
        mFragmentList.add(mSource);
        mFragmentList.add(mReward);

        TeachFragmentPagerAdapter mTeachFragmentPagerAdapter = new TeachFragmentPagerAdapter(getChildFragmentManager(), mString, mFragmentList);
        mTeachMainViewpager.setAdapter(mTeachFragmentPagerAdapter);
        mTeachMainTablayout.setupWithViewPager(mTeachMainViewpager);
    }

    private class TeachFragmentPagerAdapter extends FragmentPagerAdapter {

        private String[] mStringList;
        private List<Fragment> mFragmentList = new ArrayList<>();

        TeachFragmentPagerAdapter(FragmentManager fm, String[] mStringList, List<Fragment> mFragmentList) {
            super(fm);
            this.mStringList = mStringList;
            this.mFragmentList = mFragmentList;
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mStringList[position];
        }

    }


}
