package com.apkpack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 参考地址 v1 http://tech.meituan.com/mt-apk-packaging.html
 * v2 https://github.com/Meituan-Dianping/walle
 * 多渠道打包
 * channel.xlsx渠道表
 * 渠道包见apktool/apk
 * 命令gradle clean packChannel 执行多渠道打包程序
 * 获取渠道方法
 * gradle 2.2打包启用Android Signature V2 Scheme模式
 * 当应用被v2签名后，如果在进一步对应用进行更改则会导致签名无效
 * 往apk中插入渠道包文件，导致应用进行了修改所以导致v2签名失效
 * 在android7.0及以上手机可能导致应用包出问题无法安装；错误码：
 * {"ret":-3,"msg":"com.bihe0832.checksignature.ApkSignatureSchemeV2Verifier$SignatureNotFoundException:
 * No APK Signing Block before ZIP Central Directory","isV2":true,"isV2OK":false,"version":"","serverUrl":""}
 * 现使用美团walle方法渠道打包
 * v2:
 *  WalleChannelReader.getChannel(mInstance,"0");
 *
 * v1:
 * public static String getChannel(Context context) {
     ApplicationInfo appinfo = context.getApplicationInfo();
     String sourceDir = appinfo.sourceDir;
     String ret = "";
     ZipFile zipfile = null;
     try {
     zipfile = new ZipFile(sourceDir);
     Enumeration<?> entries = zipfile.entries();
     while (entries.hasMoreElements()) {
     ZipEntry entry = ((ZipEntry) entries.nextElement());
     String entryName = entry.getName();
     if (entryName.startsWith("META-INF/channel")) {
     ret = entryName;
     break;
     }
     }
     } catch (IOException e) {
     e.printStackTrace();
     } finally {
     if (zipfile != null) {
     try {
     zipfile.close();
     } catch (IOException e) {
     e.printStackTrace();
     }
     }
     }
     String[] split = ret.split("_");
     if (split != null && split.length >= 2) {
     return ret.substring(split[0].length() + 1);
     } else {
     return "";
     }

     }
 */
public class Pack {
     private static String appName[]={"animation"};
     private static String userDir=System.getProperty("user.dir");

    private static String SEPARATOR = File.separator;
    private static String OS = System.getProperty("os.name").toLowerCase();


    /**
     * 渠道号
     */
	private static String[] chanc = new String[] { "02", "04",
		"05", "06","09", "10", "11","13", "23","25","55", "56"};


    public static void main(String[] args) throws Exception {
        System.out.println(userDir);
        File file;
        File libFile;
        File channelFile;
        if(userDir.indexOf("apktool")>0){
            file=new File(userDir+SEPARATOR+"apk");
            libFile =new File(userDir+SEPARATOR+"libs");
            channelFile =new File(userDir+SEPARATOR+"channel");
        }else{
            libFile =new File(userDir+SEPARATOR+"apktool"+SEPARATOR+"libs");
            file=new File(userDir+SEPARATOR+"apktool"+SEPARATOR+"apk");
            channelFile =new File(userDir+SEPARATOR+"apktool"+SEPARATOR+"channel");
        }
        if(!file.exists()){
            file.mkdir();
        }

        String checkAndroidV2SignaturePath =libFile.getAbsolutePath()+SEPARATOR+"CheckAndroidV2Signature.jar";
        String walleChannelWritterPath =libFile.getAbsolutePath()+SEPARATOR+"walle-cli-all.jar";
        String channelFilePath=channelFile.getAbsolutePath();

        String cmdPre = OS.contains("windows")?"cmd /c ":"";
        String charsetName = OS.contains("windows")?"GBK":"UTF-8";

        for(int i=0;i<appName.length;i++){
            File appFile = new File(file,appName[i]);
            if(!appFile.exists()){
                appFile.mkdir();
            }
            String channelsOutputFilePath =appFile.getAbsolutePath();
            File signedApkPath=new File(appFile,appName[i]+"-release.apk").getAbsoluteFile();
            try {
                String cmd="java -jar "  + checkAndroidV2SignaturePath +" " + signedApkPath;
                System.out.print(cmd);
                Process pro = Runtime.getRuntime().exec(cmdPre+cmd);
                BufferedReader input = new BufferedReader(new InputStreamReader(pro.getInputStream(), charsetName));
                String line = null;
                System.out.println("\n");
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
                cmd="java -jar " + walleChannelWritterPath + " batch -f " + channelFilePath + " " + signedApkPath + " " + channelsOutputFilePath;
                System.out.print(cmd);
                pro = Runtime.getRuntime().exec(cmdPre+cmd);
                input = new BufferedReader(new InputStreamReader(pro.getInputStream(), charsetName));
                line = null;
                System.out.println("\n");
                while ((line = input.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //老版本打包代码
//        for(String arg : args) {
//            System.out.println(arg);
//        }
//
//        for(int i=0;i<appName.length;i++){
//            File appFile=new File(file,appName[i]);
//            if(!appFile.exists()){
//                appFile.mkdir();
//            }
//            File oldPath=new File(appFile,appName[i]+"-release.apk");
//            System.out.println(oldPath.getAbsoluteFile());
//            int chancCount=chanc.length;
//            for (int c = 0; c < chancCount; c++) {
//                String newChancName = String
//                        .format(appName[i]+"_channel_%s_release.apk",
//                                chanc[c]);
//                File chancFile=new File(appFile,newChancName);
//                System.out.println(chancFile.getAbsoluteFile());
//                if(chancFile.exists()){
//                    chancFile.delete();
//                }
//                if(oldPath.exists()){
//                    System.out.println("doPack");
//                    doPack(chancFile.getPath(),oldPath.getPath(),chanc[c]);
//                }else{
//                    System.out.println("oldPath.!exists()");
//                }
//            }
//        }
    }

    public static void doPack(String newPath, String oldPath, String chanc) {

        File newFile = new File(newPath);
        if (!newFile.exists()) {
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.print(oldPath + "Copy  to  " + newFile.getAbsolutePath()
                    + "\n");
            fileChannelCopy(new File(oldPath), newFile);
            zipAddFile(newFile, chanc);
        }
    }

    /**
     * 拷贝文件
     *
     * @param s
     * @param t
     */
    public static void fileChannelCopy(File s, File t) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();// 得到对应的文件通道
            out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加 channel
     *
     * @param newFile
     * @param channel
     */
    public static void zipAddFile(File newFile, String channel) {
		/* Define ZIP File System Properies in HashMap */
        Map<String, String> zip_properties = new HashMap<>();
		/* We want to read an existing ZIP File, so we set this to False */
        zip_properties.put("create", "false");
		/* Specify the encoding as UTF -8 */
        zip_properties.put("encoding", "UTF-8");
		/*
		 * Specify the path to the ZIP File that you want to read as a File
		 * System
		 */
        Path path = Paths.get(newFile.getAbsolutePath());
        URI zip_disk = URI.create("jar:" + path.toUri());
		/* Create ZIP file System */
        try (FileSystem zipfs = FileSystems.newFileSystem(zip_disk,
                zip_properties)) {
			/* Create a Path in ZIP File */
            Path ZipFilePath = zipfs.getPath("META-INF", "channel_" + channel);
            if(userDir.indexOf("apktool")<0){
                userDir=userDir+SEPARATOR+"apktool";
            }
            Path addNewFile = Paths.get(userDir+"/temp.txt");
            Files.copy(addNewFile, ZipFilePath);
            System.out.print(newFile.getAbsolutePath() + "add channel "
                    + channel + "\n");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
