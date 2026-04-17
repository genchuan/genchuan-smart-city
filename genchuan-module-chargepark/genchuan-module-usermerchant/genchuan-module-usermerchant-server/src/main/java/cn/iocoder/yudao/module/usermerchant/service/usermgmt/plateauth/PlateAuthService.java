package cn.iocoder.yudao.module.usermerchant.service.usermgmt.plateauth;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.plateauth.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.plateauth.PlateAuthDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 车牌认证 Service 接口
 *
 * @author 亘川智城
 */
public interface PlateAuthService {

    /**
     * 获得车牌认证分页
     *
     * @param pageReqVO 分页查询
     * @return 车牌认证分页
     */
    PageResult<PlateAuthDO> getPlateAuthPage(PlateAuthPageReqVO pageReqVO);

    /**
     * 批量更新车牌认证
     *
     * @param updateReqVO 更新信息
     */
    void batchUpdatePlateAuth(@Valid PlateAuthSaveReqVO updateReqVO);

    /**
     * 创建车牌认证
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPlateAuth(@Valid PlateAuthSaveReqVO createReqVO);

    /**
     * 更新车牌认证
     *
     * @param updateReqVO 更新信息
     */
    void updatePlateAuth(@Valid PlateAuthSaveReqVO updateReqVO);

    /**
     * 删除车牌认证
     *
     * @param id 编号
     */
    void deletePlateAuth(Long id);

    /**
    * 批量删除车牌认证
    *
    * @param ids 编号
    */
    void deletePlateAuthListByIds(List<Long> ids);

    /**
     * 获得车牌认证
     *
     * @param id 编号
     * @return 车牌认证
     */
    PlateAuthDO getPlateAuth(Long id);

    /**
     * 车牌认证统计可视化
     *
     * @param chartReqVO 时间范围
     * @return 统计信息
     */
    PlateAuthChartRespVO getPlateAuthChart(@Valid PlateAuthChartReqVO chartReqVO);
}