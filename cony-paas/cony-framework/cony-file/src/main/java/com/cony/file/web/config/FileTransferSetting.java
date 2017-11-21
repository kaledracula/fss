package com.cony.file.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by wangk-p on 2017/5/18.
 */
@Component
@ConfigurationProperties(prefix="fileTransfer")
public class FileTransferSetting {

    private String root;

    private File temp;

    private String attachment;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public File getTemp() {
        return temp;
    }

    public void setTemp(File temp) {
        this.temp = temp;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
