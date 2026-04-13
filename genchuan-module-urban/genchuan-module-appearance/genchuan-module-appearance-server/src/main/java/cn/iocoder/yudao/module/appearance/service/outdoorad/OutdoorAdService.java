package cn.iocoder.yudao.module.appearance.service.outdoorad;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo.*;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.OutdoorAdOrderDO;

/**
 * 户外广告 Service 接口
 *
 * @author 亘川智城
 */
public interface OutdoorAdService {
    /**
     * 获得户外广告分页
     *
     * @param pageReqVO 分页查询
     * @return 户外广告分页
     */
    PageResult<OutdoorAdPageRespVO> getOutdoorAdPage(OutdoorAdPageReqVO pageReqVO);
    /**
     * 获得户外广告
     *
     * @param id 查询参数
     * @return 户外广告
     */
    OutdoorAdGetRespVO getOutdoorAd(String id);
    /**
     * 新增户外广告
     *
     * @param addReqVO 新增参数
     * @return 新增结果
     */
    OutdoorAdAddRespVO addOutdoorAd(OutdoorAdAddReqVO addReqVO);
    /**
     * 更新户外广告
     *
     * @param editReqVO 更新参数
     *
     */
    void editOutdoorAd(OutdoorAdEditReqVO editReqVO);
    /**
     * 删除户外广告
     */
    void removeOutdoorAds(OutdoorAdRemoveReqVO removeReqVO);

    PageResult<OutdoorAdOrderPageRespVO> getOrderPage(OutdoorAdOrderPageReqVO pageReqVO);

    OutdoorAdOrderGetRespVO getOrderDetail(String id);
//    /**
//     * 获得户外广告分页
//     *
//     * @param pageReqVO 分页查询
//     * @return 户外广告分页
//     */
//    PageResult<OutdoorAdDO> getOutdoorAdPage( OutdoorAdPageReqVO pageReqVO );
//    /**
//     * 获得户外广告
//     *
//     * @param getReqVO 查询参数
//     * @return 户外广告
//     */
//    OutdoorAdDO getOutdoorAd( OutdoorAdGetReqVO getReqVO );
//    /**
//     * 新增户外广告
//     *
//     * @param addReqVO 新增参数
//     * @return 新增结果
//     */
//    OutdoorAdDO addOutdoorAd( OutdoorAdAddReqVO addReqVO );
//    /**
//     * 更新户外广告
//     *
//     * @param editReqVO 更新参数
//     * @return 更新结果
//     */
//    Boolean editOutdoorAd( OutdoorAdEditReqVO editReqVO );
    /**
     * 获得户外广告订单分页
     *
     * @param pageReqVO 分页查询
     * @return 户外广告订单分页
     */
    PageResult<OutdoorAdOrderDO> getOutdoorAdOrderPage( OutdoorAdOrderPageReqVO pageReqVO );
    /**
     * 获得户外广告订单
     *
     * @param getReqVO 查询参数
     * @return 户外广告订单
     */
    OutdoorAdOrderDO getOutdoorAdOrder(  OutdoorAdOrderGetReqVO getReqVO );
}