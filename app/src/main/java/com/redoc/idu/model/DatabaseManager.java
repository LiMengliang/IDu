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
class DatabaseManager {
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
        // News
        mChannelDao.insert(new Channel(1L, "头条", "1", true, true, "http://c.m.163.com/nc/article/headline/T1348647909107/%d-20.html", 1));
        mChannelDao.insert(new Channel(2L, "娱乐", "1", true, true, "http://c.m.163.com/nc/article/list/T1348648517839/%d-20.html", 2));
        mChannelDao.insert(new Channel(3L, "社会", "1", true, true, "http://c.m.163.com/nc/article/list/T1349837698345/%d-20.html", 3));
        mChannelDao.insert(new Channel(4L, "财经", "1", true, true, "http://c.m.163.com/nc/article/list/T1348648756099/%d-20.html", 4));
        mChannelDao.insert(new Channel(5L, "科技", "1", true, true, "http://c.m.163.com/nc/article/list/T1348649580692/%d-20.html", 5));
        mChannelDao.insert(new Channel(6L, "汽车", "1", true, true, "http://c.m.163.com/nc/article/list/T1348654060988/%d-20.html", 6));
        mChannelDao.insert(new Channel(7L, "手机", "1", true, false, "http://c.m.163.com/nc/article/list/T1348649654285/%d-20.html", 7));
        mChannelDao.insert(new Channel(8L, "情感", "1", true, false, "http://c.m.163.com/nc/article/list/T1348650839000/%d-20.html", 8));
        mChannelDao.insert(new Channel(9L, "电影", "1", true, false, "http://c.m.163.com/nc/article/list/T1348648650048/%d-20.html", 9));
        mChannelDao.insert(new Channel(10L, "NBA", "1", true, false, "http://c.m.163.com/nc/article/list/T1348649145984/%d-20.html", 10));
        mChannelDao.insert(new Channel(11L, "数码", "1", true, false, "http://c.m.163.com/nc/article/list/T1348649776727/%d-20.html", 11));
        mChannelDao.insert(new Channel(12L, "移动", "1", true, false, "http://c.m.163.com/nc/article/list/T1351233117091/%d-20.html", 12));
        mChannelDao.insert(new Channel(13L, "房产", "1", true, false, "http://c.m.163.com/nc/article/list/T1348648517839/%d-20.html", 13));
        mChannelDao.insert(new Channel(14L, "CBA", "1", true, false, "http://c.m.163.com/nc/article/list/T1348648517839/%d-20.html", 14));
        mChannelDao.insert(new Channel(15L, "笑话", "1", true, false, "http://c.m.163.com/nc/article/list/T1350383429665/%d-20.html", 15));
        mChannelDao.insert(new Channel(16L, "时尚", "1", true, false, "http://c.m.163.com/nc/article/list/T1348650593803/%d-20.html", 16));
        mChannelDao.insert(new Channel(17L, "军事", "1", true, false, "http://c.m.163.com/nc/article/list/T1348648517839/%d-20.html", 17));
        mChannelDao.insert(new Channel(18L, "游戏", "1", true, false, "http://c.m.163.com/nc/article/list/T1348654151579/%d-20.html", 18));
        mChannelDao.insert(new Channel(19L, "精选", "1", true, false, "http://c.m.163.com/nc/article/list/T1370583240249/%d-20.html", 19));
        mChannelDao.insert(new Channel(20L, "教育", "1", true, false, "http://c.m.163.com/nc/article/list/T1348654225495/%d-20.html", 20));
        mChannelDao.insert(new Channel(21L, "论坛", "1", true, false, "http://c.m.163.com/nc/article/list/T1349837670307/%d-20.html", 21));
        mChannelDao.insert(new Channel(22L, "旅游", "1", true, false, "http://c.m.163.com/nc/article/list/T1348654204705/%d-20.html", 22));

        // Radio
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
