package com.redoc.idu.daogenerator;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.DaoGenerator;

public class IDuDaoGenerator {
    public static void main(String[] args)  throws Exception {
        Schema schema = new Schema(1, "com.redoc.idu.model.bean");
        schema.setDefaultJavaPackageDao("com.redoc.idu.model.dao");
        addCategoryEntity(schema);
        addChannelEntity(schema);
        new DaoGenerator().generateAll(schema, "app\\src\\main\\java-gen");
    }

    private static void addCategoryEntity(Schema schema) {
        Entity note = schema.addEntity("Category");
        note.addIdProperty();
        note.addStringProperty("CATEGORY_NAME").notNull();
    }

    private static void addChannelEntity(Schema schema) {
        Entity channel = schema.addEntity("Channel");
        channel.addIdProperty();
        channel.addStringProperty("CHANNEL_NAME");
        channel.addStringProperty("CATEGORY_ID");
        channel.addBooleanProperty("CAN_CACHE");
        channel.addBooleanProperty("FOLLOWED");
        channel.addStringProperty("LINK");
        channel.addIntProperty("WEIGHT");
    }
}
