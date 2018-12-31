# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     md5sign
   Description :
   Author :       YOUQING
   date：          2017/11/20
-------------------------------------------------
   Change Activity:
                   2017/11/20:
-------------------------------------------------
"""
import hashlib
import time
import random
import string
import urllib
import sys



def get_params(plus_item):
    '''请求时间戳（秒级），用于防止请求重放（保证签名5分钟有效）'''
    t = time.time()
    time_stamp=int(t)

    '''请求随机字符串，用于保证签名不可预测'''
    nonce_str = ''.join(random.sample(string.ascii_letters + string.digits, 10))

    '''应用标志，这里修改成自己的id和key'''
    app_id='2110019887'
    app_key='l6ly6nLYwSqiaWtn'

    '''值使用URL编码，URL编码算法用大写字母'''
    text1=plus_item
    text=urllib.parse.quote(text1.encode(sys.stdin.encoding).decode('utf8')).upper()

    '''拼接应用密钥，得到字符串S'''
    sign_before='app_id='+app_id+'&nonce_str='+nonce_str+'&text='+text+'&time_stamp='+str(time_stamp)+'&app_key='+app_key

    '''计算MD5摘要，得到签名字符串'''
    m=hashlib.md5()
    m.update(sign_before.encode("utf8"))
    sign=m.hexdigest()
    sign=sign.upper()

    params='app_id='+app_id+'&time_stamp='+str(time_stamp)+'&nonce_str='+nonce_str+'&sign='+sign+'&text='+text

    return params
