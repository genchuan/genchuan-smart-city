package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnappscene.vo.EarlyWarnAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnappscene.vo.EarlyWarnAppSceneSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnappscene.EarlyWarnAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按应用场景预警告警统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnAppSceneService {

    /**
     * 创建按应用场景预警告警统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnAppScene(@Valid EarlyWarnAppSceneSaveReqVO createReqVO);

    /**
     * 更新按应用场景预警告警统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnAppScene(@Valid EarlyWarnAppSceneSaveReqVO updateReqVO);

    /**
     * 删除按应用场景预警告警统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnAppScene(Long id);

    /**
     * 获得按应用场景预警告警统计
     *
     * @param id 编号
     * @return 按应用场景预警告警统计
     */
    EarlyWarnAppSceneDO getEarlyWarnAppScene(Long id);

    /**
     * 获得按应用场景预警告警统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按应用场景预警告警统计分页
     */
    PageResult<EarlyWarnAppSceneDO> getEarlyWarnAppScenePage(EarlyWarnAppScenePageReqVO pageReqVO);

}