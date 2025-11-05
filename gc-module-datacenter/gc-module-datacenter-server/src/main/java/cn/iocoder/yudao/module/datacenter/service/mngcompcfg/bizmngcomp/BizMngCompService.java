package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo.BizMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo.BizMngCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcomp.BizMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件信息管理 Service 接口
 *
 * @author 亘川智城
 */
public interface BizMngCompService {

    /**
     * 创建管理部件信息管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBizMngComp(@Valid BizMngCompSaveReqVO createReqVO);

    /**
     * 更新管理部件信息管理
     *
     * @param updateReqVO 更新信息
     */
    void updateBizMngComp(@Valid BizMngCompSaveReqVO updateReqVO);

    /**
     * 删除管理部件信息管理
     *
     * @param id 编号
     */
    void deleteBizMngComp(Long id);

    /**
     * 获得管理部件信息管理
     *
     * @param id 编号
     * @return 管理部件信息管理
     */
    BizMngCompDO getBizMngComp(Long id);

    /**
     * 获得管理部件信息管理分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件信息管理分页
     */
    PageResult<BizMngCompDO> getBizMngCompPage(BizMngCompPageReqVO pageReqVO);

}