package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcompext;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo.BizMngCompExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo.BizMngCompExtSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompext.BizMngCompExtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件扩展管理部件配置 Service 接口
 *
 * @author 亘川智城
 */
public interface BizMngCompExtService {

    /**
     * 创建管理部件扩展管理部件配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizMngCompExt(@Valid BizMngCompExtSaveReqVO createReqVO);

    /**
     * 更新管理部件扩展管理部件配置
     *
     * @param updateReqVO 更新信息
     */
    void updateBizMngCompExt(@Valid BizMngCompExtSaveReqVO updateReqVO);

    /**
     * 删除管理部件扩展管理部件配置
     *
     * @param id 编号
     */
    void deleteBizMngCompExt(Long id);

    /**
     * 获得管理部件扩展管理部件配置
     *
     * @param id 编号
     * @return 管理部件扩展管理部件配置
     */
    BizMngCompExtDO getBizMngCompExt(Long id);

    /**
     * 获得管理部件扩展管理部件配置分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件扩展管理部件配置分页
     */
    PageResult<BizMngCompExtDO> getBizMngCompExtPage(BizMngCompExtPageReqVO pageReqVO);

}