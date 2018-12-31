package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.ResultRecommend;
import com.bluegrass.pojo.User;

public interface IRecommendService {
    ServerResponse add(User user, ResultRecommend resultRecommend);

    ServerResponse update(User user, ResultRecommend resultRecommend);

    ServerResponse delete(User user, Integer recommendId);

    ServerResponse get(Integer value, int pageNum, int pageSize);
    }
