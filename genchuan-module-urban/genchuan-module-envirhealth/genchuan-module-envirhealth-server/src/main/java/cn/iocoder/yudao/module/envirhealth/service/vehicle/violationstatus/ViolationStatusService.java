package cn.iocoder.yudao.module.envirhealth.service.vehicle.violationstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationstatus.ViolationStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationstatus.ViolationStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.ViolationStatusDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 违规状态字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface ViolationStatusService {

    /**
     * 创建违规状态字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createViolationStatus(@Valid ViolationStatusSaveReqVO createReqVO);

    /**
     * 更新违规状态字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateViolationStatus(@Valid ViolationStatusSaveReqVO updateReqVO);

    /**
     * 删除违规状态字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteViolationStatus(Long id);

    /**
     * 获得违规状态字典表【通用复用】
     *
     * @param id 编号
     * @return 违规状态字典表【通用复用】
     */
    ViolationStatusDO getViolationStatus(Long id);

    /**
     * 获得违规状态字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 违规状态字典表【通用复用】分页
     */
    PageResult<ViolationStatusDO> getViolationStatusPage(ViolationStatusPageReqVO pageReqVO);

    /**
     * 获得违规状态字典表【通用复用】下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getViolationStatusOptions();
}