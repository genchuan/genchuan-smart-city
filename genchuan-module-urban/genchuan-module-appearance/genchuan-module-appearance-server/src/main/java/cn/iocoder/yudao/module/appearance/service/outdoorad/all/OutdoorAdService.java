package cn.iocoder.yudao.module.appearance.service.outdoorad.all;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.all.vo.*;
import cn.iocoder.yudao.module.appearance.dal.dataobject.outdoorad.all.OutdoorAdDO;

import java.util.List;
import java.util.Map;

/**
 * 户外广告 Service 接口
 *
 * @author 亘川智城
 */
public interface OutdoorAdService {

    /**
     * 创建户外广告
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
//    Long createOutdoorAd(@Valid PendingDisposalSaveReqVO createReqVO);
    Long createOutdoorAd( OutdoorAdSaveReqVO createReqVO );

    /**
     * 更新户外广告
     *
     * @param updateReqVO 更新信息
     */
//    void updateOutdoorAd(@Valid PendingDisposalSaveReqVO updateReqVO);
    void updateOutdoorAd( OutdoorAdSaveReqVO updateReqVO );

    /**
     * 删除户外广告
     *
     * @param id 编号
     */
    void deleteOutdoorAd( Long id );

    /**
     * 获得户外广告
     *
     * @param getReqVO 查询参数
     * @return 户外广告
     */
    OutdoorAdDO getOutdoorAd( OutdoorAdGetReqVO getReqVO );

    /**
     * 获得户外广告分页
     *
     * @param pageReqVO 分页查询
     * @return 户外广告分页
     */
    PageResult<OutdoorAdDO> getOutdoorAdPage( OutdoorAdPageReqVO pageReqVO );

    /**
     * 获得户外广告关联查询分页
     *
     * @param pageReqVO 分页查询
     * @return 户外广告关联查询分页
     */
    PageResult<OutdoorAdDO> getOutdoorAdPageWithRelations( OutdoorAdPageReqVO pageReqVO );

    /**
     * 导入户外广告数据
     *
     * @param list 导入数据列表
     * @return 导入结果
     */
    Map<String, Object> importOutdoorAd( List<OutdoorAdSaveReqVO> list );

    /**
     * 获取核心指标
     *
     * @return 核心指标
     */
    Map<String, Object> getCoreIndicators();

    /**
     * 获取区域预警趋势
     *
     * @return 区域预警趋势
     */
    List<Map<String, Object>> getAreaWarningTrend();

    /**
     * 获取近30日预警趋势
     *
     * @return 近30日预警趋势
     */
    List<Map<String, Object>> getRecentWarningTrend();

    /**
     * 获取广告状态占比
     *
     * @return 广告状态占比
     */
    List<Map<String, Object>> getAdStatusDistribution();

    /**
     * 获取预警类型占比
     *
     * @return 预警类型占比
     */
    List<Map<String, Object>> getWarningTypeDistribution();

    /**
     * 获取复核结果占比
     *
     * @return 复核结果占比
     */
    List<Map<String, Object>> getReviewResultDistribution();

    /**
     * 获得待处置户外广告分页
     *
     * @param pageReqVO 分页查询
     * @return 待处置户外广告分页
     */
}