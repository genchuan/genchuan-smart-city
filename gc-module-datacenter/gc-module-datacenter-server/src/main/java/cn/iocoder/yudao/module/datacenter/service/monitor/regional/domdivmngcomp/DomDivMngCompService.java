package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp.vo.DomDivMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngcomp.vo.DomDivMngCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmngcomp.DomDivMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件分域 Service 接口
 *
 * @author zcq
 */
public interface DomDivMngCompService {

    /**
     * 创建管理部件分域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDivMngComp(@Valid DomDivMngCompSaveReqVO createReqVO);

    /**
     * 更新管理部件分域
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDivMngComp(@Valid DomDivMngCompSaveReqVO updateReqVO);

    /**
     * 删除管理部件分域
     *
     * @param id 编号
     */
    void deleteDomDivMngComp(Long id);

    /**
     * 获得管理部件分域
     *
     * @param id 编号
     * @return 管理部件分域
     */
    DomDivMngCompDO getDomDivMngComp(Long id);

    /**
     * 获得管理部件分域分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件分域分页
     */
    PageResult<DomDivMngCompDO> getDomDivMngCompPage(DomDivMngCompPageReqVO pageReqVO);

}