/*
* @Author: Remnant
* @Date:   2018-11-10 16:33:16
* @Last Modified by:   Remnant
* @Last Modified time: 2018-11-13 21:29:44
*/
'use strict';
require('./index.css');
var _mm=require('../../util/mm.js');
$(function()
{
	var type = _mm.getUrlParam('type')||'default',
	    $element =$('.'+type+'-success').show();
});