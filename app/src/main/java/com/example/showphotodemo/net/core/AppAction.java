
package com.example.showphotodemo.net.core;

import com.example.showphotodemo.net.PostRequestCallBack;

/**
 * 接收app层的各种Action
 *
 * @author Keegan小钢
 * @version 1.0
 * @date 15/6/25
 */
public interface AppAction {
    /**
     * 获取用户信息
     * @return
     */
    public void getUser(PostRequestCallBack requestCallBack);

    /**
     * 获取朋友圈列表
     * @return
     */
    public void getWeChatMomentList(PostRequestCallBack requestCallBack);
}
