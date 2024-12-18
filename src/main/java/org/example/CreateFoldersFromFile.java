package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CreateFoldersFromFile {
    public static void main(String[] args) {
        // 定义文本文件的路径
        String filePath = "input/a.txt"; // 请确保这个路径是正确的

        // 获取当前工作目录并构建out文件夹的路径
        File currentDir = new File(System.getProperty("user.dir"));
        File outDir = new File(currentDir, "output");

        // 如果out文件夹不存在，则创建它
        if (!outDir.exists() && !outDir.mkdirs()) {
            System.err.println("无法创建out文件夹");
            return; // 如果无法创建out文件夹，则退出程序
        }

        // 使用 BufferedReader 读取文件
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 去除每行末尾的换行符和空格
                line = line.trim();

                // 忽略空行
                if (!line.isEmpty()) {
                    // 构建文件夹的完整路径
                    File folder = new File(outDir, line);

                    // 创建文件夹
                    if (!folder.exists() && folder.mkdir()) {
                        System.out.println("成功创建文件夹: " + folder.getAbsolutePath());
                    } else if (folder.exists()) {
                        System.out.println("文件夹已存在: " + folder.getAbsolutePath());
                    } else {
                        System.out.println("创建文件夹失败: " + folder.getAbsolutePath());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("读取文件时出错: " + e.getMessage());
        }
    }
}