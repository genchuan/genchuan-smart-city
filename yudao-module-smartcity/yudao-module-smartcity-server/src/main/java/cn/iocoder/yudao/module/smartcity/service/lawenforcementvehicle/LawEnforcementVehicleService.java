package cn.iocoder.yudao.module.smartcity.service.lawenforcementvehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo.LawEnforcementVehiclePageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo.LawEnforcementVehicleSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementvehicle.LawEnforcementVehicleDO;
import jakarta.validation.Valid;

/**
 * 执法车辆管理 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface LawEnforcementVehicleService {

    /**
     * 创建执法车辆管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLawEnforcementVehicle(@Valid LawEnforcementVehicleSaveReqVO createReqVO);

    /**
     * 更新执法车辆管理
     *
     * @param updateReqVO 更新信息
     */
    void updateLawEnforcementVehicle(@Valid LawEnforcementVehicleSaveReqVO updateReqVO);

    /**
     * 删除执法车辆管理
     *
     * @param id 编号
     */
    void deleteLawEnforcementVehicle(Long id);

    /**
     * 获得执法车辆管理
     *
     * @param id 编号
     * @return 执法车辆管理
     */
    LawEnforcementVehicleDO getLawEnforcementVehicle(Long id);

    /**
     * 获得执法车辆管理分页
     *
     * @param pageReqVO 分页查询
     * @return 执法车辆管理分页
     */
    PageResult<LawEnforcementVehicleDO> getLawEnforcementVehiclePage(LawEnforcementVehiclePageReqVO pageReqVO);

}