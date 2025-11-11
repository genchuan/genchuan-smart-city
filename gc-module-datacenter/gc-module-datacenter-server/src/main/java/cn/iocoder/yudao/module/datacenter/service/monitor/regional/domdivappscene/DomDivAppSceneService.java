package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene.vo.DomDivAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivappscene.vo.DomDivAppSceneSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivappscene.DomDivAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 应用场景分域 Service 接口
 *
 * @author zcq
 */
public interface DomDivAppSceneService {

    /**
     * 创建应用场景分域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDivAppScene(@Valid DomDivAppSceneSaveReqVO createReqVO);

    /**
     * 更新应用场景分域
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDivAppScene(@Valid DomDivAppSceneSaveReqVO updateReqVO);

    /**
     * 删除应用场景分域
     *
     * @param id 编号
     */
    void deleteDomDivAppScene(Long id);

    /**
     * 获得应用场景分域
     *
     * @param id 编号
     * @return 应用场景分域
     */
    DomDivAppSceneDO getDomDivAppScene(Long id);

    /**
     * 获得应用场景分域分页
     *
     * @param pageReqVO 分页查询
     * @return 应用场景分域分页
     */
    PageResult<DomDivAppSceneDO> getDomDivAppScenePage(DomDivAppScenePageReqVO pageReqVO);

}