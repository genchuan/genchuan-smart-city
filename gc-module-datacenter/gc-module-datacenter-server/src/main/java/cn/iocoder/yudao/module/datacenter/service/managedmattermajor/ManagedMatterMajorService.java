package cn.iocoder.yudao.module.datacenter.service.managedmattermajor;


import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.managedmattermajor.vo.ManagedMatterMajorListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.managedmattermajor.vo.ManagedMatterMajorPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.managedmattermajor.vo.ManagedMatterMajorRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.managedmattermajor.vo.ManagedMatterMajorSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.managedmattermajor.ManagedMatterMajorDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 管理事项分类表（含大类和小类） Service 接口
 *
 * @author 亘川智城
 */
public interface ManagedMatterMajorService {

    /**
     * 创建管理事项分类表（含大类和小类）
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createManagedMatterMajor(@Valid ManagedMatterMajorSaveReqVO createReqVO);

    /**
     * 更新管理事项分类表（含大类和小类）
     *
     * @param updateReqVO 更新信息
     */
    void updateManagedMatterMajor(@Valid ManagedMatterMajorSaveReqVO updateReqVO);

    /**
     * 删除管理事项分类表（含大类和小类）
     *
     * @param id 编号
     */
    void deleteManagedMatterMajor(Long id);

    /**
     * 获得管理事项分类表（含大类和小类）
     *
     * @param id 编号
     * @return 管理事项分类表（含大类和小类）
     */
    ManagedMatterMajorDO getManagedMatterMajor(Long id);

    /**
     * 获得管理事项分类表（含大类和小类）分页
     *
     * @return 管理事项分类表（含大类和小类）分页
     */
    List<ManagedMatterMajorDO> getManagedMatterMajorList(ManagedMatterMajorListReqVO ReqVO);

    /**
     * 查询
     * @param managedMatterMajorRespVO
     * @return
     */
    ManagedMatterMajorDO getManagedMatterMajor(ManagedMatterMajorPageReqVO managedMatterMajorRespVO);

}
