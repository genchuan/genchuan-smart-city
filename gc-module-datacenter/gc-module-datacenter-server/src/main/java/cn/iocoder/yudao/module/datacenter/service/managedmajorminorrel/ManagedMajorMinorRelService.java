package cn.iocoder.yudao.module.datacenter.service.managedmajorminorrel;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.managedmajorminorrel.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.managedmajorminorrel.ManagedMajorMinorRelDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理部件大类小类关联 Service 接口
 *
 * @author 亘川智城
 */
public interface ManagedMajorMinorRelService {

    /**
     * 创建管理部件大类小类关联
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createManagedMajorMinorRel(@Valid ManagedMajorMinorRelSaveReqVO createReqVO);

    /**
     * 更新管理部件大类小类关联
     *
     * @param updateReqVO 更新信息
     */
    void updateManagedMajorMinorRel(@Valid ManagedMajorMinorRelSaveReqVO updateReqVO);

    /**
     * 删除管理部件大类小类关联
     *
     * @param id 编号
     */
    void deleteManagedMajorMinorRel(Long id);

    /**
     * 获得管理部件大类小类关联
     *
     * @param id 编号
     * @return 管理部件大类小类关联
     */
    ManagedMajorMinorRelDO getManagedMajorMinorRel(Long id);

    /**
     * 获得管理部件大类小类关联分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件大类小类关联分页
     */
    PageResult<ManagedMajorMinorRelDO> getManagedMajorMinorRelPage(ManagedMajorMinorRelPageReqVO pageReqVO);

}