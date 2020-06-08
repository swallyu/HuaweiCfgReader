package com.winsun.huawei.cfg;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.dom.DOMDocument;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class HwCfgParser {
    private String fileName;

    public HwCfgParser(String fileName) {
        this.fileName = fileName;
    }

    public void execute() {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(new File(fileName)); // 读取XML文件,获得document对象

            Element rootElement = document.getRootElement();

            List<Node> syndata = rootElement.selectNodes("spec:syndata");
            for (Node syndatum : syndata) {
                Element ele = (Element) syndatum;
                String functionType = ele.attribute("FunctionType").getValue();
                if (functionType.equals("gNodeBFunction")) {
                    //
                    int clzSize = 0;
                    Iterator<Element> iter = ele.elementIterator();
                    while (iter.hasNext()) {
                        Element cfgGroup = iter.next();
                        if (cfgGroup.getName().equals("class")) {
                            clzSize++;
                        }
                    }
                    System.out.println("clzSize:"+clzSize);
                }
            }

            System.out.println(syndata.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filename = "F:\\云山\\重庆\\新建文件夹\\巴南巴县中学-H5H\\BAKDATA20200608155950\\GNBCFG.XML";

        HwCfgParser parser = new HwCfgParser(filename);
        parser.execute();

    }
}
