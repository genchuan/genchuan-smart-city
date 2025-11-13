package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarntype;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype.vo.EarlyWarnTypePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype.vo.EarlyWarnTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntype.EarlyWarnTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警类型维度统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnTypeService {

    /**
     * 创建预警告警类型维度统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnType(@Valid EarlyWarnTypeSaveReqVO createReqVO);

    /**
     * 更新预警告警类型维度统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnType(@Valid EarlyWarnTypeSaveReqVO updateReqVO);

    /**
     * 删除预警告警类型维度统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnType(Long id);

    /**
     * 获得预警告警类型维度统计
     *
     * @param id 编号
     * @return 预警告警类型维度统计
     */
    EarlyWarnTypeDO getEarlyWarnType(Long id);

    /**
     * 获得预警告警类型维度统计分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警类型维度统计分页
     */
    PageResult<EarlyWarnTypeDO> getEarlyWarnTypePage(EarlyWarnTypePageReqVO pageReqVO);

}