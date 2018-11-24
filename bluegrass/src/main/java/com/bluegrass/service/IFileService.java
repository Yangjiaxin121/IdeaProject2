package com.bluegrass.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String upLoad(MultipartFile file, String path);

}
