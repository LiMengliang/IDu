package com.redoc.idu.view.multiChannel;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.redoc.idu.R;
import com.redoc.idu.contract.multichannel.IMultiChannelContract;

/**
 * Channel item view in MultiChannelsManagerView
 * Created by limen on 2016/9/17.
 */
public class MultiChannelItemView extends TextView implements IMultiChannelContract.IMultiChannelItemView {

    /**
     * The channel presenter.
     */
    private IMultiChannelContract.IMultiChannelPresenter mChannelPresenter;

    /**
     * Construct a ChannelItemView instance.
     * @param context The context.
     * @param attrs The attributes.
     */
    public MultiChannelItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Construt a ChannelItemView instance.
     * @param context The context.
     */
    public MultiChannelItemView(Context context) {
        super(context);
        setBackground(context.getResources().getDrawable(R.drawable.channel_item_background));
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setPadding(30, 15, 30, 15);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams (
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(40, 15, 40, 15);
        setLayoutParams(lp);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mChannelPresenter.isFollowed()) {
                    mChannelPresenter.unfollow();
                }
                else {
                    mChannelPresenter.follow();
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPresenter(IMultiChannelContract.IMultiChannelPresenter multiChannelPresenter) {
        mChannelPresenter = multiChannelPresenter;
        mChannelPresenter.onAttached(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChannelItemStyle(boolean followed) {
        if(followed) {
            setTextColor(Color.DKGRAY);
        }
        else{
            setTextColor(Color.LTGRAY);
        }
    }
}
