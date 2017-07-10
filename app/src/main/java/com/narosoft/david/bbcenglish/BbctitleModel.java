package com.narosoft.david.bbcenglish;

/**
 * Created by David on 2017/7/9.
 */

public class BbctitleModel {

    String BbcId;
    String Title;
    String DescCn;
    String Title_cn;
    String Category;
    String Sound;
    String Url;
    String Pic;
    String CreatTime;
    String PublishTime;
    String ReadCount;
    String HotFlg;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getBbcId() {
        return BbcId;
    }

    public void setBbcId(String bbcId) {
        BbcId = bbcId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescCn() {
        return DescCn;
    }

    public void setDescCn(String descCn) {
        DescCn = descCn;
    }

    public String getTitle_cn() {
        return Title_cn;
    }

    public void setTitle_cn(String title_cn) {
        Title_cn = title_cn;
    }

    public String getSound() {
        return Sound;
    }

    public void setSound(String sound) {
        Sound = sound;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public String getCreatTime() {
        return CreatTime;
    }

    public void setCreatTime(String creatTime) {
        CreatTime = creatTime;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }

    public String getReadCount() {
        return ReadCount;
    }

    public void setReadCount(String readCount) {
        ReadCount = readCount;
    }

    public String getHotFlg() {
        return HotFlg;
    }

    public void setHotFlg(String hotFlg) {
        HotFlg = hotFlg;
    }

    @Override
    public String toString() {
        return "BbctitleModel{" +
                "BbcId='" + BbcId + '\'' +
                ", Title='" + Title + '\'' +
                ", DescCn='" + DescCn + '\'' +
                ", Title_cn='" + Title_cn + '\'' +
                ", Category='" + Category + '\'' +
                ", Sound='" + Sound + '\'' +
                ", Url='" + Url + '\'' +
                ", Pic='" + Pic + '\'' +
                ", CreatTime='" + CreatTime + '\'' +
                ", PublishTime='" + PublishTime + '\'' +
                ", ReadCount='" + ReadCount + '\'' +
                ", HotFlg='" + HotFlg + '\'' +
                '}';
    }
}
