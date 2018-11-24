package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.TreeHoleComments;
import com.bluegrass.pojo.User;

public interface ITreeHoleCommentsService {

    ServerResponse addComment(User user, TreeHoleComments treeHoleComments);

    ServerResponse listComments(Integer treeHoleId, int pageSize, int pageNum);

    ServerResponse deleteComments(User user, Integer treeHoleCommentsId);

    ServerResponse getTreeHoleCommentsNum(Integer treeHoleId);


    }
