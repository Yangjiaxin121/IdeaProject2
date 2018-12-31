/*
* @Author: Remnant
* @Date:   2018-11-19 14:50:29
* @Last Modified by:   Remnant
* @Last Modified time: 2018-11-19 16:33:35
*/
'use strict';
require('./index.css');

var navSide         = require('../../page/common/nav-side/index.js');
var _mm             = require('../../util/mm.js');
var _user           = require('../../service/user-service.js');
var templateIndex   = require('./index.string');

// page 逻辑部分
var page = {
    init: function(){
        this.onLoad();
    },
    onLoad : function(){
        // 初始化左侧菜单
        navSide.init({
            name: 'user-center'
        });
        // 加载用户信息
        this.loadUserInfo();
    },
    // 加载用户信息
    loadUserInfo : function(){
        var userHtml = '';
        _user.getUserInfo(function(res){
            userHtml = _mm.renderHtml(templateIndex, res);
            $('.panel-body').html(userHtml);
        }, function(errMsg){
            _mm.errorTips(errMsg);
        });
    }
};
$(function(){
    page.init();
});