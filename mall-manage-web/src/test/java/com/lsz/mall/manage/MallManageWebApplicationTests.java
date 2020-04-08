package com.lsz.mall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class MallManageWebApplicationTests {

    @Test
    void contextLoads() throws IOException, MyException {
        String tracker = this.getClass().getResource("/tracker.conf").getPath();
        ClientGlobal.init(tracker);
        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer= trackerClient.getTrackerServer();
        StorageClient storageClient=new StorageClient(trackerServer,null);
        String orginalFilename="D://2.jpg";
        String[] upload_file_Info = storageClient.upload_file(orginalFilename, "jpg", null);
        for (int i = 0; i < upload_file_Info.length; i++) {
            String s = upload_file_Info[i];
            System.out.println("s = " + s);
        }

    }
}
