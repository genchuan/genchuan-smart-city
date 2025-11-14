package cn.iocoder.yudao.module.datacenter.service.schemesimcompare;


import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.schemesimcompare.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.schemesimcompare.SchemeSimCompareDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


/**
 * 方案模拟对比统计 Service 接口
 *
 * @author 亘川智城
 */
public interface SchemeSimCompareService {

    /**
     * 创建方案模拟对比统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSchemeSimCompare(@Valid SchemeSimCompareSaveReqVO createReqVO);

    /**
     * 更新方案模拟对比统计
     *
     * @param updateReqVO 更新信息
     */
    void updateSchemeSimCompare(@Valid SchemeSimCompareSaveReqVO updateReqVO);

    /**
     * 删除方案模拟对比统计
     *
     * @param id 编号
     */
    void deleteSchemeSimCompare(Long id);

    /**
     * 获得方案模拟对比统计
     *
     * @param id 编号
     * @return 方案模拟对比统计
     */
    SchemeSimCompareDO getSchemeSimCompare(Long id);

    /**
     * 获得方案模拟对比统计分页
     *
     * @param pageReqVO 分页查询
     * @return 方案模拟对比统计分页
     */
    PageResult<SchemeSimCompareDO> getSchemeSimComparePage(SchemeSimComparePageReqVO pageReqVO);

}