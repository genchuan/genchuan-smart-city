package cn.iocoder.yudao.module.vehiclecharging.service.sharingratio;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingratio.SharingRatioDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 分账比例 Service 接口
 *
 * @author 亘川智城
 */
public interface SharingRatioService {

    /**
     * 创建分账比例
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSharingRatio(@Valid SharingRatioCreateReqVO createReqVO);

    /**
     * 更新分账比例
     *
     * @param updateReqVO 更新信息
     */
    void updateSharingRatio(@Valid SharingRatioUpdateReqVO updateReqVO);

    /**
     * 获得分账比例
     *
     * @param id 编号
     * @return 分账比例
     */
    SharingRatioDO getSharingRatio(Long id);

    /**
     * 获得分账比例分页
     *
     * @param pageReqVO 分页查询
     * @return 分账比例分页
     */
    PageResult<SharingRatioDO> getSharingRatioPage(SharingRatioPageReqVO pageReqVO);

    /**
     *
     * @param id 主键ID
     * @param i 类型
     */
    void updateSharingStatus(Long id, int i);

    /**
     *
     * @param sourceId 源文件ID
     * @return 子文件ID
     */
    Long copySharingRatio(Long sourceId);

    /**
     *
     * @param reqVO req
     * @return resp
     */
    SharingRatioSummaryRespVO getChartSummary(SharingRatioChartReqVO reqVO);

    /**
     *
     * @param reqVO req
     * @return resp
     */
    SharingRatioCooperatorRatioRespVO getCooperatorRatio(SharingRatioChartReqVO reqVO);

    /**
     *
     * @param schemeIds id
     * @return resp
     */
    SharingRatioSchemeCompareRespVO schemeCompare(List<Long> schemeIds);

    /**
     *
     * @param reqVO req
     * @return resp
     */
    SharingRatioStatusCountRespVO getStatusCount(SharingRatioChartReqVO reqVO);
}