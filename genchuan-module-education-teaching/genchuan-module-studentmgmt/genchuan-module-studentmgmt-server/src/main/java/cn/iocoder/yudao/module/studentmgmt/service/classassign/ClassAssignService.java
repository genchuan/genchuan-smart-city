package cn.iocoder.yudao.module.studentmgmt.service.classassign;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.classassign.ClassAssignDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 分班管理 Service 接口
 *
 * @author 芋道源码
 */
public interface ClassAssignService {

    /**
     * 创建分班管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createClassAssign(@Valid ClassAssignSaveReqVO createReqVO);

    /**
     * 更新分班管理
     *
     * @param updateReqVO 更新信息
     */
    void updateClassAssign(@Valid ClassAssignSaveReqVO updateReqVO);

    /**
     * 删除分班管理
     *
     * @param id 编号
     */
    void deleteClassAssign(Long id);

    /**
    * 批量删除分班管理
    *
    * @param ids 编号
    */
    void deleteClassAssignListByIds(List<Long> ids);

    /**
     * 获得分班管理
     *
     * @param id 编号
     * @return 分班管理
     */
    ClassAssignDO getClassAssign(Long id);

    /**
     * 获得分班管理分页
     *
     * @param pageReqVO 分页查询
     * @return 分班管理分页
     */
    PageResult<ClassAssignDO> getClassAssignPage(ClassAssignPageReqVO pageReqVO);

    Boolean config(@Valid ClassAssignConfigReqVO reqVO);

    Boolean assign(@Valid ClassAssignAssignReqVO reqVO);

    Boolean confirm(@Valid ClassAssignConfirmReqVO reqVO);

    ClassAssignChartRespVO chart(@Valid BaseChartReqVO reqVO);

    ClassAssignDistributionRespVo classDistribution(@Valid BaseChartReqVO reqVO);
}