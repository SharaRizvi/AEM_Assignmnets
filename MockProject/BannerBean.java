package com.ttn.core.models;

public class BannerBean {
    private String heading;
    private String subHeading;
    private String ctaLink;
    private String imgLink;

    public String getHeading() {
        return heading;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public String getCtaLink() {
        return ctaLink;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public void setCtaLink(String ctaLink) {
        this.ctaLink = ctaLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }
}
