package cn.iocoder.yudao.module.studentmgmt.service.aidwork;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.aidwork.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.aidwork.AidWorkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 奖助勤贷 Service 接口
 *
 * @author 芋道源码
 */
public interface AidWorkService {

    /**
     * 创建奖助勤贷
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAidWork(@Valid AidWorkSaveReqVO createReqVO);

    /**
     * 更新奖助勤贷
     *
     * @param updateReqVO 更新信息
     */
    void updateAidWork(@Valid AidWorkSaveReqVO updateReqVO);

    /**
     * 删除奖助勤贷
     *
     * @param id 编号
     */
    void deleteAidWork(Long id);

    /**
    * 批量删除奖助勤贷
    *
    * @param ids 编号
    */
    void deleteAidWorkListByIds(List<Long> ids);

    /**
     * 获得奖助勤贷
     *
     * @param id 编号
     * @return 奖助勤贷
     */
    AidWorkDO getAidWork(Long id);

    /**
     * 获得奖助勤贷分页
     *
     * @param pageReqVO 分页查询
     * @return 奖助勤贷分页
     */
    PageResult<AidWorkDO> getAidWorkPage(AidWorkPageReqVO pageReqVO);

    boolean audit(@Valid AidWorkAuditReqVO reqVO);

    boolean follow(@Valid AidWorkFollowReqVO reqVO);

    AidWorkChartRespVO chart(@Valid AidWorkChartReqVO reqVO);

    List<AidWorkApplyCountRespVO> applyCount(@Valid AidWorkApplyCountReqVO reqVO);
}