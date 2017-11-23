package com.cony.codeGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangk-p on 2017/8/22.
 */
public class CodeGeneratorApplication {

    private static final String  diskPath = "D:\\coca-paas\\coca-services\\coca-codeGenerator-service\\src\\main\\java\\com\\glodon\\mybatis\\";
    private static final String  basePackage = "com.glodon.coca.services.";
    public static Map<String,String[]> getObjects() {
        Map<String,String[]> map = new HashMap<>();
        String[] fileNames = {"GDocFile"};
        map.put("gdoc",fileNames);
        return map;
    }


    public static void main(String[] args) throws Exception{
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generate(getObjects(),diskPath,basePackage);
    }
}
