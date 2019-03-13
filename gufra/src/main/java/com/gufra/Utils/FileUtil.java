package com.gufra.Utils;

import java.io.File;

public class FileUtil {

    //搜索
    private String searchFile(String keyWord){
        String result = "";
        File[] files = new File("/").listFiles();
        for (File file :files){
            if (file.getName().indexOf(keyWord) >= 0){
                result += file.getPath()+"\n";
            }
            if (result.equals("")){
                result = "找不到文件!!";
            }
        }
        return result;
    }
}
