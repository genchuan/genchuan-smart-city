package cn.iocoder.yudao.module.studentmgmt.service.registermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.BaseChartReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.registermgmt.RegisterMgmtDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 报名管理 Service 接口
 *
 * @author 芋道源码
 */
public interface RegisterMgmtService {

    /**
     * 创建报名管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRegisterMgmt(@Valid RegisterMgmtSaveReqVO createReqVO);

    /**
     * 更新报名管理
     *
     * @param updateReqVO 更新信息
     */
    void updateRegisterMgmt(@Valid RegisterMgmtSaveReqVO updateReqVO);

    /**
     * 删除报名管理
     *
     * @param id 编号
     */
    void deleteRegisterMgmt(Long id);

    /**
    * 批量删除报名管理
    *
    * @param ids 编号
    */
    void deleteRegisterMgmtListByIds(List<Long> ids);

    /**
     * 获得报名管理
     *
     * @param id 编号
     * @return 报名管理
     */
    RegisterMgmtDO getRegisterMgmt(Long id);

    /**
     * 获得报名管理分页
     *
     * @param pageReqVO 分页查询
     * @return 报名管理分页
     */
    PageResult<RegisterMgmtDO> getRegisterMgmtPage(RegisterMgmtPageReqVO pageReqVO);

    Boolean audit(@Valid RegisterMgmtAuditReqVO reqVO);

    Boolean confirm(@Valid RegisterMgmtConfirmReqVO reqVO);

    RegisterMgmtChartRespVO chart(@Valid BaseChartReqVO reqVO);

    RegisterMgmtEnrollCountRespVO enrollCount(@Valid BaseChartReqVO reqVO);
}