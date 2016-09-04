package com.qigeairen.user.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.qigeairen.user.R;
import com.qigeairen.user.adapter.MarketGoodsAdapter1;
import com.qigeairen.user.entity.MarketGoods;
import com.qigeairen.user.presenter.MarketGoodsPresenter;
import com.qigeairen.user.view.MarketView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MarketActivity extends MVPBaseActivity<MarketView, MarketGoodsPresenter> implements MarketView {
    MarketGoodsAdapter1 mAdapter;
    List<MarketGoods> mGoodses;
    @InjectView(R.id.myrecycler)
    RecyclerView mMyrecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        ButterKnife.inject(this);
        //优品
        mPresenter = createPresenter();
        // mPresenter.attachView(this);
        mGoodses = mPresenter.getGoodsInfo();
        // test();
        showInfo(mGoodses, mAdapter);
    }

    public void test() {
        Toast.makeText(this, mGoodses.size() + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void back() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }


    @Override
    protected MarketGoodsPresenter createPresenter() {
        return new MarketGoodsPresenter();
    }

    @Override
    public void showFailMsg() {
        Toast.makeText(this, "加载数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInfo(List<MarketGoods> marketGoodsList, MarketGoodsAdapter1 marketGoodsAdapter) {
        marketGoodsList = mPresenter.getGoodsInfo();
        marketGoodsAdapter = new MarketGoodsAdapter1(this, marketGoodsList);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
            mMyrecycler.setLayoutManager(new GridLayoutManager(this, 2));
            mMyrecycler.setAdapter(marketGoodsAdapter);
        }

    }


}
