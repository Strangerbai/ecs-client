package com.bee.sample.ecs.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ImageService {

    String uploadImage(MultipartFile originFile) throws IOException;

    List<String> getUserPictureName();


}
