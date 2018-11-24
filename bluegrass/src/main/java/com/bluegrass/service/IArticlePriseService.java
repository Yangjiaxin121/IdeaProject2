package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;

public interface IArticlePriseService {

    ServerResponse addArticlePrise(User user, Integer articleId);

    ServerResponse getArticlePriseNum(Integer articleId);

    ServerResponse deleteArticlePrise(User user, Integer articleId);

    }
