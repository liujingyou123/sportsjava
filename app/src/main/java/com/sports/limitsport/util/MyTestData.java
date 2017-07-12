package com.sports.limitsport.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuworkmac on 17/7/7.
 */

public class MyTestData {
    public static List<String> data = new ArrayList<>();

    public static List<String> getData() {
        if (data.size() == 0) {
            data.add("http://image.tianjimedia.com/uploadImages/2015/318/28/J0IUZ1ST711A.jpg");
            data.add("http://pic.87g.com/upload/2016/1105/20161105035353511.jpg");
            data.add("http://img.pconline.com.cn/images/upload/upc/tx/ladyproduct/1410/18/c0/39816467_1413611585425_medium.jpg");
            data.add("http://img1.imgtn.bdimg.com/it/u=632417265,4092516572&fm=26&gp=0.jpg");
            data.add("http://image.tianjimedia.com/uploadImages/2015/149/10/4YH3B281RZIA.jpg");
            data.add("http://img0.imgtn.bdimg.com/it/u=2444306774,542387780&fm=214&gp=0.jpg");
            for (int i = 0; i < 10; i++)
                data.add("http://img.pconline.com.cn/images/upload/upc/tx/ladyproduct/1410/18/c0/39816467_1413611585425_medium.jpg");
        }
        return data;
    }

    public static List<String> getEmptyData() {
        return new ArrayList<>();
    }
}
