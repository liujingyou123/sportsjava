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
            for (int i = 0; i < 10; i++) data.add("" + i);
        }
        return data;
    }
}
