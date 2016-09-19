package com.redoc.idu.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.redoc.idu.model.bean.Category;
import com.redoc.idu.model.bean.Channel;
import com.redoc.idu.model.dao.CategoryDao;
import com.redoc.idu.model.dao.ChannelDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Categories provider.
 * Created by limen on 2016/9/4.
 */
public class CategoryAndChannelManager {
    /**
     * Context.
     */
    private Context mContext;

    /**
     * Database manager.
     */
    private DatabaseManager mDatabaseManager;

    /**
     *  Categories cache.
     */
    private List<Category> mCategories ;

    /**
     * Channels cache.
     */
    private List<Channel> mChannels;

    /**
     * Construct a {@link CategoryAndChannelManager} instance.
     * @param context The context.
     */
    public CategoryAndChannelManager(Context context) {
        mContext = context;
        mDatabaseManager = new DatabaseManager();
    }

    /**
     * Initialize.
     */
    public void initialize() {
        mDatabaseManager.setupDatabase(mContext);
    }

    /**
     * Get categories from database.
     * @return All categories.
     */
    public List<Category> getCategories() {
        if(mCategories == null) {
            mCategories = new ArrayList<>();
            CategoryDao categoryDao = mDatabaseManager.getCategoryDao();
            SQLiteDatabase database = mDatabaseManager.getDatabase();
            String categoryId = CategoryDao.Properties.Id.columnName;
            String orderBy = categoryId + " COLLATE LOCALIZED ASC";
            Cursor cursor = database.query(categoryDao.getTablename(), categoryDao.getAllColumns(), null, null, null, null, orderBy);
            while(cursor.moveToNext()) {
                mCategories.add(categoryDao.readEntity(cursor, 0));
            }
        }
        return mCategories;
    }

    /**
     * Get channels from database.
     * @return All channels.
     */
    public List<Channel> getChannels() {
        if(mChannels == null) {
            mChannels = new ArrayList<>();
            ChannelDao channelDao = mDatabaseManager.getChannelDao();
            SQLiteDatabase database = mDatabaseManager.getDatabase();
            String channelId = ChannelDao.Properties.Id.columnName;
            String orderBy = channelId + " COLLATE LOCALIZED ASC";
            Cursor cursor = database.query(channelDao.getTablename(), channelDao.getAllColumns(), null, null, null, null, orderBy);
            while(cursor.moveToNext()) {
                mChannels.add(channelDao.readEntity(cursor, 0));
            }
        }
        return mChannels;
    }

    /**
     * Get a channel by id.
     * @param channelId Channel id.
     * @return Channel with the id.
     */
    public Channel getChannelById(String channelId) {
        for (Channel channel : getChannels()) {
            if(channel.getCATEGORY_ID() == channelId) {
                return channel;
            }
        }
        return null;
    }

    /**
     * Follow a channel
     * @param channel The channel to follow.
     */
    public void followAChannel(Channel channel) {
        ChannelDao channelDao = mDatabaseManager.getChannelDao();
        channel.setFOLLOWED(true);
        channelDao.update(channel);
    }

    /**
     * Unfollow a channel
     * @param channel The channel to unfollow.
     */
    public void unfollowAChannel(Channel channel) {
        ChannelDao channelDao = mDatabaseManager.getChannelDao();
        channel.setFOLLOWED(false);
        channelDao.update(channel);
    }
}
