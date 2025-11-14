package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo.DomDivMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmoncomp.vo.DomDivMonCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmoncomp.DomDivMonCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测部件分域 Service 接口
 *
 * @author zcq
 */
public interface DomDivMonCompService {

    /**
     * 创建监测部件分域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDivMonComp(@Valid DomDivMonCompSaveReqVO createReqVO);

    /**
     * 更新监测部件分域
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDivMonComp(@Valid DomDivMonCompSaveReqVO updateReqVO);

    /**
     * 删除监测部件分域
     *
     * @param id 编号
     */
    void deleteDomDivMonComp(Long id);

    /**
     * 获得监测部件分域
     *
     * @param id 编号
     * @return 监测部件分域
     */
    DomDivMonCompDO getDomDivMonComp(Long id);

    /**
     * 获得监测部件分域分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件分域分页
     */
    PageResult<DomDivMonCompDO> getDomDivMonCompPage(DomDivMonCompPageReqVO pageReqVO);

}