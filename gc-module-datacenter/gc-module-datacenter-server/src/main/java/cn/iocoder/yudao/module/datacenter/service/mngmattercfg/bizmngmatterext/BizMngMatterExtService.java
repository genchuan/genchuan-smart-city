package cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmatterext;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo.BizMngMatterExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo.BizMngMatterExtSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatterext.BizMngMatterExtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理事项扩展管理事项配置 Service 接口
 *
 * @author 亘川智城
 */
public interface BizMngMatterExtService {

    /**
     * 创建管理事项扩展管理事项配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizMngMatterExt(@Valid BizMngMatterExtSaveReqVO createReqVO);

    /**
     * 更新管理事项扩展管理事项配置
     *
     * @param updateReqVO 更新信息
     */
    void updateBizMngMatterExt(@Valid BizMngMatterExtSaveReqVO updateReqVO);

    /**
     * 删除管理事项扩展管理事项配置
     *
     * @param id 编号
     */
    void deleteBizMngMatterExt(Long id);

    /**
     * 获得管理事项扩展管理事项配置
     *
     * @param id 编号
     * @return 管理事项扩展管理事项配置
     */
    BizMngMatterExtDO getBizMngMatterExt(Long id);

    /**
     * 获得管理事项扩展管理事项配置分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项扩展管理事项配置分页
     */
    PageResult<BizMngMatterExtDO> getBizMngMatterExtPage(BizMngMatterExtPageReqVO pageReqVO);

}