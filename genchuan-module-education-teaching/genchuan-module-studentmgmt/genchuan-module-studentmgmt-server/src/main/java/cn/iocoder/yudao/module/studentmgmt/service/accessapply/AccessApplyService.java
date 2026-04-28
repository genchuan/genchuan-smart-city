package cn.iocoder.yudao.module.studentmgmt.service.accessapply;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.accessapply.AccessApplyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 出入申请 Service 接口
 *
 * @author 芋道源码
 */
public interface AccessApplyService {

    /**
     * 创建出入申请
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAccessApply(@Valid AccessApplySaveReqVO createReqVO);

    /**
     * 更新出入申请
     *
     * @param updateReqVO 更新信息
     */
    void updateAccessApply(@Valid AccessApplySaveReqVO updateReqVO);

    /**
     * 删除出入申请
     *
     * @param id 编号
     */
    void deleteAccessApply(Long id);

    /**
    * 批量删除出入申请
    *
    * @param ids 编号
    */
    void deleteAccessApplyListByIds(List<Long> ids);

    /**
     * 获得出入申请
     *
     * @param id 编号
     * @return 出入申请
     */
    AccessApplyDO getAccessApply(Long id);

    /**
     * 获得出入申请分页
     *
     * @param pageReqVO 分页查询
     * @return 出入申请分页
     */
    PageResult<AccessApplyDO> getAccessApplyPage(AccessApplyPageReqVO pageReqVO);

    boolean audit(@Valid AccessApplyAuditReqVO updateReqVO);

    AccessApplyChartRespVO chart(@Valid AccessApplyChartReqVO reqVO);

    AccessApplyCountRespVO applyCount(@Valid AccessApplyCountReqVO reqVO);
}