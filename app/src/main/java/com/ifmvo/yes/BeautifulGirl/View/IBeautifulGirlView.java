package com.ifmvo.yes.BeautifulGirl.View;

import com.ifmvo.yes.BeautifulGirl.Model.Girl;

import java.util.List;

/**
 * ifmvo on 2016/3/29.
 */
public interface IBeautifulGirlView {

    void loadGirlDataToListView(List<Girl> girls);

    void notifyAdapter();

    void showProgressBar(boolean show);

    void showCannotLoad(boolean show);

    void setHasMoreData(boolean hasMoreData);

}
