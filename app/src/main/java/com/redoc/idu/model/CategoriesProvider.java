package com.redoc.idu.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.redoc.idu.IDuApplication;
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
public class CategoriesProvider {
    private static List<Category> mCategories ;

    /**
     * Get categories from database.
     * @return All categories.
     */
    public static List<Category> getCategories() {
        if(mCategories == null) {
            mCategories = new ArrayList<>();
            CategoryDao categoryDao = IDuApplication.DatabaseManager.getCategoryDao();
            SQLiteDatabase database = IDuApplication.DatabaseManager.getDatabase();
            String categoryId = CategoryDao.Properties.Id.columnName;
            String orderBy = categoryId + " COLLATE LOCALIZED ASC";
            Cursor cursor = database.query(categoryDao.getTablename(), categoryDao.getAllColumns(), null, null, null, null, orderBy);
            while(cursor.moveToNext()) {
                mCategories.add(categoryDao.readEntity(cursor, 0));
            }
        }
        return mCategories;
    }

    private static List<Channel> mChannels;

    /**
     * Get channels from database.
     * @return All channels.
     */
    public static List<Channel> getChannels() {
        if(mChannels == null) {
            mChannels = new ArrayList<>();
            ChannelDao channelDao = IDuApplication.DatabaseManager.getChannelDao();
            SQLiteDatabase database = IDuApplication.DatabaseManager.getDatabase();
            String channelId = ChannelDao.Properties.Id.columnName;
            String orderBy = channelId + " COLLATE LOCALIZED ASC";
            Cursor cursor = database.query(channelDao.getTablename(), channelDao.getAllColumns(), null, null, null, null, orderBy);
            while(cursor.moveToNext()) {
                mChannels.add(channelDao.readEntity(cursor, 0));
            }
        }
        return mChannels;
    }
}
