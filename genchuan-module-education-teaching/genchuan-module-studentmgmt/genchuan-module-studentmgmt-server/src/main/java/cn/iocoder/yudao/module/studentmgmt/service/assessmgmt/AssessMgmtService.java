package cn.iocoder.yudao.module.studentmgmt.service.assessmgmt;

import java.util.*;

import cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo.StudentInfoCoreIndexReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.assessmgmt.AssessMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 考评管理 Service 接口
 *
 * @author 芋道源码
 */
public interface AssessMgmtService {

    /**
     * 创建考评管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssessMgmt(@Valid AssessMgmtSaveReqVO createReqVO);

    /**
     * 更新考评管理
     *
     * @param updateReqVO 更新信息
     */
    void updateAssessMgmt(@Valid AssessMgmtSaveReqVO updateReqVO);

    /**
     * 删除考评管理
     *
     * @param id 编号
     */
    void deleteAssessMgmt(Long id);

    /**
    * 批量删除考评管理
    *
    * @param ids 编号
    */
    void deleteAssessMgmtListByIds(List<Long> ids);

    /**
     * 获得考评管理
     *
     * @param id 编号
     * @return 考评管理
     */
    AssessMgmtDO getAssessMgmt(Long id);

    /**
     * 获得考评管理分页
     *
     * @param pageReqVO 分页查询
     * @return 考评管理分页
     */
    PageResult<AssessMgmtDO> getAssessMgmtPage(AssessMgmtPageReqVO pageReqVO);

    boolean publishAssessMgmt(@Valid AssessMgmtPublishReqVO publishReqVO);

    AssessMgmtChartRespVO chart(@Valid AssessMgmtChartReqVO reqVO);

    AssessMgmtTypeCountRespVO typeCount(@Valid AssessMgmtChartReqVO reqVO);

    AssessMgmtCoreIndexReqVO getCoreIndex(@Valid StudentInfoCoreIndexReqVO reqVO);
}