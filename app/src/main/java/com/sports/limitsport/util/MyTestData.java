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

            data.add("http://img0.imgtn.bdimg.com/it/u=2444306774,542387780&fm=214&gp=0.jpg");

            data.add("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=9909ddc1b91c8701c2bbbaa54f16f45a/a08b87d6277f9e2fb0727e2d1530e924b899f354.jpg");
            data.add("http://imgsrc.baidu.com/imgad/pic/item/64380cd7912397dd6aca3b205382b2b7d0a28767.jpg");
            data.add("http://img.taopic.com/uploads/allimg/121017/234940-12101FS43275.jpg");
            data.add("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=bc2036d531c79f3d9becec73d2c8a764/f9dcd100baa1cd110796010ab312c8fcc3ce2df3.jpg");

            data.add("http://file06.16sucai.com/2016/0927/eec7700e926af72e918e9efc76e5c614.jpg");
            data.add("http://img05.tooopen.com/images/20140326/sy_57640132134.jpg");
            data.add("http://img3.redocn.com/tupian/20150318/qingxinshuzhibiankuang_4021000.jpg");
            data.add("http://img06.tooopen.com/images/20170316/tooopen_sy_201978487694.jpg");
            data.add("http://img.sccnn.com/bimg/338/27502.jpg");

            data.add("http://img4.imgtn.bdimg.com/it/u=1184165042,3228167293&fm=214&gp=0.jpg");
            data.add("http://img05.tooopen.com/images/20160109/tooopen_sy_153858412946.jpg");
            data.add("http://img02.tooopen.com/images/20160617/tooopen_sy_165387259697.jpg");


            for (int i = 0; i < 20; i++)
                data.add("http://img02.tooopen.com/images/20160617/tooopen_sy_165387259697.jpg");

            for (int i = 0; i < 20; i++)
                data.add("http://img4.imgtn.bdimg.com/it/u=1184165042,3228167293&fm=214&gp=0.jpg");
        }
        return data;
    }

    public static List<String> getEmptyData() {
        return new ArrayList<>();
    }
}
