package cn.iocoder.yudao.module.kitchen.controller.admin.aiyu;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;

public interface AiSceneMessageService {

    /** 订阅AI场景告警 */
    CommonResult<?> subscribe(SubscribeReqVO reqVO);

    /** 退订AI场景告警 */
    CommonResult<?> unsubscribe(UnsubscribeReqVO reqVO);

    /** 查询订阅信息 */
    CommonResult<?> querySubscription(QuerySubscribeReqVO reqVO);

    /** 刷新告警图片/视频下载地址 */
    CommonResult<?> refreshSrcUrl(RefreshSrcUrlReqVO reqVO);
}
