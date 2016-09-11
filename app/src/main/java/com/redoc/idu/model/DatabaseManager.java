package com.redoc.idu.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.redoc.idu.model.bean.Category;
import com.redoc.idu.model.bean.Channel;
import com.redoc.idu.model.dao.CategoryDao;
import com.redoc.idu.model.dao.ChannelDao;
import com.redoc.idu.model.dao.DaoMaster;
import com.redoc.idu.model.dao.DaoSession;

/**
 * Manage database.
 * Created by limen on 2016/9/4.
 */
public class DatabaseManager {
    private SQLiteDatabase mDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private CategoryDao mCategoryDao;
    private ChannelDao mChannelDao;
    public void setupDatabase(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "database", null);
        mDatabase = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDatabase);
        mDaoSession = mDaoMaster.newSession();
        mCategoryDao = mDaoSession.getCategoryDao();
        mChannelDao = mDaoSession.getChannelDao();
        // addCategories();
        // addChannels();
    }

    /**
     * Add categories.
     */
    private void addCategories() {
        mCategoryDao.insert(new Category(1L, "首页"));
        mCategoryDao.insert(new Category(2L, "电台"));
    }

    /**
     * Add channels.
     */
    private void addChannels() {
        mChannelDao.insert(new Channel(1L, "头条", "1", true, true, "http://c.m.163.com/nc/article/headline/T1348647909107/%d-20.html", 1));
        mChannelDao.insert(new Channel(2L, "娱乐", "1", true, false, "http://c.m.163.com/nc/article/list/T1348648517839/%d-20.html", 2));
        mChannelDao.insert(new Channel(3L, "社会", "1", true, false, "http://c.m.163.com/nc/article/list/T1349837698345/%d-20.html", 3));
        mChannelDao.insert(new Channel(4L, "财经", "1", true, false, "http://c.m.163.com/nc/article/list/T1348648756099/%d-20.html", 4));
        mChannelDao.insert(new Channel(5L, "科技", "1", true, false, "http://c.m.163.com/nc/article/list/T1348649580692/%d-20.html", 5));
        mChannelDao.insert(new Channel(6L, "汽车", "1", true, false, "http://c.m.163.com/nc/article/list/T1348654060988/%d-20.html", 6));
        mChannelDao.insert(new Channel(7L, "手机", "1", true, false, "http://c.m.163.com/nc/article/list/T1348649654285/%d-20.html", 7));
        mChannelDao.insert(new Channel(101L, "电台", "2", false, true, "http://c.m.163.com/nc/article/list/T1379038288239/%d-20.html", 1));
    }

    /**
     * Get category dao.
     * @return Category dao.
     */
    public CategoryDao getCategoryDao() {
        return mCategoryDao;
    }

    /**
     * Get channel dao.
     * @return ChannelDao
     */
    public ChannelDao getChannelDao() {
        return mChannelDao;
    }

    /**
     * Get database.
     * @return Database.
     */
    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }
}
