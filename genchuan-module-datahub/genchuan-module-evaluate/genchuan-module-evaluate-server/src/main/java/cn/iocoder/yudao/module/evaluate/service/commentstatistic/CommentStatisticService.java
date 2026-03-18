package cn.iocoder.yudao.module.evaluate.service.commentstatistic;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentstatistic.CommentStatisticDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检统计 Service 接口
 *
 * @author 亘川智城
 */
public interface CommentStatisticService {

    /**
     * 创建巡查巡检统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCommentStatistic(@Valid CommentStatisticSaveReqVO createReqVO);

    /**
     * 更新巡查巡检统计
     *
     * @param updateReqVO 更新信息
     */
    void updateCommentStatistic(@Valid CommentStatisticSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检统计
     *
     * @param id 编号
     */
    void deleteCommentStatistic(Long id);

    /**
     * 获得巡查巡检统计
     *
     * @param id 编号
     * @return 巡查巡检统计
     */
    CommentStatisticDO getCommentStatistic(Long id);

    /**
     * 获得巡查巡检统计分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检统计分页
     */
    PageResult<CommentStatisticDO> getCommentStatisticPage(CommentStatisticPageReqVO pageReqVO);

    /**
     * 根据指标项ID和评价对象ID增加统计数量
     * 如果记录不存在则创建新记录，count默认为1
     *
     * @param systemId 体系ID
     * @param itemId 指标项ID
     * @param objectId 评价对象ID
     * @param addressCoding 地址编码（可选）
     */
    void incrementCount(Long systemId, Long itemId, Long objectId, String addressCoding);

    /**
     * 根据指标项ID和评价对象ID减少统计数量
     * 如果count为0则删除该统计记录
     *
     * @param systemId 体系ID
     * @param itemId 指标项ID
     * @param objectId 评价对象ID
     */
    void decrementCount(Long systemId, Long itemId, Long objectId);

    /**
     * 同步统计数量
     * 根据systemId、itemId和objectId从巡查表重新统计数量并更新
     *
     * @param systemId 体系ID
     * @param itemId 指标项ID
     * @param objectId 评价对象ID
     * @param addressCoding 地址编码（可选）
     */
    void syncCount(Long systemId, Long itemId, Long objectId, String addressCoding);

    /**
     * 更新统计记录的 ruleId
     *
     * @param systemId 体系ID
     * @param itemId 指标项ID
     * @param objectId 评价对象ID
     * @param ruleId 规则ID
     * @param addressCoding 地址编码（可选）
     */
    void updateRuleId(Long systemId, Long itemId, Long objectId, Long ruleId, String addressCoding);

}