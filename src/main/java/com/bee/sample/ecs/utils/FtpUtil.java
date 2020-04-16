package com.bee.sample.ecs.utils;

import com.bee.sample.ecs.entity.EcsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class FtpUtil {
    //通过properties文件自动注入
//    @Value("${ftp.host}")
//    private String host;    //ftp服务器ip
//    @Value("${ftp.port}")
//    private int port;        //ftp服务器端口
//    @Value("${ftp.username}")
//    private String username;//用户名
//    @Value("${ftp.password}")
//    private String password;//密码
//    @Value("${ftp.basePath}")
//    private String basePath;//存放文件的基本路径

    private static FTPClient ftp = null;

    private static String host = EcsConstant.HOST_NAME;
    private int port = EcsConstant.FTP_PORT;
    private String username = EcsConstant.USER_NAME;
    private String password = EcsConstant.PASSWORD;
    private String basePath = EcsConstant.FTP_BASE_PATH;

    public FtpUtil() throws IOException {
        log.info("host {} username {} password {} basePath {}", host, username, password, basePath );
        FTPClient ftpClient=new FTPClient();
        try{
            ftpClient.connect(host, port);
            ftpClient.login(username, password);
            //设置文件编码格式
            ftpClient.setControlEncoding("UTF-8");
        } finally {
            if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                throw new RuntimeException("FTP服务器拒绝连接");
            }
            if(!ftpClient.changeWorkingDirectory(basePath)) {
                throw new RuntimeException("根目录不存在，需要创建");
            }
            ftp = ftpClient;
        }


    }
    /**
     *
     * @param filename    上传文件名
     * @param input        输入流
     * @return
     */
    public boolean fileUpload(String filename, InputStream input) {
        try {
            //设置传输方式为流方式
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);

            //判断是否存在目录
            if(ftp.changeWorkingDirectory(basePath)) {
                //设置上传文件的类型为二进制类型
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                //上传文件
                if(!ftp.storeFile(filename, input)) {
                    return false;
                }
                input.close();
                ftp.logout();
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }
    /**
     *
     * @param filename    文件名，注意！此处文件名为加路径文件名，如：/2015/06/04/aa.jpg
     * @param localPath    存放到本地第地址
     * @return
     */
    public boolean downloadFile(String filename,String localPath) {
        try {
            //设置传输方式为流方式
            ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            //获取状态码，判断是否连接成功
            if(!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                throw new RuntimeException("FTP服务器拒绝连接");
            }

            //判断是否存在目录
            if(!ftp.changeWorkingDirectory(basePath)) {
                throw new RuntimeException("文件路径不存在："+basePath);
            }
            //获取该目录所有文件
            FTPFile[] files=ftp.listFiles();
            for (FTPFile file : files) {
                //判断是否有目标文件
                //System.out.println("文件名"+file.getName()+"---"+name);
                if(file.getName().equals(filename)) {
                    //System.out.println("找到文件");
                    //如果找到，将目标文件复制到本地
                    File localFile =new File(localPath+"/"+file.getName());
                    OutputStream out=new FileOutputStream(localFile);
                    ftp.retrieveFile(file.getName(), out);
                    out.close();
                }
            }
            ftp.logout();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean deleteFile(String filename) {
        try {
            //判断是否存在目录,不存在则说明文件存在
            if(!ftp.changeWorkingDirectory(basePath)) {
                return true;
            }
            if(ftp.deleteFile(filename)) {
                clearDirectory(ftp, basePath, filename);
                ftp.logout();
                return true;
            }
            ftp.logout();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     *
     * @param ftp
     * @param basePath
     * @param path        以path为根，递归清除上面所有空的文件夹，直到出现不为空的文件夹停止，最多清除到basePath结束
     * @throws IOException
     */
    private void clearDirectory(FTPClient ftp,String basePath,String path) throws IOException {
        //如果路径长度小于2，说明到顶了
        if(path.length()<2) {
            return ;
        }
        //如果当前目录文件数目小于1则删除此目录
        if(ftp.listNames(basePath+path).length<1) {
            //删除目录
            System.out.println("删除目录："+basePath+path);
            ftp.removeDirectory(basePath+path);
            int index=path.lastIndexOf("/");
            //路径向上一层
            path=path.substring(0, index);
            //继续判断
            clearDirectory(ftp, basePath, path);
        }
    }


    public List<String> getFilesName(String basePath){
        List<String> result = new ArrayList<>();
        try{
            FTPFile[] files = ftp.listFiles();
            for(FTPFile ftpFile : files){
                result.add(ftpFile.getName());
            }
        } catch (RuntimeException | IOException e){
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        FtpUtil ftputil=new FtpUtil();
        List<String> flag=ftputil.getFilesName("/var/ftp/test");
//        ftputil.downloadFile("20200413213022.png", "/Users/weidian/Desktop/test_halo/ecs-client");
        System.out.println(flag);
    }

}
