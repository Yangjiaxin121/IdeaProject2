package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.Radio;
import com.bluegrass.pojo.User;

public interface IRadioService {

    ServerResponse add(User user, Radio radio);

    ServerResponse searchMyAllRadio(User user, int pageNum, int pageSize);

    ServerResponse searchByUserId(Integer userId, int pageNum, int pageSize);

    ServerResponse checkRadioId(Integer radioId);

    ServerResponse deleteByRadioId(User user, Integer radioId);
    }
