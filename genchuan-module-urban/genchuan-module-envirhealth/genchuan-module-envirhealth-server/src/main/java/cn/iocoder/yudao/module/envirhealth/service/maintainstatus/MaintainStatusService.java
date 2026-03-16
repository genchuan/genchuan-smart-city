package cn.iocoder.yudao.module.envirhealth.service.maintainstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.maintainstatus.vo.MaintainStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.maintainstatus.vo.MaintainStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.maintainstatus.MaintainStatusDO;
import jakarta.validation.Valid;

/**
 * 维护状态字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface MaintainStatusService {

    /**
     * 创建维护状态字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaintainStatus(@Valid MaintainStatusSaveReqVO createReqVO);

    /**
     * 更新维护状态字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateMaintainStatus(@Valid MaintainStatusSaveReqVO updateReqVO);

    /**
     * 删除维护状态字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteMaintainStatus(Long id);

    /**
     * 获得维护状态字典表【通用复用】
     *
     * @param id 编号
     * @return 维护状态字典表【通用复用】
     */
    MaintainStatusDO getMaintainStatus(Long id);

    /**
     * 获得维护状态字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 维护状态字典表【通用复用】分页
     */
    PageResult<MaintainStatusDO> getMaintainStatusPage(MaintainStatusPageReqVO pageReqVO);

}