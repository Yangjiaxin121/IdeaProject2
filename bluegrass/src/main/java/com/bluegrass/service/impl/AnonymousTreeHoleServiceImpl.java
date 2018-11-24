package com.bluegrass.service.impl;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.AnonymousTreeHoleMapper;
import com.bluegrass.pojo.AnonymousTreeHole;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IAnonymousTreeHoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AnonymousTreeHoleServiceImpl implements IAnonymousTreeHoleService {

    @Autowired
    AnonymousTreeHoleMapper anonymousTreeHoleMapper;


    /**
     * 发表树洞
     * @param user
     * @param anonymousTreeHole
     * @return
     */
    public ServerResponse addTreeHole(User user, AnonymousTreeHole anonymousTreeHole){
        if (anonymousTreeHole.getDetail() == null){
            return ServerResponse.createByErrorMessage("detail不能为空");
        }
        anonymousTreeHole.setUserId(user.getUserId());
        anonymousTreeHole.setTreeHoleId(0);
        anonymousTreeHole.setPraise(0);
        int count  = anonymousTreeHoleMapper.insert(anonymousTreeHole);
        if (count > 0){
            return ServerResponse.createBySuccess("树洞发表成功");
        }
        return ServerResponse.createByErrorMessage("树洞发表失败");
    }


    /**
     * 获取树洞全部信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse listTreehole(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<AnonymousTreeHole> anonymousTreeHoleList = anonymousTreeHoleMapper.selectAll();
        for (AnonymousTreeHole anonymousTreeHole : anonymousTreeHoleList) {
            System.out.println(anonymousTreeHole);
            anonymousTreeHole.setUserId(null);
        }


        PageInfo pageInfo = new PageInfo(anonymousTreeHoleList);
        return ServerResponse.createBySuccess(pageInfo);
    }


    /**
     * 查询用户的所有树洞
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse searchMyTreeHole(User user, int pageNum, int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<AnonymousTreeHole> anonymousTreeHoleList = anonymousTreeHoleMapper.selectByUserId(user.getUserId());
        for (AnonymousTreeHole anonymousTreeHole : anonymousTreeHoleList) {
            anonymousTreeHole.setUserId(null);
        }
        PageInfo pageInfo = new PageInfo(anonymousTreeHoleList);
        return ServerResponse.createBySuccess(pageInfo);
    }


    /**
     * 删除我的指定树洞
     * @param user
     * @param treeHoleId
     * @return
     */
    public ServerResponse deleteMyTreeHole(User user, Integer treeHoleId){
        if (treeHoleId == null){
            return ServerResponse.createByErrorMessage("treeHoleId不能为空");
        }
        int count = anonymousTreeHoleMapper.deleteByUserIdAndTreeHoleId(user.getUserId(),treeHoleId);
        if (count>0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }


}










