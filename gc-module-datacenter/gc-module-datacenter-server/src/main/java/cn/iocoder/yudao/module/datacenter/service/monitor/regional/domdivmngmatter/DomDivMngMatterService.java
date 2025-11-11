package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo.DomDivMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmngmatter.vo.DomDivMngMatterSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmngmatter.DomDivMngMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理事项分域 Service 接口
 *
 * @author zcq
 */
public interface DomDivMngMatterService {

    /**
     * 创建管理事项分域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDivMngMatter(@Valid DomDivMngMatterSaveReqVO createReqVO);

    /**
     * 更新管理事项分域
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDivMngMatter(@Valid DomDivMngMatterSaveReqVO updateReqVO);

    /**
     * 删除管理事项分域
     *
     * @param id 编号
     */
    void deleteDomDivMngMatter(Long id);

    /**
     * 获得管理事项分域
     *
     * @param id 编号
     * @return 管理事项分域
     */
    DomDivMngMatterDO getDomDivMngMatter(Long id);

    /**
     * 获得管理事项分域分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项分域分页
     */
    PageResult<DomDivMngMatterDO> getDomDivMngMatterPage(DomDivMngMatterPageReqVO pageReqVO);

}