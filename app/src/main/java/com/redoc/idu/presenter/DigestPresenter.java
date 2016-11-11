package com.redoc.idu.presenter;

import com.redoc.idu.IView;
import com.redoc.idu.contract.IDigest;

/**
 * A digest presenter.
 * Created by limen on 2016/10/25.
 */
// TODO: Need to transfer to abstract
public class DigestPresenter implements IDigest.IDigestPresenter {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return "124";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDigest() {
        return "456";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttached(IView view) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDetached() {

    }
}
