package com.cony.codeGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangk-p on 2017/8/22.
 */
public class CodeGeneratorApplication {

    private static final String  diskPath = "F:\\fss\\cony-paas\\cony-services\\cony-codeGenerator-service\\src\\main\\java\\com\\cony\\code\\";

    private static final String  basePackage = "com.cony.fss.";
    public static Map<String,String[]> getObjects() {
        Map<String,String[]> map = new HashMap<>();
        String[] fileNames = {"Role"};
        map.put("security",fileNames);
        return map;
    }


    public static void main(String[] args) throws Exception{
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generate(getObjects(),diskPath,basePackage);
    }
}
