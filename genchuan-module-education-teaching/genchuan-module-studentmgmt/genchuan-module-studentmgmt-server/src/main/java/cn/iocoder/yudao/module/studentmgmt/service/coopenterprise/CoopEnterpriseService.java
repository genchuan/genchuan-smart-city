package cn.iocoder.yudao.module.studentmgmt.service.coopenterprise;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.coopenterprise.CoopEnterpriseDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 校企合作 Service 接口
 *
 * @author 芋道源码
 */
public interface CoopEnterpriseService {

    /**
     * 创建校企合作
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCoopEnterprise(@Valid CoopEnterpriseSaveReqVO createReqVO);

    /**
     * 更新校企合作
     *
     * @param updateReqVO 更新信息
     */
    void updateCoopEnterprise(@Valid CoopEnterpriseSaveReqVO updateReqVO);

    /**
     * 删除校企合作
     *
     * @param id 编号
     */
    void deleteCoopEnterprise(Long id);

    /**
    * 批量删除校企合作
    *
    * @param ids 编号
    */
    void deleteCoopEnterpriseListByIds(List<Long> ids);

    /**
     * 获得校企合作
     *
     * @param id 编号
     * @return 校企合作
     */
    CoopEnterpriseDO getCoopEnterprise(Long id);

    /**
     * 获得校企合作分页
     *
     * @param pageReqVO 分页查询
     * @return 校企合作分页
     */
    PageResult<CoopEnterpriseDO> getCoopEnterprisePage(CoopEnterprisePageReqVO pageReqVO);

    Boolean maintain(@Valid CoopEnterpriseMaintainReqVO updateReqVO);

    CoopEnterpriseChartRespVO chart(@Valid CoopEnterpriseChartReqVO updateReqVO);

    CoopEnterpriseDistributionRespVO enterpriseDistribution(@Valid BaseChartReqVO reqVO);
}