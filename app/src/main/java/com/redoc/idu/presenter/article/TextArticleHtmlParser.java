package com.redoc.idu.presenter.article;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.redoc.idu.IDuApplication;
import com.redoc.idu.contract.article.IArticleContract;
import com.redoc.idu.model.bean.TextArticle;
import com.redoc.idu.utils.html.JsoupParser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mengliang Li on 12/31/2016.
 */

public class TextArticleHtmlParser {

    private final String ContentName = "content";
    private final String TitleClassName = "atitle tCenter";
    private final String AboutClassName = "about";
    private final String ImageTagName = "img";
    private final String ImageSourceAttributeName = "src";
    private final String AnchorTagName = "a";
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
    private final String ArticleTemplate = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<style> img{ max-width:100%; height:auto;}\n" +
            ".source {color:#999999; font-size:12px; text-align:left}\n" +
            ".title{ text-align:left; font-size:20px; font-weight:bold; padding:0px 3px 20px 0px; }\n" +
            "p {line-height:150%; padding:30px 0px 0px 0px;}\n" +
            ".content{color:#666666; font-size:15px; margin:3px; }</style>\n" +
            "<head>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"application/vnd.wap.xhtml+xml;charset=utf-8\"/>\n" +
            "<meta http-equiv=\"Cache-Control\" content=\"no-transform\"/>\n" +
            "<meta name=\"author\" content=\"网易\" />\n" +
            "<table style=\"padding:10px 0px 0px 0px; border-color:#0066cc;border-left-style:solid;border-width:5px\"><tr><td valign=\"top\">" +
            "<div class=\"title\"><!--TITLE--></div>" +
            "<div class=\"source\"><!--TIME_SOURCE--></div>\n" +
            "</td></tr></table>\n" +
            "<div class=\"content\"><!--CONTENT--></div>\n" +
            "</body>\n" +
            "</html>";

    private JsoupParser mJsoupParser;
    private IArticleContract.IArticlePresenter mArticlePresenter;

    public void parse(String url, IArticleContract.IArticlePresenter articlePresenter) {
        mArticlePresenter = articlePresenter;
        IDuApplication.HttpClient.addStringRequest(url, new RawUrlResponseListener(), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    class RawUrlResponseListener implements Response.Listener<String> {

        @Override
        public void onResponse(String content) {
            mJsoupParser = new JsoupParser(content);
            Element title = mJsoupParser.getFirstElementByClass(TitleClassName);
            Element contentNode = mJsoupParser.getFirstElementByClass(ContentName);
            if(contentNode != null) {
                Elements imageElements = mJsoupParser.getElementsByTag(ImageTagName, contentNode);
                Elements anchors = mJsoupParser.getElementsByTag(AnchorTagName, contentNode);
                for (Element anchor: anchors) {
                    anchor.remove();
                }
                // get large image src
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
                Element about = mJsoupParser.getFirstElementByClass(AboutClassName);
                String timeAndSource = about.text();
                String htmlContent = ArticleTemplate.replace("<!--CONTENT-->", contentNode.toString());
                htmlContent = htmlContent.replace("<!--TITLE-->", title.text());
                htmlContent = htmlContent.replace("<!--TIME_SOURCE-->", timeAndSource.substring(0, timeAndSource.indexOf("网友评论")));
                mArticlePresenter.onArticleLoaded(htmlContent);
            }
        }
    }
}
