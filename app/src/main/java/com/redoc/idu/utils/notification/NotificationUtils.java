package com.redoc.idu.utils.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.NotificationCompat;

import com.redoc.idu.IDuApplication;
import com.redoc.idu.R;

/**
 * All kinds of notifications.
 * I just copy part of the code from document: http://blog.csdn.net/w804518214/article/details/51231946
 * for more information, refer to that document.
 * Created by Mengliang Li on 2/2/2017.
 */

public class NotificationUtils{
    public static final int TYPE_Normal = 1;
    public static final int TYPE_Progress = 2;
    public static final int TYPE_BigText = 3;
    public static final int TYPE_Inbox = 4;
    public static final int TYPE_BigPicture = 5;
    public static final int TYPE_Hangup = 6;
    public static final int TYPE_Media = 7;
    public static final int TYPE_Customer = 8;

    public static void sendSimpleNotification(Context context,Intent intent, int iconResourceId, String notificationDigest,
                                              String title, String firstLineContent, String secondLineContent) {
        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        //Ticker是状态栏显示的提示
        builder.setTicker(notificationDigest);
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle(title);
        //第二行内容 通常是通知正文
        builder.setContentText(firstLineContent);
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        builder.setSubText(secondLineContent);
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
        //builder.setContentInfo("2");
        //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
        // builder.setNumber(2);
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true);
        //系统状态栏显示的小图标
        builder.setSmallIcon(iconResourceId);
        //下拉显示的大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),iconResourceId));
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) IDuApplication.Context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(TYPE_Normal,notification);

    }

    public static void sendProgressNotification(Context context, Intent intent, int iconResourceId, int progress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(iconResourceId);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), iconResourceId));
        //禁止用户点击删除按钮删除
        builder.setAutoCancel(false);
        //禁止滑动删除
        builder.setOngoing(true);
        //取消右上角的时间显示
        builder.setShowWhen(false);
        builder.setContentTitle("下载中..."+progress+"%");
        builder.setProgress(100,progress,false);
        //builder.setContentInfo(progress+"%");
        builder.setOngoing(true);
        builder.setShowWhen(false);
        intent.putExtra("command",1);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) IDuApplication.Context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(TYPE_Progress,notification);
    }

    /**
     * 注意事项
     * 1. 使用类 Android.support.v4.app.NotificationCompat.BigTextStyle
     * 2. 在低版本系统上只显示点击前的普通通知样式 如4.4可以点击展开，在4.0系统上就不行
     * 3. 点击前后的ContentTitle、ContentText可以不一致，bigText内容可以自动换行 好像最多5行的样子
     * @param context
     * @param intent
     * @param iconResourceId
     */
    public static void sendBigTextNotification(Context context, Intent intent, int iconResourceId){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("BigTextStyle");
        builder.setContentText("BigTextStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), iconResourceId));
        android.support.v4.app.NotificationCompat.BigTextStyle style = new android.support.v4.app.NotificationCompat.BigTextStyle();
        style.bigText("这里是点击通知后要显示的正文，可以换行可以显示很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
        style.setBigContentTitle("点击后的标题");
        //SummaryText没什么用 可以不设置
        style.setSummaryText("末尾只一行的文字内容");
        builder.setStyle(style);
        builder.setAutoCancel(true);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) IDuApplication.Context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(TYPE_BigText,notification);
    }


    /**
     * 1. 使用类android.support.v4.app.NotificationCompat.BigPictureStyle
     * 2. style.bigPicture传递的是个bitmap对象 所以也不应该传过大的图 否则会oom
     * 3. 同BigTextStyle 低版本上系统只能显示普通样式
     * @param context
     * @param intent
     * @param iconResourceId
     * @param imageResourceId
     */
    public void bigPictureStyle(Context context, Intent intent, int iconResourceId, int imageResourceId){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("BigPictureStyle");
        builder.setContentText("BigPicture演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),iconResourceId));
        android.support.v4.app.NotificationCompat.BigPictureStyle style = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("BigContentTitle");
        style.setSummaryText("SummaryText");
        style.bigPicture(BitmapFactory.decodeResource(context.getResources(),imageResourceId));
        builder.setStyle(style);
        builder.setAutoCancel(true);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        //设置点击大图后跳转
        builder.setContentIntent(pIntent);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) IDuApplication.Context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(TYPE_BigPicture,notification);
    }

    /**
     * 类似于手机QQ消息的通知，不显示在通知栏而是以横幅的模式显示在其他应用上方
     * 注意事项
     * 1. 此种效果只在5.0以上系统中有效
     * 2. mainfest中需要添加<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
     * 3. 可能还需要在设置开启横幅通知权限（在设置通知管理中）
     * 4. 在部分改版rom上可能会直接弹出应用而不是显示横幅
     * @param context
     * @param intent
     * @param iconResourceId
     */
    public void sendHangUpNotification(Context context, Intent intent, int iconResourceId) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("横幅通知");
        builder.setContentText("请在设置通知管理中开启消息横幅提醒权限");
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),iconResourceId));
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent);
        //这句是重点
        builder.setFullScreenIntent(pIntent,true);
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) IDuApplication.Context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(TYPE_Hangup,notification);
    }

    /**
     * 注意事项
     * 1. 使用类v7包下的NotificationCompat.MediaStyle
     * 2. addAction方法并普通样式也可以用，使用后普通通知可以点击展开，展开部分会显示一排添加的图标，并且可以给每个图标设置不同的点击事件
     * 3. 最多可以添加5哥action 并排显示在点击展开的部分
     * 4. setShowActionsInCompactView的参数是添加的action在所有action组成的数组中的下标，从0开始
     * 5. setShowActionsInCompactView设置的action会显示在点击前的通知的右侧，低版本上也可以显示
     * 6. setShowCancelButton(true)会在通知的右上部分显示一个删除图标 5.0以下有效
     * @param context
     * @param intent
     * @param iconResourceId
     * @param previousIcon
     * @param stopIcon
     * @param playIcon
     * @param nextIcon
     */
    private void mediaStyle(Context context, Intent intent, int iconResourceId, int previousIcon, int stopIcon,
                            int playIcon, int nextIcon){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("MediaStyle");
        builder.setContentText("Song Title");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),iconResourceId));
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent);
        //第一个参数是图标资源id 第二个是图标显示的名称，第三个图标点击要启动的PendingIntent
        builder.addAction(previousIcon,"",null);
        builder.addAction(stopIcon,"",null);
        builder.addAction(playIcon,"",pIntent);
        builder.addAction(nextIcon,"",null);
        NotificationCompat.MediaStyle style = new NotificationCompat.MediaStyle();
        style.setMediaSession(new MediaSessionCompat(context,"MediaSession",
                new ComponentName(context,Intent.ACTION_MEDIA_BUTTON),null).getSessionToken());
        //CancelButton在5.0以下的机器有效
        style.setCancelButtonIntent(pIntent);
        style.setShowCancelButton(true);
        //设置要现实在通知右方的图标 最多三个
        style.setShowActionsInCompactView(2,3);
        builder.setStyle(style);
        builder.setShowWhen(false);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) IDuApplication.Context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(TYPE_Media,notification);
    }
}
