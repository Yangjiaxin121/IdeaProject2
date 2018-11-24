package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;

public interface ITreeHolePraiseService {


    ServerResponse addTreeHolePraise(User user, Integer treeHoleId);

    ServerResponse getTreeHolePriseNum(Integer treeHoleId);

    ServerResponse deleteTreeHolePrise(User user, Integer treeHoleId);


    }
