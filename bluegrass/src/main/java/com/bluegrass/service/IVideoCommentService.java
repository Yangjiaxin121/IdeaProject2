package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;
import com.bluegrass.pojo.VideoComment;
import com.github.pagehelper.PageInfo;

public interface IVideoCommentService {

    ServerResponse addComments(User user, VideoComment videoComment);

    ServerResponse<PageInfo> listComments(Integer videoId, int pageNum, int pageSize);

    ServerResponse deleteComments(User user, Integer videoCommentId);

    ServerResponse getCommentsNum(Integer videoId);


    }
