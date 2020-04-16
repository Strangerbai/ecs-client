package com.bee.sample.ecs.service.impl;

import com.bee.sample.ecs.entity.EcsConstant;
import com.bee.sample.ecs.repository.ImageRepository;
import com.bee.sample.ecs.service.ImageService;
import com.bee.sample.ecs.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    @Resource
    ImageRepository imageRepository;

    @Override
    public String uploadImage(MultipartFile originFile, File imageFile) throws IOException {
        String fileName = originFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        List<String> extList = Arrays.asList(".jpg", ".png", ".jpeg", ".gif");
        if (!extList.contains(suffixName)) {
            throw new RuntimeException("格式不支持");
        }
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName;
        String url = "http://" +  EcsConstant.HOST_NAME +  ":" + EcsConstant.IMAGE_PORT + "/img/" + fileName;
        String uploadDir = EcsConstant.BASIS_PATH;
        File dest = new File( uploadDir + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        originFile.transferTo(dest);
        if (imageRepository.saveImageName(fileName,suffixName, ThreadLocalUtil.getCookie())){
            return url;
        }
        return "";
    }

}
