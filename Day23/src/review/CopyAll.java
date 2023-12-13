package review;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import javax.naming.ldap.SortResponseControl;
import java.io.*;

/*
*   拷贝目录
* */
public class CopyAll {
    public static void main(String[] args) {
//        拷贝源
        File srcFlie = new File("D:\\java\\新建文件夹");
//        拷贝目标
        File destFile = new File("D:\\java\\java\\Day23\\src\\review");
//        调用方法拷贝
        copyDir(srcFlie, destFile);
    }

    /**
     * 拷贝目录
     * @param srcFile   拷贝源
     * @param destFile  拷贝目标
     */
    private static void copyDir(File srcFile, File destFile) {
        if (srcFile.isFile()) {
//            srcFile如果是一个文件的话，递归结束。
//            是文件的时候需要拷贝
//            ...一边读一边写
            FileInputStream fileInputStream = null;
            FileOutputStream fileOutputStream = null;
            try {
//                读文件的目录
                fileInputStream = new FileInputStream(srcFile);
//                写文件的目录
                String path = (destFile.getAbsolutePath().endsWith("\\") ? destFile.getAbsolutePath() : destFile.getAbsolutePath() + "\\") + srcFile.getAbsolutePath().substring(3);
                fileOutputStream = new FileOutputStream(path);
//                一边读一边写
                byte[] bytes = new byte[1024 * 1024];       // 一次复制1MB
                int readCount = 0;
                if ((readCount = fileInputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, readCount);
                }

                fileOutputStream.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            return;
        }

//        获取源下面的子目录
        File[] files = srcFile.listFiles();
//        测试
//        System.out.println(files.length);

        for (File data :files) {
//            获取所有文件的（包括目录和文件）绝对路径
//            System.out.println(file.getAbsolutePath());

            if (data.isDirectory()) {
//                新建对应的目录
//                System.out.println(data.getAbsolutePath());
//                D:\java\新建文件夹 源目录
//                D:\java\java\Day23\src\review 目标目录
                String srcDir = data.getAbsolutePath();
//                System.out.println(srcDir.substring(3));
//                String destDir = "C:\\" + srcDir.substring(3);
//                获取目标目录
                String destDir = (destFile.getAbsolutePath().endsWith("\\") ? destFile.getAbsolutePath() : destFile.getAbsolutePath() + "\\") + srcDir.substring(3);
//                System.out.println(destDir);
                File newFile = new File(destDir);
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
            }

//            递归调用
            copyDir(data, destFile);
        }
    }
}
