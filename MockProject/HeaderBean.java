package com.ttn.core.models;

import java.util.ArrayList;
import java.util.List;

public class HeaderBean {
    private String pageTitle;
    private String pageLink;
    private List<ListPageDetail> dataFromModelList=new ArrayList<>();

    public String getPageTitle() {
        return pageTitle;
    }
    public void setTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
    public String getPageLink() {
        return pageLink;
    }
    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    public void setDataFromModelList(List<ListPageDetail> dataFromModelList) {
        this.dataFromModelList = dataFromModelList;
    }

    public List<ListPageDetail> getDataFromModelList() {
        return dataFromModelList;
    }
}
