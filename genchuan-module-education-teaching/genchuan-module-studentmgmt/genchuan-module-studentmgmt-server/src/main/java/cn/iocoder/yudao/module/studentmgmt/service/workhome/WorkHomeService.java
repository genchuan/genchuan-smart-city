package cn.iocoder.yudao.module.studentmgmt.service.workhome;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo.*;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 学工首页 Service 接口
 *
 * @author 芋道源码
 */
public interface WorkHomeService {

    /**
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