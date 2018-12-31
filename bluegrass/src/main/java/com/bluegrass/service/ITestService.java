package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.DepressionTest;
import com.bluegrass.pojo.User;

public interface ITestService {

    ServerResponse add(DepressionTest depressionTest);

    ServerResponse delete(Integer testId);

    ServerResponse update(DepressionTest depressionTest);

    ServerResponse getTest(int pageNum, int pageSize);

    ServerResponse saveResult(User user, Double result, String word);
    }
