package com.redoc.idu.framework.presenter.digest;

import com.redoc.idu.framework.contract.IDigest;

import java.util.List;

/**
 * Created by Mengliang Li on 1/25/2017.
 */

public abstract class DigestsWithImagePresenter implements IDigest.IDigestPresenter {
    public List<String> getDigestImageSources() {
        return null;
    }
}
