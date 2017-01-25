package com.redoc.idu.framework.contract;

import com.redoc.idu.IPresenter;
import com.redoc.idu.IView;

/**
 * Digest contract.
 * Created by limen on 2016/10/25.
 */
public interface IDigest {
    /**
     * Digest view.
     */
    interface IDigestView extends IView<IDigestPresenter> {
        void loadDigestImages();
    }

    /**
     * Digest presenter..
     */
    interface IDigestPresenter extends IPresenter {
        /**
         * Get title.
         * @return Title of the digest.
         */
        String getTitle();

        /**
         * Get digest.
         * @return The digest content.
         */
        String getDigest();

        /**
         * Get source.
         * @return Source of the digest.
         */
        String getSource();

        /**
         * Get url of article.
         * @return Url of article.
         */
        String getArticleUrl();

        /**
         * Load images.
         */
        void loadImages();
    }
}
