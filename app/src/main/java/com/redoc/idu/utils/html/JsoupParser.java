package com.redoc.idu.utils.html;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Mengliang Li on 12/31/2016.
 */

public class JsoupParser {

    private final String ClassName = "class";
    private final String ContentName = "content";
    private final String AnchorName = "a";

    private Document mDocument;
    public JsoupParser(String html) {
        mDocument = Jsoup.parse(html);
    }

    public Elements getElementsByClass(String className) {
        return mDocument.getElementsByAttributeValue(ClassName, className);
    }

    public Element getFirstElementByClass(String className) {
        return getElementsByClass(className).first();
    }

    public Elements getElementsByTag(String tag, Element element) {
        return element.getElementsByTag(tag);
    }

    public Element getElementByTag(String tag, Element element) {
        return getElementsByTag(tag, element).first();
    }

    public void setElementAttribute(Element element, String attributeName, String attributeValue) {
        element.attr(attributeName, attributeValue);
    }

    public Elements getElementsByMatchingText(String text) {
        return mDocument.getElementsMatchingText(text);
    }

    // public Elements getElementsByTagAndValue(Element element, String tag, String value) {
    //     return element.get
    // }
}
