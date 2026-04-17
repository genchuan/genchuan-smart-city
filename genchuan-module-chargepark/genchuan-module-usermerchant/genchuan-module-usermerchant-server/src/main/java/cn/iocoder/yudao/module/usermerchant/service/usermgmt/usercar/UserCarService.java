package cn.iocoder.yudao.module.usermerchant.service.usermgmt.usercar;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.usercar.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.usercar.UserCarDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import jakarta.validation.constraints.NotEmpty;

/**
 * 用户车辆 Service 接口
 *
 * @author 亘川智城
 */
public interface UserCarService {

    /**
     * 创建用户车辆
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Boolean createUserCar(@Valid UserCarCreateReqVO createReqVO);

    /**
     * 更新用户车辆
     *
     * @param updateReqVO 更新信息
     */
    void updateUserCar(@Valid UserCarUpdateReqVO updateReqVO);

    /**
     * 获得用户车辆
     *
     * @param id 编号
     * @return 用户车辆
     */
    UserCarDO getUserCar(Long id);

    /**
     * 获得用户车辆分页
     *
     * @param pageReqVO 分页查询
     * @return 用户车辆分页
     */
    PageResult<UserCarDO> getUserCarPage(UserCarPageReqVO pageReqVO);

    /**
     * 导入用户车辆
     *
     * @param list,updateSupport 用户信息
     */
    Boolean importUserCar(List<UserCarImportExcelVO> list, Boolean updateSupport);

    /**
     * 审核用户车辆
     *
     * @param ids,updateSupport 用户信息
     */
    void auditUserCar(@NotEmpty(message = "用户ID列表不能为空") List<Long> ids, String remark, String status);

    /**
     * 用户车辆统计可视化
     *
     * @param chartReqVO 时间范围
     * @return 统计信息
     */
    UserCarChartRespVO getUserCarChart(@Valid UserCarChartReqVO chartReqVO);
}