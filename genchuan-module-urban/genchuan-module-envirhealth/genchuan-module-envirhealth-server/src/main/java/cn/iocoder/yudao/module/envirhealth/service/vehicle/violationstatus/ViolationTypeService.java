package cn.iocoder.yudao.module.envirhealth.service.vehicle.violationstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationtype.ViolationTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.violationtype.ViolationTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.ViolationTypeDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 违规类型字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface ViolationTypeService {

    /**
     * 创建违规类型字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createViolationType(@Valid ViolationTypeSaveReqVO createReqVO);

    /**
     * 更新违规类型字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateViolationType(@Valid ViolationTypeSaveReqVO updateReqVO);

    /**
     * 删除违规类型字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteViolationType(Long id);

    /**
     * 获得违规类型字典表【通用复用】
     *
     * @param id 编号
     * @return 违规类型字典表【通用复用】
     */
    ViolationTypeDO getViolationType(Long id);

    /**
     * 获得违规类型字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 违规类型字典表【通用复用】分页
     */
    PageResult<ViolationTypeDO> getViolationTypePage(ViolationTypePageReqVO pageReqVO);

    /**
     * 获得违规类型字典表【通用复用】下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getViolationTypeOptions();
}