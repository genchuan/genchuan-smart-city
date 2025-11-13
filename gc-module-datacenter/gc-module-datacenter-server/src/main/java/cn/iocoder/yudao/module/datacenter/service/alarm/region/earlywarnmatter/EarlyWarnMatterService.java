package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter.vo.EarlyWarnMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter.vo.EarlyWarnMatterSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmatter.EarlyWarnMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按管理事项预警告警统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnMatterService {

    /**
     * 创建按管理事项预警告警统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnMatter(@Valid EarlyWarnMatterSaveReqVO createReqVO);

    /**
     * 更新按管理事项预警告警统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnMatter(@Valid EarlyWarnMatterSaveReqVO updateReqVO);

    /**
     * 删除按管理事项预警告警统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnMatter(Long id);

    /**
     * 获得按管理事项预警告警统计
     *
     * @param id 编号
     * @return 按管理事项预警告警统计
     */
    EarlyWarnMatterDO getEarlyWarnMatter(Long id);

    /**
     * 获得按管理事项预警告警统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按管理事项预警告警统计分页
     */
    PageResult<EarlyWarnMatterDO> getEarlyWarnMatterPage(EarlyWarnMatterPageReqVO pageReqVO);

}