package cn.iocoder.yudao.module.smartcity.service.drainagelicense;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagelicense.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagelicense.DrainageLicenseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 排水电子许可证信息 Service 接口
 *
 * @author 超级管理员
 */
public interface DrainageLicenseService {

    /**
     * 创建排水电子许可证信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDrainageLicense(@Valid DrainageLicenseSaveReqVO createReqVO);

    /**
     * 更新排水电子许可证信息
     *
     * @param updateReqVO 更新信息
     */
    void updateDrainageLicense(@Valid DrainageLicenseSaveReqVO updateReqVO);

    /**
     * 删除排水电子许可证信息
     *
     * @param id 编号
     */
    void deleteDrainageLicense(Long id);

    /**
     * 获得排水电子许可证信息
     *
     * @param id 编号
     * @return 排水电子许可证信息
     */
    DrainageLicenseDO getDrainageLicense(Long id);

    /**
     * 获得排水电子许可证信息分页
     *
     * @param pageReqVO 分页查询
     * @return 排水电子许可证信息分页
     */
    PageResult<DrainageLicenseDO> getDrainageLicensePage(DrainageLicensePageReqVO pageReqVO);

}