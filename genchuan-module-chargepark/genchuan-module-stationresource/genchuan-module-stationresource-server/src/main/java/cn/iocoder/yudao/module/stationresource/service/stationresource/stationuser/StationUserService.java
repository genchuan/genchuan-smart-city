package cn.iocoder.yudao.module.stationresource.service.stationresource.stationuser;

import java.util.*;

import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationuser.vo.StationUserSaveReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationuser.StationUserDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 站点用户 Service 接口
 *
 * @author 亘川智城
 */
public interface StationUserService {

    /**
     * 创建站点用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStationUser(@Valid StationUserSaveReqVO createReqVO);

    /**
     * 更新站点用户
     *
     * @param updateReqVO 更新信息
     */
    void updateStationUser(@Valid StationUserSaveReqVO updateReqVO);

    /**
     * 删除站点用户
     *
     * @param id 编号
     */
    void deleteStationUser(Long id);

    /**
    * 批量删除站点用户
    *
    * @param ids 编号
    */
    void deleteStationUserListByIds(List<Long> ids);

    /**
     * 获得站点用户
     *
     * @param id 编号
     * @return 站点用户
     */
    StationUserDO getStationUser(Long id);

    /**
     * 获得站点用户分页
     *
     * @param pageReqVO 分页查询
     * @return 站点用户分页
     */
    PageResult<StationUserRespVO> getStationUserPage(StationUserPageReqVO pageReqVO);

}
