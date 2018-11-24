package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;

public interface IVideoPraiseService {

    ServerResponse addVideoPraise(User user, Integer videoId);

    ServerResponse getVideoPraiseNum(Integer videoId);

    ServerResponse deleteVideoPraise(User user, Integer videoId);
    }
