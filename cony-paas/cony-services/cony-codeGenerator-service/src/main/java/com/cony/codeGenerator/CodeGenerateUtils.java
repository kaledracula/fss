package com.cony.codeGenerator;

import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangk-p on 2017/8/22.
 */
public class CodeGenerateUtils {

    private String diskPath ;
    private String package_name;

    public void generate( Map<String,String[]> map,String diskPath,String package_name) throws Exception{
        this.diskPath = diskPath;
        this.package_name = package_name;
            for(Map.Entry<String,String[]> entry : map.entrySet()){
                String module = entry.getKey();
                String[] fileNames = entry.getValue();
                for (int i = 0; i < fileNames.length; i++) {
                    String fileName = fileNames[i];
                    //生成数据层接口文件
                    generateDaoInterfaceFile(fileName,module);
                    //生成服务实现层文件
                    generateDaoFile(fileName,module);
                    //生成服务层接口文件
                    generateServiceInterfaceFile(fileName,module);
                    //生成服务实现层文件
                    generateServiceImplFile(fileName,module);
                    //生成Controller层文件
                    generateControllerFile(fileName,module);

                }
            }

    }

    private void generateControllerFile(String fileName,String module) throws Exception{
        final String suffix = "Controller.java";
        final String path = diskPath + fileName + suffix;
        final String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("table_name",fileName);
        dataMap.put("package_name",package_name+ module);
        dataMap.put("module",module);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceImplFile(String fileName,String module) throws Exception{
        final String suffix = "ServiceImpl.java";
        final String path = diskPath + fileName + suffix;
        final String templateName = "Service.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("table_name",fileName);
        dataMap.put("package_name",package_name+ module);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceInterfaceFile(String fileName,String module) throws Exception{
        final String prefix = "I";
        final String suffix = "Service.java";
        final String path = diskPath + prefix + fileName + suffix;
        final String templateName = "Interface.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("table_name",fileName);
        dataMap.put("package_name",package_name+ module);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateDaoInterfaceFile(String fileName,String module) throws Exception{
        final String prefix = "I";
        final String suffix = "Dao.java";
        final String path = diskPath + prefix + fileName + suffix;
        final String templateName = "DaoInterface.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("table_name",fileName);
        dataMap.put("package_name",package_name+ module);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateDaoFile(String fileName,String module) throws Exception{
        final String suffix = "Repository.java";
        final String path = diskPath  + fileName + suffix;
        final String templateName = "Dao.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("table_name",fileName);
        dataMap.put("package_name",package_name+ module);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }


    private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap) throws Exception{
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }

}