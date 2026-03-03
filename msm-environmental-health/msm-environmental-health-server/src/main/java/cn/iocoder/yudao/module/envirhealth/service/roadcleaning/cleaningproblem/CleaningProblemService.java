package cn.iocoder.yudao.module.envirhealth.service.roadcleaning.cleaningproblem;

import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.CleaningProblemDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.CleaningProblemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 道路清扫问题 Service 接口
 *
 * @author 芋道源码
 */
public interface CleaningProblemService {

    /**
     * 创建道路清扫问题
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCleaningProblem(@Valid CleaningProblemSaveReqVO createReqVO);

    /**
     * 更新道路清扫问题
     *
     * @param updateReqVO 更新信息
     */
    void updateCleaningProblem(@Valid CleaningProblemSaveReqVO updateReqVO);

    /**
     * 删除道路清扫问题
     *
     * @param id 编号
     */
    void deleteCleaningProblem(Long id);

    /**
     * 获得道路清扫问题
     *
     * @param id 编号
     * @return 道路清扫问题
     */
    CleaningProblemDO getCleaningProblem(Long id);

    /**
     * 获得道路清扫问题分页
     *
     * @param pageReqVO 分页查询
     * @return 道路清扫问题分页
     */
    PageResult<CleaningProblemDO> getCleaningProblemPage(CleaningProblemPageReqVO pageReqVO);

    PageResult<CleaningProblemDetailDO> getCleaningProblemDetailPage(CleaningProblemPageReqVO pageReqVO);
}