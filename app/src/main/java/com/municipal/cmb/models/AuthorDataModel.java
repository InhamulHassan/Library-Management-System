package com.municipal.cmb.models;

/**
 * Created by RazR on 7/1/2018.
 */

public class AuthorDataModel {
    private int AuthorID;
    private String AuthorName;
    private String AboutAuthor;
    private String LargeImageURL;
    private String ImageURL;
    private String ThumbnailURL;
    private int WorksCount;
    private String Gender;
    private String BornAt;
    private String DiedAt;

    public AuthorDataModel(int authorID, String authorName, String aboutAuthor, String largeImageURL, String imageURL, String thumbnailURL, int worksCount, String gender, String bornAt, String diedAt) {
        AuthorID = authorID;
        AuthorName = authorName;
        AboutAuthor = aboutAuthor;
        LargeImageURL = largeImageURL;
        ImageURL = imageURL;
        ThumbnailURL = thumbnailURL;
        WorksCount = worksCount;
        Gender = gender;
        BornAt = bornAt;
        DiedAt = diedAt;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(int authorID) {
        AuthorID = authorID;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getAboutAuthor() {
        return AboutAuthor;
    }

    public void setAboutAuthor(String aboutAuthor) {
        AboutAuthor = aboutAuthor;
    }

    public String getLargeImageURL() {
        return LargeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        LargeImageURL = largeImageURL;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getThumbnailURL() {
        return ThumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        ThumbnailURL = thumbnailURL;
    }

    public int getWorksCount() {
        return WorksCount;
    }

    public void setWorksCount(int worksCount) {
        WorksCount = worksCount;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBornAt() {
        return BornAt;
    }

    public void setBornAt(String bornAt) {
        BornAt = bornAt;
    }

    public String getDiedAt() {
        return DiedAt;
    }

    public void setDiedAt(String diedAt) {
        DiedAt = diedAt;
    }
}
