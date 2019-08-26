package com.judian.goule.store.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.ccy.ccyui.adapter.CommonBaseAdapter;
import com.judian.goule.store.adapter.AdapterUtil;
import com.judian.goule.store.bean.BounsBean;
import com.judian.goule.store.presenter.CdataPresenter;
import com.judian.goule.store.views.BaseView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BounsFragment extends Fragment  {


    GridView myGv;
    private String mType;
    private CommonBaseAdapter<BounsBean.ResultBean> bounsList;
//    private PullToRefresh refresh;
    private CdataPresenter presenter;

    public BounsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_order, container, false);
//        unbinder = ButterKnife.bind(this, view);
        presenter = new CdataPresenter(getContext());


        myGv=new GridView(getContext());
        initList();
        return myGv;
    }

    private void initList() {
        myGv.setNumColumns(1);
        bounsList = AdapterUtil.getBounsList(getActivity());
       myGv.setAdapter(bounsList);
        presenter.getBounsList(mType,page, view);

    }
           BaseView<BounsBean> view=  new BaseView<BounsBean>() {
        @Override
        public void result(BounsBean bean) {
            if (bean.getCode()==200){
                bounsList.addAll(bean.getResult());
            }

        }

        @Override
        public void error() {
        }
    };


    public void bind(String type) {
        mType = type;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
  int  page=1;

    public void shuaxin() {
      page=1;
        bounsList.setNull();
        presenter.getBounsList(mType,page, view);
    }


    public void jiazai() {


    }
}
