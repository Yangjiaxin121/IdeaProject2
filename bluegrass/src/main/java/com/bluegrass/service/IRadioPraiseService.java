package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;

public interface IRadioPraiseService {

    ServerResponse addRadioPraise(User user, Integer radioId);

    ServerResponse getRadioPraiseNum(Integer radioId);

    ServerResponse deleteRadioPraise(User user, Integer radioId);
}
