package com.bee.sample.ecs.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ImageService {

    String uploadImage(MultipartFile originFile, File imageFile) throws IOException;


}
