package com.appmarket.mapleaf.applicationmarket.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appmarket.mapleaf.applicationmarket.bean.AppInfo;
import com.appmarket.mapleaf.applicationmarket.utils.UIUtils;
import com.appmarket.mapleaf.applicationmarket.view.LoadingPage;

import java.util.ArrayList;

/**
 * Created by Mapleaf on 2016/7/8.
 */
public abstract class BaseFragment extends Fragment{
    private LoadingPage loadingPage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(UIUtils.getContext()) {
            @Override
            public int onLoad() {
                return BaseFragment.this.onLoad();
            }

            @Override
            public View onCreateSuccessView() {
                return BaseFragment.this.onCreateSuccessView();
            }
        };
        return loadingPage;
    }
    public void loadData(){
        loadingPage.loadData();
    }
    public abstract View onCreateSuccessView();
    public abstract int onLoad();
    protected int check(Object obj){
        if(obj!=null){
            if(obj instanceof ArrayList){
                ArrayList list = (ArrayList) obj;
                if(list.isEmpty()){
                    return LoadingPage.STATER_EMPTY;
                }else{
                    return LoadingPage.STATER_SUCCESS;
                }
            }
        }
        return LoadingPage.STATER_ERROR;
    }
}
