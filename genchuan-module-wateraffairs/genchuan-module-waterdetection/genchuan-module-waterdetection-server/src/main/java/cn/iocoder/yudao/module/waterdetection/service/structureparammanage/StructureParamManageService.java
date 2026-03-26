package cn.iocoder.yudao.module.waterdetection.service.structureparammanage;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.structureparammanage.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.structureparammanage.StructureParamManageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 构建筑物参数管理 Service 接口
 *
 * @author zcq
 */
public interface StructureParamManageService {

    /**
     * 创建构建筑物参数管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStructureParamManage(@Valid StructureParamManageSaveReqVO createReqVO);

    /**
     * 更新构建筑物参数管理
     *
     * @param updateReqVO 更新信息
     */
    void updateStructureParamManage(@Valid StructureParamManageSaveReqVO updateReqVO);

    /**
     * 删除构建筑物参数管理
     *
     * @param id 编号
     */
    void deleteStructureParamManage(Long id);

    /**
     * 获得构建筑物参数管理
     *
     * @param id 编号
     * @return 构建筑物参数管理
     */
    StructureParamManageDO getStructureParamManage(Long id);

    /**
     * 获得构建筑物参数管理分页
     *
     * @param pageReqVO 分页查询
     * @return 构建筑物参数管理分页
     */
    PageResult<StructureParamManageDO> getStructureParamManagePage(StructureParamManagePageReqVO pageReqVO);

}