package com.redoc.idu.model.bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mengliang Li on 12/31/2016.
 */

public class TextArticle {
    private String mTitle;
    private String mSource;
    private String mTime;
    private List<Paragraph> mParagraphs;

    public TextArticle(List<Paragraph> paragraphs) {
        mParagraphs = paragraphs;
    }


    public class Paragraph {
        private List<String> mImageUrls;
        private String mArticle;

        public List<String> getImageUrls() {
            return mImageUrls;
        }

        public String getArticle() {
            return mArticle;
        }

        public Paragraph(List<String> imageUrls, String article) {
            mImageUrls = imageUrls;
            mArticle = article;
        }

    }
}
