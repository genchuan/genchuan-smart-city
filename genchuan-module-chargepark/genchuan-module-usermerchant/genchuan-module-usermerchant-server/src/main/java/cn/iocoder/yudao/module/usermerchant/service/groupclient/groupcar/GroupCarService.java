package cn.iocoder.yudao.module.usermerchant.service.groupclient.groupcar;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.groupclient.groupcar.GroupCarDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 集团车辆 Service 接口
 *
 * @author 亘川智城
 */
public interface GroupCarService {

    /**
     * 创建集团车辆
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Boolean createGroupCar(@Valid GroupCarSaveReqVO createReqVO);

    /**
     * 更新集团车辆
     *
     * @param updateReqVO 更新信息
     */
    Boolean updateGroupCar(@Valid GroupCarUpdateReqVO updateReqVO);

    /**
     * 删除集团车辆
     *
     * @param id 编号
     */
    void deleteGroupCar(Long id);

    /**
    * 批量删除集团车辆
    *
    * @param ids 编号
    */
    void deleteGroupCarListByIds(List<Long> ids);

    /**
     * 获得集团车辆
     *
     * @param id 编号
     * @return 集团车辆
     */
    GroupCarDO getGroupCar(Long id);

    /**
     * 获得集团车辆分页
     *
     * @param pageReqVO 分页查询
     * @return 集团车辆分页
     */
    PageResult<GroupCarDO> getGroupCarPage(GroupCarPageReqVO pageReqVO);

    /**
     * 导入集团车辆
     *
     * @param list 集团车辆
     */
    Boolean importGroups(List<GroupCarImportExcelVO> list, Boolean updateSupport);

    /**
     * 批量审核，绑定集团车辆
     *
     * @param reqVO msg
     */
    void auditGroupCar(@Valid GroupCarAuditReqVO reqVO);
}