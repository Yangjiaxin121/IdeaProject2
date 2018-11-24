package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.AnonymousTreeHole;
import com.bluegrass.pojo.User;

public interface IAnonymousTreeHoleService {

    ServerResponse addTreeHole(User user, AnonymousTreeHole anonymousTreeHole);

    ServerResponse listTreehole(int pageNum, int pageSize);

    ServerResponse searchMyTreeHole(User user, int pageNum, int pageSize);

    ServerResponse deleteMyTreeHole(User user, Integer treeHoleId);
    }
