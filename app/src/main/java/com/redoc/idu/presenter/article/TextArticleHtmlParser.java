package com.redoc.idu.presenter.article;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.redoc.idu.IDuApplication;
import com.redoc.idu.contract.article.IArticleContract;
import com.redoc.idu.utils.html.JsoupParser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Text article html parser.
 * Created by Mengliang Li on 12/31/2016.
 */

public class TextArticleHtmlParser {

    private final String ContentName = "content";
    private final String TitleClassName = "atitle tCenter";
    private final String AboutClassName = "about";
    private final String ImageTagName = "img";
    private final String ImageSourceAttributeName = "src";
    private final String AnchorTagName = "a";
    private final String ParagraphTagName = "p";
    private final String ImageAddressSource = "http://s.cimg.163.com/i/";
    private final List<String> ImageExtensions = new ArrayList<String>()
    {
        {
            add(".jpeg.");
            add(".jpg.");
            add(".png.");
            add(".bmp.");
        }
    };

    /**
     * Article html template.
     */
    private final String ArticleTemplate = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "\t<head>\n" +
            "\t\t<style>\n" +
            "\t\t\timg{ max-width:100%; height:auto;}\n" +
            "\t\t\t.source {color:#999999; font-size:12px; text-align:left}\n" +
            "\t\t\t.title{ text-align:left; font-size:20px; font-weight:bold; padding:0px 3px 20px 0px; }\n" +
            "\t\t\tp {line-height:150%; padding:20px 0px 0px 0px;}\n" +
            "\t\t\t.content{color:#666666; font-size:15px; margin:3px; }\n" +
            "\t\t\tdiv.wrapper{\n" +
            "\t\t\tfloat:left; /* important */\n" +
            "\t\t\tposition:relative; /* important(so we can absolutely position the description div */ \n" +
            "}\n" +
            "\t\t\tdiv.description{position:absolute; bottom:0px; left:0px; width:100%; background-color:black; font-size:15px; opacity:0.6; filter:alpha(opacity=60);}\n" +
            "p.description_content{text-align:right; color:white;}\n"+
            "\t\t</style>\n" +
            "\t\t<meta http-equiv=\"Content-Type\" content=\"application/vnd.wap.xhtml+xml;charset=utf-8\"/>\n" +
            "\t\t<meta http-equiv=\"Cache-Control\" content=\"no-transform\"/>\n" +
            "\t</head>\n" +
            "\t<body>\n" +
            "\t\t<table style=\"padding:10px 0px 0px 0px; border-color:#0066cc;border-left-style:solid;border-width:5px\"><tr><td valign=\"top\">" +
            "\t\t\t<div class=\"title\"><!--TITLE--></div>" +
            "\t\t\t<div class=\"source\"><!--TIME_SOURCE--></div>\n" +
            "\t\t</td></tr>" +
            "\t\t</table>\n" +
            "\t\t<!--CONTENT-->\n" +
            "\t</body>\n" +
            "</html>";

    /**
     * Node template of image link to a video
     */
    private final String VideoImageNode =
            "<div class=\"wrapper\"><a href=\"<!--VIDEO_SOURCE-->\"><img src=\"<!--IMAGE_SOURCE-->\"/></a>" +
            "\t\t<div class='description'>\n" +
            "\t\t\t<a href=\"<!--VIDEO_SOURCE-->\"><p class='description_content'>播放视频▶  </p></a>\n" +
            "\t\t</div>\n";

    private JsoupParser mJsoupParser;
    private IArticleContract.IArticlePresenter mArticlePresenter;

