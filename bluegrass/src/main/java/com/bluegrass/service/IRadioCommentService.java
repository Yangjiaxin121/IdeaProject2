package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.RadioComment;
import com.bluegrass.pojo.User;

public interface IRadioCommentService {

    ServerResponse addComments(User user, RadioComment radioComment);

    ServerResponse listComments(Integer radioId, int pageNum, int pageSize);

    ServerResponse deleteComments(User user, Integer radioCommentId);

    ServerResponse getCommentsNum(Integer radioId);
    }
