package cn.iocoder.yudao.module.waterdetection.service.userbasicinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.userbasicinfo.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.userbasicinfo.UserBasicInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 用户基础信息登记 Service 接口
 *
 * @author zcq
 */
public interface UserBasicInfoService {

    /**
     * 创建用户基础信息登记
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserBasicInfo(@Valid UserBasicInfoSaveReqVO createReqVO);

    /**
     * 更新用户基础信息登记
     *
     * @param updateReqVO 更新信息
     */
    void updateUserBasicInfo(@Valid UserBasicInfoSaveReqVO updateReqVO);

    /**
     * 删除用户基础信息登记
     *
     * @param id 编号
     */
    void deleteUserBasicInfo(Long id);

    /**
     * 获得用户基础信息登记
     *
     * @param id 编号
     * @return 用户基础信息登记
     */
    UserBasicInfoDO getUserBasicInfo(Long id);

    /**
     * 获得用户基础信息登记分页
     *
     * @param pageReqVO 分页查询
     * @return 用户基础信息登记分页
     */
    PageResult<UserBasicInfoDO> getUserBasicInfoPage(UserBasicInfoPageReqVO pageReqVO);

}