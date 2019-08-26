package com.judian.goule.store.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.example.ccy.ccyui.util.Logger;
import com.gyf.barlibrary.ImmersionBar;
import com.judian.goule.store.R;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.HelpBean;
import com.judian.goule.store.presenter.HelpPresenter;
import com.judian.goule.store.views.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {


    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.help_back)
    ImageView helpBack;
    @BindView(R.id.qq)
    ImageView qq;

    private CommonBaseAdapter<HelpBean.ResultBean> adapterProblem;
    Unbinder unbinder;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help_new, container, false);
        unbinder = ButterKnife.bind(this, view);
        helpBack.setVisibility(View.GONE);
        qq.setVisibility(View.GONE);

        doBusiness(getContext());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onResume() {
        Logger.e("gggggggg","vvvvvvv== SpendMoneyFragment 1");
//        if (getActivity()!=null&& MainActivity.option==4){
//            ImmersionBar.with(getActivity())
//                    .fitsSystemWindows(true)
//                     .statusBarColor(R.color.white)
//                    .statusBarDarkFont(true,0.2f)
//                    .init();
//        }

        super.onResume();
    }

    public void doBusiness(Context mContext) {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true,0.2f)
                .init();

        adapterProblem = AdapterUtil.getArticle(getActivity());

        listView.setAdapter(adapterProblem);


        new HelpPresenter(getActivity()).getHelp("", new BaseView<HelpBean>() {
            @Override
            public void result(HelpBean bean) {
                adapterProblem.addAll(bean.getResult());
            }

            @Override
            public void error() {

            }
        });
    }
    String  mQq;

    @OnClick({R.id.help_back, R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.help_back:

                break;
            case R.id.qq:

                break;


        }
    }

}
