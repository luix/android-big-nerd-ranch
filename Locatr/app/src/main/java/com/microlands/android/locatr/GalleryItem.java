package com.microlands.android.locatr;

import android.net.Uri;

/**
 * Created by luisvivero on 7/3/16.
 */

public class GalleryItem {
    private static final String PHOTOS_END_POINT = "http://www.flickr.com/photos/";

    private String mCaption;
    private String mId;
    private String mUrl;
    private String mOwner;

    @Override
    public String toString() {
        return mCaption;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getOwner() {
        return mOwner;
    }

    public void setOwner(String owner) {
        mOwner = owner;
    }

    public Uri getPhotoPageUri() {
        return Uri.parse(PHOTOS_END_POINT)
                .buildUpon()
                .appendPath(mOwner)
                .appendPath(mId)
                .build();
    }
}
