package cn.iocoder.yudao.module.studentmgmt.service.workhome;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.workreport.vo.WorkReportPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.workreport.vo.WorkReportSaveReqVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.workreport.WorkReportDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 学工首页 Service 接口
 *
 * @author 芋道源码
 */
public interface WorkHomeService {
/*
    *//**
     * 创建学工首页
     *
     * @param createReqVO 创建信息
     * @return 编号
     *//*
    Long createWorkReport(@Valid WorkReportSaveReqVO createReqVO);

    *//**
     * 更新学工首页
     *
     * @param updateReqVO 更新信息
     *//*
    void updateWorkReport(@Valid WorkReportSaveReqVO updateReqVO);

    *//**
     * 删除学工首页
     *
     * @param id 编号
     *//*
    void deleteWorkReport(Long id);

    *//**
    * 批量删除学工首页
    *
    * @param ids 编号
    *//*
    void deleteWorkReportListByIds(List<Long> ids);

    *//**
     * 获得学工首页
     *
     * @param id 编号
     * @return 学工首页
     *//*
    WorkReportDO getWorkReport(Long id);

    *//**
     * 获得学工首页分页
     *
     * @param pageReqVO 分页查询
     * @return 学工首页分页
     */
    PageResult<WorkHomeRespVO> getWorkHomePage(WorkHomePageReqVO pageReqVO);


    WorkHomeChartRespVO chart(@Valid WorkHomeChartReqVO reqVO);

    List<WorkHomeDimensionCountRespVO> dimensionCount(@Valid WorkHomeChartReqVO reqVO);

    List<WorkHomeScoreAnalysisRespVO> scoreAnalysis(@Valid WorkHomeScoreAnalysisReqVO reqVO);

    List<WorkHomeCoreIndexRespVO> coreIndex(@Valid WorkHomeCoreIndexReqVO reqVO);
}