    /**
     * Parse article html.
     * @param url The url of article.
     * @param articlePresenter Article presenter.
     */
    public void parse(String url, IArticleContract.IArticlePresenter articlePresenter) {
        mArticlePresenter = articlePresenter;
        IDuApplication.HttpClient.addStringRequest(url, new RawUrlResponseListener(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    /**
     * A response listener to raw html.
     */
    class RawUrlResponseListener implements Response.Listener<String> {

        /**
         * {@inheritDoc /}
         */
        @Override
        public void onResponse(String content) {
            // content = "\n" +
            //         "<!DOCTYPE html>\n" +
            //         "<html>\n" +
            //         "<head>\n" +
            //         "<meta http-equiv=\"Content-Type\" content=\"application/vnd.wap.xhtml+xml;charset=utf-8\"/>\n" +
            //         "<meta http-equiv=\"Cache-Control\" content=\"no-transform\"/>\n" +
            //         "<title>广东陆丰凌晨多人枪战6人受伤 街坊冒险拍下视频_手机网易网</title>\n" +
            //         "<meta name=\"keywords\" content=\"广东陆丰,债务纠纷,街头,斗殴,枪战\" />\n" +
            //         "<meta name=\"description\" content=\"　　南都讯 记者周松柏 昨日凌晨1时左右，汕尾陆丰市发生枪战，两伙人因债务纠纷，在该市东海镇一热闹街道上互相开枪火并，震惊当地，枪案造成6人不同程度受伤。\" />\n" +
            //         "<meta name=\"author\" content=\"网易\" />\n" +
            //         "<script>\n" +
            //         "function testTouchJump(){\n" +
            //         "    if( window && window.localStorage && navigator && navigator.userAgent ) {\n" +
            //         "        if( navigator.userAgent.match(/mobile/i) || navigator.userAgent.match(/ScoreSportWeb/i) ) {\n" +
            //         "            return true;\n" +
            //         "        }\n" +
            //         "    }\n" +
            //         "}\n" +
            //         ";(function(){\n" +
            //         "\tif(testTouchJump()){\n" +
            //         "\t    var docid = window.location.href.match(/\\/([0-9A-Z]{16})/)[1];\n" +
            //         "\t    var stat_str = \"\";\n" +
            //         "\t    if(document.referrer){\n" +
            //         "\t        stat_str = \"&from=x&referrerdomain=\" + document.referrer.replace(/^.*\\/\\/([^\\/]*)\\/.*/g,\"$1\");\n" +
            //         "\t    }else{\n" +
            //         "\t        stat_str = \"&from=x\";\n" +
            //         "\t    }\n" +
            //         "\t    window.location.href = 'http://3g.163.com/touch/article.html?docid=' + docid + stat_str;\n" +
            //         "\t}\t\n" +
            //         "})();\n" +
            //         "</script>\n" +
            //         "<style type=\"text/css\">\n" +
            //         "body{margin:2px 6px;padding:0;line-height:150%;background:#f8fcff}\n" +
            //         "a{color:#00f;text-decoration:underline}\n" +
            //         "a:visited{color:#83006f;text-decoration:underline}\n" +
            //         "a:hover{color:#c00;}\n" +
            //         ".cBlue,a.cBlue,.cBlue:visited,.cBlue a{color:#00f}\n" +
            //         ".cRed,a.cRed,.cRed:visited,.cRed a{color:#f00}\n" +
            //         ".cGray,a.cGray,.cGray:visited,.cGray a{color:#444}\n" +
            //         ".lBlue a,.lBlue a:visited{color:#00f}\n" +
            //         ".cBlue a:hover,a.cBlue:hover,.lBlue a:hover,.cGray:hover,.cGray a:hover{color:#c00}\n" +
            //         ".cRed a:hover,a.cRed:hover{color:#000}\n" +
            //         ".tCenter{text-align:center}\n" +
            //         ".tRight{text-align:right}\n" +
            //         ".reset{margin:0;padding:0;list-style:none}\n" +
            //         ".marTop6{margin-top:6px}\n" +
            //         ".marTop10{margin-top:10px}\n" +
            //         ".marTop12{margin-top:12px}\n" +
            //         ".title{background:#384ea3;color:#fff;padding:3px 6px}\n" +
            //         ".atitle{background:#bfd7fb;padding:3px 0;font-size: 16px;}\n" +
            //         ".comment p{margin:0;padding:3px 0 0;clear:both}\n" +
            //         ".comment .review{border-bottom:1px dashed #999;padding-bottom:6px}\n" +
            //         ".comment .review .data{width:100%;font-size:12px}\n" +
            //         ".comment .review .data .name{float:left;color:#1f3a87}\n" +
            //         ".comment .review .data .time{float:right;color:#666}\n" +
            //         ".comment .commentBox{clear:both;background:#ffe; border:1px solid #999; padding:2px}\n" +
            //         ".comment .commentBox .forCite{ font-size:12px; display:block; padding-top:6px; line-height:120%; color:#1f3a87}\n" +
            //         ".comment .commentBox .forCite .pic{ float:left; margin:2px 2px 0 1px;}\n" +
            //         ".comment .review .function{width:100%;text-align:right; font-size:12px;}.comment .commentBox2{clear:both;background:#ffe; margin-top:3px; border-right:1px solid #999; border-bottom:1px solid #999; border-left:1px solid #999; padding:0 2px 2px}\n" +
            //         ".backTop{text-align:center;margin-bottom:6px;}\n" +
            //         "img { border:none; }\n" +
            //         "</style>\n" +
            //         "<style type=\"text/css\">\n" +
            //         "body{ line-height:140%;}\n" +
            //         ".content p{ margin:7px 0;}\n" +
            //         ".cGreen,.cGreen:visited,.cGreen a{color:#008000;}\n" +
            //         ".cBlack,.cBlack:visited,.cBlack a{color:#000000;}\n" +
            //         ".iframe-ad{display:block;height:21px;}\n" +
            //         ".banner-imglist {width: 100%;}\n" +
            //         ".banner-imglist img{margin:10px 5px 0 0;}\n" +
            //         ".imgArea {margin: 0 5px 3px;display: inline;padding: 1px;float: left;}\n" +
            //         ".imgArea img, .imgArea3 img {display: block;}\n" +
            //         ".imgArea p, .imgArea3 p {background: #ebf3fb;margin:0;line-height: 20px;font-style: normal;padding-left: 4px;border: 1px solid #cde0f6;border-top: none;}\n" +
            //         "</style>\n" +
            //         "</head>\n" +
            //         "<body>\n" +
            //         "<div class=\"position lBlue\"><a name=\"top\" id=\"top\"></a><a href=\"http://3g.163.com/x/\">网易</a>&gt;<a href=\"http://news.163.com/\">新闻</a>&gt;正文</div>\n" +
            //         "<!-- bannerTop Ad -->\n" +
            //         "<!-- bannerTop Ad end -->   \n" +
            //         "<!-- 2017-01-06 20:40:08 -->\n" +
            //         "<h1 class=\"atitle tCenter\">广东陆丰凌晨多人枪战6人受伤 街坊冒险拍下视频</h1>\n" +
            //         "<div class=\"about\">\n" +
            //         "        2017-01-06 11:33:10<br/>\n" +
            //         "            来源:《南方都市报》<br/>\n" +
            //         "        <a href=\"http://comment.3g.163.com/news2_bbs/CA3I1CHS00018AOR.html\" class=\"cBlue\">网友评论0条</a><br/>\n" +
            //         "                </div>\n" +
            //         "<!-- 推广位01 -->\n" +
            //         "<a href=\"http://3g.163.com/links/3034\">*新闻客户端提供本地城市新闻</a>\n" +
            //         "\n" +
            //         "<div><img src=\"http://analytics.163.com/ntes?_ntit=3g_articles_x&amp;_nacc=wap&amp;_nvid=VISITOR_CLIENT_NO_COOKIE_SUPPORT&amp;_t=204008&amp;_nurl=http%3A//3g.163.com/x/news/17/0106/11/CA3I1CHS00018AOR_0.html&amp;_end\" width=\"6\" height=\"1\" alt=\"\" /></div>\n" +
            //         "<div class=\"content\">\n" +
            //         "    <p><!--@@VIDEO=\"https://flv.bn.netease.com/videolib3/1701/06/zWAkV4993/SD/zWAkV4993-mobile.mp4,https://flv.bn.netease.com/videolib3/1701/06/zWAkV4993/SD/zWAkV4993-mobile.mp4\" IMG=\"http://vimg1.ws.126.net/image/snapshot/2017/1/P/C/VC95PICPC.jpg\" ALT=\"广东省陆丰市东海镇人民广场发生枪战\" BROADCAST=\"in\" SIZE=\"\" TOPICID=\"1000\" COMMENTID=\"C95PEQH5008535RB\" COMMENTBOARD=\"video_bbs\" VID=\"VC95PEQH5\"--></p><p><!--@@VIDEO=\"https://flv.bn.netease.com/videolib3/1701/06/xmjRz4669/SD/xmjRz4669-mobile.mp4,https://flv.bn.netease.com/videolib3/1701/06/xmjRz4669/SD/xmjRz4669-mobile.mp4\" IMG=\"http://vimg2.ws.126.net/image/snapshot/2017/1/1/S/VC95PKP1S.jpg\" ALT=\"广东陆丰当街发生枪战，堪比美国大片\" BROADCAST=\"in\" SIZE=\"\" TOPICID=\"1000\" COMMENTID=\"C95P4TRQ008535RB\" COMMENTBOARD=\"video_bbs\" VID=\"VC95P4TRQ\"--></p><p><!--@@VIDEO=\"https://flv.bn.netease.com/videolib3/1701/06/RcyPu6658/SD/RcyPu6658-mobile.mp4,https://flv.bn.netease.com/videolib3/1701/06/RcyPu6658/SD/RcyPu6658-mobile.mp4\" IMG=\"http://vimg1.ws.126.net/image/snapshot/2017/1/6/B/VC95R1F6B.jpg\" ALT=\"广东陆丰凌晨现多人枪战，街坊冒险拍下视频\" BROADCAST=\"in\" SIZE=\"\" TOPICID=\"1000\" COMMENTID=\"C95R1F6A008535RB\" COMMENTBOARD=\"video_bbs\" VID=\"VC95R1F6A\"--></p><p><!--@@VIDEO=\"https://flv.bn.netease.com/videolib3/1701/06/Fiqvs6821/SD/Fiqvs6821-mobile.mp4,https://flv.bn.netease.com/videolib3/1701/06/Fiqvs6821/SD/Fiqvs6821-mobile.mp4\" IMG=\"http://vimg1.ws.126.net/image/snapshot/2017/1/M/S/VC95R6EMS.jpg\" ALT=\"广东陆丰当街发生枪战\" BROADCAST=\"in\" SIZE=\"\" TOPICID=\"1000\" COMMENTID=\"C95R6EMR008535RB\" COMMENTBOARD=\"video_bbs\" VID=\"VC95R6EMR\"--></p><p>　　南都讯 记者周松柏 昨日凌晨1时左右，汕尾陆丰市发生枪战，两伙人因债务纠纷，在该市东海镇一热闹街道上互相开枪火并，震惊当地，枪案造成6人不同程度受伤。</p><p>　　<b>街头枪战众街坊害怕躲避</b></p><p>　　枪案具体地点位于东海镇马街，此处属于该镇繁华闹市，虽到凌晨1时许，仍有不少摊档和娱乐场所在经营，有不少街坊用手机拍下了双方冲突枪战的激烈场面。</p><p>　　据当地街坊微信圈流传的多段枪战视频显示，昨日凌晨1时左右，在马街上，两伙人正大打出手，有一名男子倒地疑似已昏迷。随后，另一名男子也被打倒在地。突然，接连几声枪响，再次有人倒地。“两帮人打架，后来有人开枪，不敢再看了，把门关了。”事发地一杂货店老板说，听双方先前吵闹，好像是因欠债起冲突，谈不拢，便当街开战。</p><p>　　“怕，当然怕了。”住在街旁一栋楼里的街坊李先生说，双方打架，互相乱开枪，乱飞的流弹很容易伤到四周无辜的人，他在楼上阳台，枪声突起，也连忙跑进屋内躲避。</p><p>　　<b>警方通报30余人涉案</b></p><p>　　据街坊们称，两伙人现场火并不久后，有警车赶到，这些人做鸟兽散，四处逃离。倒地伤者被后面赶到的救护车送往医院救治。昨日上午，在陆丰市人民医院，几名冲突中受伤较轻的男子在医院病床上接受警方审问。根据该院拍摄的伤者CT片，有伤者大腿中枪。据枪伤特征，疑为霰弹枪击中。</p><p>　　昨晚8时57分，陆丰市公安局对外通报该起事件称，约30名男子因债务纠纷，在东海镇马街上聚众斗殴，双方均有人开枪，造成6人不同程度受伤，在医院治疗，暂无生命危险。陆丰市公安局已抓获7名涉案人员，其他涉案人员在追捕中。</p><p>　　作者：王煜</p>\n" +
            //         "    </div>\n" +
            //         "<!--微博-->\n" +
            //         "<div style=\"margin:2px -2px -10px 0px\">分享到<span><img alt=\"v\" src=\"http://img3.cache.netease.com/3g/transparent.png\"/><a href=\"http://weibo.cn/ext/share?rt=%E5%B9%BF%E4%B8%9C%E9%99%86%E4%B8%B0%E5%87%8C%E6%99%A8%E5%A4%9A%E4%BA%BA%E6%9E%AA%E6%88%986%E4%BA%BA%E5%8F%97%E4%BC%A4+%E8%A1%97%E5%9D%8A%E5%86%92%E9%99%A9%E6%8B%8D%E4%B8%8B%E8%A7%86%E9%A2%91_%E6%89%8B%E6%9C%BA%E7%BD%91%E6%98%93%E7%BD%91&amp;ru=http%3A%2F%2F3g.163.com%2Fnews%2F17%2F0106%2F11%2FCA3I1CHS00018AOR_0.html&amp;st=1483706408081&amp;wm=4326\">新浪微博</a></span><br/></div>\n" +
            //         "<div class=\"comment marTop12\">\n" +
            //         "<a href=\"http://comment.3g.163.com/news2_bbs/CA3I1CHS00018AOR.html\" class=\"cBlue\">网友评论</a>|<a href=\"http://3g.163.com/ntes/special/003403EU/3gcommentrank.html\">评论排行</a>\n" +
            //         "<br/><a href=\"http://comment.3g.163.com/news2_bbs/CA3I1CHS00018AOR.html\" class=\"cBlue\">查看全部评论(0条)</a>\n" +
            //         "</div>\n" +
            //         "<div class=\"position lBlue\"><a href=\"http://3g.163.com/x/\">网易</a>&gt;<a href=\"http://news.163.com/\">新闻</a>&gt;正文</div>\n" +
            //         "<div class=\"banner-imglist\">\n" +
            //         "         </div>\n" +
            //         "<div class=\"list marTop12\">\n" +
            //         "    <!-- 推广位02-->\n" +
            //         "     <!-- no_co -->\n" +
            //         "<a href=\"http://g.163.com/a?CID=39480&#38;Values=3315799004&#38;Redirect=http://v.tensynchina.com/servlet/clk/clk;06668-0026?http://www.faw-benteng.com/market/2015/hhhbys/m/\">奔腾全系购置税减半</a><br/>\n" +
            //         "<!--UC广告-->\n" +
            //         "<!--UCAD[v=1;ad=798]-->\n" +
            //         "【最新消息】\n" +
            //         "  <br/><a href=\"http://3g.163.com/ntes/special/003417GE/touch_live.html?roomid=\">《“十二五”时期中国的减灾行动》发布</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/news/16/1011/14/C33SOS2900014AEE.html\">假装观看他人打游戏 小伙在网吧偷盗多部手机</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/news/16/1011/14/C33S4I2T00011229.html\">女子扶摔倒老人被质疑想当网红 老人现身作证</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/news/16/1011/14/C33S7RNC00014JB6.html\">东京都知事薪金将减半 成日本同级别官员中最低</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/news/16/1011/14/C33R67L700014SEH.html\">房产中介买卖个人信息被批捕 电脑存非法信息500G</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/news/16/1011/14/C33QJFGG000146BE.html\">印媒:中印将再举行年度联演 曾因争执中断4年</a>\n" +
            //         "\n" +
            //         "    <!-- 推广位04-->\n" +
            //         "                <br/><a href=\"http://3g.163.com/links/3431\">*新闻客户端获最佳移动应用奖</a>\n" +
            //         "        \n" +
            //         "</div>\n" +
            //         "<div class=\"list marTop12\">\n" +
            //         "    【热点关注】\n" +
            //         "      <br/><a href=\"http://3g.163.com/gallery/photoview/3R710001/2203811.html\">看客：血色的迁徙，一路都是欲望之网</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/gallery/photoview/00AP0001/2203910.html\">郑州一建筑似央视大楼 被称为&#34;小裤衩&#34;</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/ntes/special/003417GE/touch_live.html?roomid=null\">她是&#34;背影杀手&#34;,曾拍艳照仍受国民爱戴</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/gallery/photoview/00AP0001/2203966.html\">济南一制药厂发生泄爆 学生烟雾中自习</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/gallery/photoview/00AO0001/2203997.html\">希腊民众总理官邸前示威 抗议潜在失业</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/gallery/photoview/00AO0001/2203996.html\">航拍美国飓风灾区 洪涝来袭一片汪洋</a>\n" +
            //         "\n" +
            //         "    <!-- 推广位05-->\n" +
            //         "                <br/><a href=\"http://quan.163.com/main.do\">*超给力优惠券免费领</a>\n" +
            //         "        \n" +
            //         "</div>\n" +
            //         "<div class=\"list marTop12\">\n" +
            //         "    【精彩推荐】\n" +
            //         "    <!-- 推广位03-->\n" +
            //         "      <br/><a href=\"http://3g.163.com/money/16/1011/13/C33P1BG9002580SL.html\">三星宣布召回中国大陆地区全部Note7 可全额退款</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/news/16/1011/13/C33MES560001124J.html\">三星被指区别对待中国消费者:中国官网无任何公告</a>\n" +
            //         "  <br/><a href=\"http://3g.163.com/news/16/1011/11/C33GFLIP0001124J.html\">温州通报农房倒塌事故:系地基不牢所致 22人遇难</a>\n" +
            //         "<br/><a href=\"http://g.163.com/a?CID=39478&#38;Values=1866966199&#38;Redirect=http://e.cn.miaozhen.com/r/k=2014993&#38;p=6y08h&#38;dx=0&#38;rt=2&#38;ns=__IP__&#38;ni=__IESID__&#38;v=__LOC__&#38;nd=__DRA__&#38;np=__POS__&#38;nn=__APP__&#38;vo=335ebe70f&#38;vr=2&#38;o=http%3A%2F%2Fevent.ftms.com.cn%2Fnewcrown_mobile%2F%3Fv%3D18%26utm_source%3D163wap%26utm_medium%3Darticle%26utm_term%3Darticle%26utm_content%3Dtext3%26utm_campaign%3Dcrown_Y15Q4M12_1005\">皇冠2.0T+ “约驾”有礼</a>\n" +
            //         "<br/><a href=\"http://3g.163.com/links/3101\">欧朋浏览器极速省流量</a>\n" +
            //         "\n" +
            //         "<!--广告06-->\n" +
            //         "            <br/><a href=\"http://3g.163.com/links/6475\">*高能辐射区，一般人玩不懂</a>\n" +
            //         "        \n" +
            //         "</div>\n" +
            //         "<div class=\"cnav marTop12 lBlue\"><div class=\"btmNav\">\n" +
            //         "<p class=\"center\"><a href=\"http://3g.163.com/nav/\">新闻导航</a> | <a href=\"http://3g.163.com/links/\">友情链接</a></p>\n" +
            //         "</div></div>\n" +
            //         "<!-- <div class=\"imgArea\"> \n" +
            //         "  <a href=\"http://g.163.com/a?CID=39581&Values=1920285938&Redirect=http://vitara.changansuzuki.com/h5/\">\n" +
            //         "    <img src=\"http://img1.126.net/channel4/021499/ca60090_1223.jpg\" width=\"100%\">\n" +
            //         "  </a>\n" +
            //         "  <p><a href=\"http://g.163.com/a?CID=39581&Values=3865818016&Redirect=http://valiantway.leibotech.com/\">敢做 敢拼，I'm VITARA\n" +
            //         "</a></p>\n" +
            //         "</div> -->\n" +
            //         "<p class=\"backTop\"><a id=\"btm\" href=\"#top\"><img src=\"http://img1.cache.netease.com/3g/img09/backtop.gif\"/></a></p>\n" +
            //         "<div class=\"foot\"><span><a href=\"http://3g.163.com/feedback/\">&gt;&gt;给网易提意见</a><br /><a href=\"http://3g.163.com/x/\" class=\"cBlue\">手机网易网</a>-<a href=\"http://3g.163.com/nav/\" class=\"cBlue\">导航</a>-<a href=\"http://3g.163.com/search/\" class=\"cBlue\">搜索</a>-<a href=\"http://3g.163.com/fav/\" class=\"cBlue\">收藏</a></span><br/><span class=\"EnText\">163.com [2017-01-06 20:39]</span></div>\n" +
            //         "<!--domob start-->\n" +
            //         "<script type=\"text/javascript\">\n" +
            //         "// var domob_vars = {\n" +
            //         "//     pub_id: '56OJyRCYuMVLNpCtjp',\n" +
            //         "//     style : 'inline',\n" +
            //         "//     docks : ['domob_ad1'],\n" +
            //         "//     test_mode: false //在上线时，请修改此处的值为false\n" +
            //         "// };\n" +
            //         "</script>\n" +
            //         "<!-- <script type=\"text/javascript\" src=\"http://s.domob.cn/sdk/domobt.js\"></script> -->\n" +
            //         "<!--domob end-->\n" +
            //         "<!-- 2017-01-06 20:40:08 -->\n" +
            //         "<!-- version:xhtml -->\n" +
            //         "</body>\n" +
            //         "</html>";

            mJsoupParser = new JsoupParser(content);
            Element title = mJsoupParser.getFirstElementByClass(TitleClassName);
            Element contentNode = mJsoupParser.getFirstElementByClass(ContentName);
            if(contentNode != null) {
                // Remove all anchors.
                removeAnchors(contentNode);

                // Deal with video snap link
                dealWithVideoTag(contentNode);

                // get large image src
                dealWithImageTags(contentNode);

                Element about = mJsoupParser.getFirstElementByClass(AboutClassName);
                String timeAndSource = about.text();
                String htmlContent = ArticleTemplate.replace("<!--CONTENT-->", contentNode.toString());
                htmlContent = htmlContent.replace("<!--TITLE-->", title.text());
                htmlContent = htmlContent.replace("<!--TIME_SOURCE-->", timeAndSource.substring(0, timeAndSource.indexOf("网友评论")));
                mArticlePresenter.onArticleLoaded(htmlContent);
            }
        }

        private void removeAnchors(Element contentNode) {
            Elements anchors = mJsoupParser.getElementsByTag(AnchorTagName, contentNode);
            for (Element anchor: anchors) {
                anchor.remove();
            }
        }

        private void dealWithImageTags(Element contentNode) {
            Elements imageElements = mJsoupParser.getElementsByTag(ImageTagName, contentNode);
            for(Element imageElement : imageElements) {
                String imageSrc = imageElement.attr(ImageSourceAttributeName);
                for (String imageExtension: ImageExtensions) {
                    if(imageSrc.contains(imageExtension)) {
                        String largeImageSrc = "http://" + imageSrc.substring(ImageAddressSource.length(), imageSrc.indexOf(imageExtension) + imageExtension.length() - 1);
                        mJsoupParser.setElementAttribute(imageElement, ImageSourceAttributeName, largeImageSrc);
                        break;
                    }
                }
            }
        }

        private void dealWithVideoTag(Element contentNode) {
            Elements paragraphs = mJsoupParser.getElementsByTag(ParagraphTagName, contentNode);
            Pattern videoUrlPattern = Pattern.compile("https(.+)mp4,");
            Pattern imageSourcePattern = Pattern.compile("IMG(.+)ALT");
            for (Element paragraph: paragraphs) {
                Pattern regstr = Pattern.compile("<!--@@VIDEO=(.+)-->");
                Matcher matcher = regstr.matcher(paragraph.toString());
                String videoImageNode = VideoImageNode;
                while(matcher.find()) {
                    String match = matcher.group();
                    Matcher videoUrlMatcher = videoUrlPattern.matcher(match);
                    if(videoUrlMatcher.find()) {
                        String videoUrl = videoUrlMatcher.group();
                        videoUrl = videoUrl.substring(0, videoUrl.length() - 1);
                        videoImageNode = videoImageNode.replace("<!--VIDEO_SOURCE-->", videoUrl);
                    }
                    Matcher imageUrlMatcher = imageSourcePattern.matcher(match);
                    if(imageUrlMatcher.find()) {
                        String imageUrl = imageUrlMatcher.group();
                        imageUrl = imageUrl.substring("IMG=\"".length(), imageUrl.length() - 5).trim();
                        videoImageNode = videoImageNode.replace("<!--IMAGE_SOURCE-->", imageUrl);
                    }
                    paragraph.append(videoImageNode);
                    break;
                }
            }
        }
    }
}
