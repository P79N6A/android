package com.judian.goule.store.fragment.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.judian.goule.store.R;

/**
 * 收益消息
 */

public class EarningsMessagesFragment extends Fragment {

    public EarningsMessagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_earnings_messages, container, false);
        return view;
    }
}
