package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivadmin;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin.vo.DomDivAdminPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivadmin.vo.DomDivAdminSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivadmin.DomDivAdminDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 行政区划分域 Service 接口
 *
 * @author zcq
 */
public interface DomDivAdminService {

    /**
     * 创建行政区划分域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDivAdmin(@Valid DomDivAdminSaveReqVO createReqVO);

    /**
     * 更新行政区划分域
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDivAdmin(@Valid DomDivAdminSaveReqVO updateReqVO);

    /**
     * 删除行政区划分域
     *
     * @param id 编号
     */
    void deleteDomDivAdmin(Long id);

    /**
     * 获得行政区划分域
     *
     * @param id 编号
     * @return 行政区划分域
     */
    DomDivAdminDO getDomDivAdmin(Long id);

    /**
     * 获得行政区划分域分页
     *
     * @param pageReqVO 分页查询
     * @return 行政区划分域分页
     */
    PageResult<DomDivAdminDO> getDomDivAdminPage(DomDivAdminPageReqVO pageReqVO);

}