package cn.iocoder.yudao.module.waterdetection.service.issuetracking;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.issuetracking.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.issuetracking.IssueTrackingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 问题上报与闭环跟踪 Service 接口
 *
 * @author zcq
 */
public interface IssueTrackingService {

    /**
     * 创建问题上报与闭环跟踪
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIssueTracking(@Valid IssueTrackingSaveReqVO createReqVO);

    /**
     * 更新问题上报与闭环跟踪
     *
     * @param updateReqVO 更新信息
     */
    void updateIssueTracking(@Valid IssueTrackingSaveReqVO updateReqVO);

    /**
     * 删除问题上报与闭环跟踪
     *
     * @param id 编号
     */
    void deleteIssueTracking(Long id);

    /**
     * 获得问题上报与闭环跟踪
     *
     * @param id 编号
     * @return 问题上报与闭环跟踪
     */
    IssueTrackingDO getIssueTracking(Long id);

    /**
     * 获得问题上报与闭环跟踪分页
     *
     * @param pageReqVO 分页查询
     * @return 问题上报与闭环跟踪分页
     */
    PageResult<IssueTrackingDO> getIssueTrackingPage(IssueTrackingPageReqVO pageReqVO);

}