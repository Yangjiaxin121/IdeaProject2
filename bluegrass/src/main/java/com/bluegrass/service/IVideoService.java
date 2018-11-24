package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;
import com.bluegrass.pojo.Video;

public interface IVideoService {

    ServerResponse add(User user, Video video);

    ServerResponse searchMyAllVideo(User user, int pageNum, int pageSize);

    ServerResponse searchByUserId(Integer userId, int pageNum, int pageSize);

    ServerResponse deleteByVideoId(User user, Integer videoId);

    ServerResponse checkVideoId(Integer videoId);

    }
