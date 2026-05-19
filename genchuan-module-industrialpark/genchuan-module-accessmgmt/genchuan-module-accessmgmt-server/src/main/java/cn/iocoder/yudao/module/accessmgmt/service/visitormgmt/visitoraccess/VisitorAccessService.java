package cn.iocoder.yudao.module.accessmgmt.service.visitormgmt.visitoraccess;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo.*;

import java.util.List;

/**
 * 访客通行 Service 接口
 *
 * @author 亘川智城
 */
public interface VisitorAccessService {

    /**
     * 获得访客通行分页
     */
    PageResult<VisitorAccessRespVO> getVisitorAccessPage(VisitorAccessPageReqVO pageReqVO);

    /**
     * 获得访客通行
     */
    VisitorAccessRespVO getVisitorAccess(Long id);

    /**
     * 凭证核验 —— 根据预约ID查询通行记录，判断凭证是否有效
     */
    VisitorAccessCheckRespVO checkVisitorAccess(VisitorAccessCheckReqVO reqVO);

    /**
     * 放行 —— 状态变更为"已放行"
     */
    Boolean passVisitorAccess(VisitorAccessPassReqVO reqVO);

    /**
     * 禁行 —— 状态变更为"已禁行"，写入禁行原因
     */
    Boolean blockVisitorAccess(VisitorAccessBlockReqVO reqVO);

    /**
     * 提醒 —— 提醒内容写入备用字段
     */
    Boolean remindVisitorAccess(VisitorAccessRemindReqVO reqVO);

    /**
     * 获得访客通行列表（导出用，不分页）
     */
    List<VisitorAccessRespVO> getVisitorAccessList(VisitorAccessPageReqVO pageReqVO);

    /**
     * 访客通行区域分布 —— 区域通行次数 + 时间趋势 + 凭证状态分布
     */
    VisitorAccessChartRespVO getVisitorAccessChart(Long startTime, Long endTime);

}
