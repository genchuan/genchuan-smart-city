package cn.iocoder.yudao.module.evaluate.service.objectscore;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.ObjectScoreCalculateRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.ObjectScorePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.ObjectScoreRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.objectscore.vo.ObjectScoreSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objectscore.ObjectScoreDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 公司得分 Service 接口
 *
 * @author 亘川智城
 */
public interface ObjectScoreService {

    /**
     * 创建公司得分
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createObjectScore(@Valid ObjectScoreSaveReqVO createReqVO);

    /**
     * 更新公司得分
     *
     * @param updateReqVO 更新信息
     */
    void updateObjectScore(@Valid ObjectScoreSaveReqVO updateReqVO);

    /**
     * 删除公司得分
     *
     * @param id 编号
     */
    void deleteObjectScore(Long id);

    /**
    * 批量删除公司得分
    *
    * @param ids 编号
    */
    void deleteObjectScoreListByIds(List<Long> ids);

    /**
     * 获得公司得分
     *
     * @param id 编号
     * @return 公司得分
     */
    ObjectScoreDO getObjectScore(Long id);

    /**
     * 获得公司得分分页（实时重新计算每条记录的得分）
     *
     * @param pageReqVO 分页查询
     * @return 公司得分分页
     */
    PageResult<ObjectScoreRespVO> getObjectScorePage(ObjectScorePageReqVO pageReqVO);

    /**
     * 根据公司得分记录ID计算公司得分
     * 先查询公司得分表获取 objectId 和 systemId，再根据统计表数据加权计算得分
     *
     * @param id 公司得分记录ID（eval_object_score 表主键）
     * @return 计算结果（含总得分和明细）
     */
    ObjectScoreCalculateRespVO calculateScore(Long id);

    /**
     * 根据 systemId 和 objectId 重新计算得分
     * 如果得分记录不存在则先创建，再计算
     * 如果已存在则直接更新 score 和 userId
     *
     * @param systemId 体系ID
     * @param objectId 对象ID
     */
    void recalculateScore(Long systemId, Long objectId);

    /**
     * 刷新公司得分表
     * 遍历统计表中所有(systemId, objectId)组合，根据最新统计表数据重新计算并同步到得分表
     *
     * @return 刷新影响的记录数
     */
    int refreshScoreTable();

}