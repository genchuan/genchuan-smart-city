package cn.iocoder.yudao.module.industry.service.park.user.parkuser;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser.vo.ParkUserPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser.vo.ParkUserSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkuser.ParkUserDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 停车系统用户 Service 接口
 *
 * @author lxs
 */
public interface ParkUserService {

    /**
     * 创建停车系统用户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkUser(@Valid ParkUserSaveReqVO createReqVO);

    /**
     * 更新停车系统用户
     *
     * @param updateReqVO 更新信息
     */
    void updateParkUser(@Valid ParkUserSaveReqVO updateReqVO);

    /**
     * 删除停车系统用户
     *
     * @param id 编号
     */
    void deleteParkUser(Long id);

    /**
     * 获得停车系统用户
     *
     * @param id 编号
     * @return 停车系统用户
     */
    ParkUserDO getParkUser(Long id);

    /**
     * 获得停车系统用户分页
     *
     * @param pageReqVO 分页查询
     * @return 停车系统用户分页
     */
    PageResult<ParkUserDO> getParkUserPage(ParkUserPageReqVO pageReqVO);

}
