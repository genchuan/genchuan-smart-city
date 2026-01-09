package cn.iocoder.yudao.module.smartcity.service.maintenancepersonnel;

import java.util.*;
import jakarta.validation.Valid;
import cn.iocoder.yudao.module.smartcity.controller.admin.maintenancepersonnel.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancepersonnel.MaintenancePersonnelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 养护人员 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface MaintenancePersonnelService {

    /**
     * 创建养护人员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaintenancePersonnel(@Valid MaintenancePersonnelSaveReqVO createReqVO);

    /**
     * 更新养护人员
     *
     * @param updateReqVO 更新信息
     */
    void updateMaintenancePersonnel(@Valid MaintenancePersonnelSaveReqVO updateReqVO);

    /**
     * 删除养护人员
     *
     * @param id 编号
     */
    void deleteMaintenancePersonnel(Long id);

    /**
     * 获得养护人员
     *
     * @param id 编号
     * @return 养护人员
     */
    MaintenancePersonnelDO getMaintenancePersonnel(Long id);

    /**
     * 获得养护人员分页
     *
     * @param pageReqVO 分页查询
     * @return 养护人员分页
     */
    PageResult<MaintenancePersonnelDO> getMaintenancePersonnelPage(MaintenancePersonnelPageReqVO pageReqVO);

}