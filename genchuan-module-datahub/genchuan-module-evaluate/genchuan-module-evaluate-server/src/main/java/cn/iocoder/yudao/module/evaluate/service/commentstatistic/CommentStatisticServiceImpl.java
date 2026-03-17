package cn.iocoder.yudao.module.evaluate.service.commentstatistic;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.commentstatistic.vo.CommentStatisticSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentstatistic.CommentStatisticDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.patrolinspection.PatrolInspectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.commentstatistic.CommentStatisticMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.patrolinspection.PatrolInspectionMapper;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CommentStatisticServiceImpl implements CommentStatisticService {

    @Resource
    private CommentStatisticMapper commentStatisticMapper;

    @Resource
    private PatrolInspectionMapper patrolInspectionMapper;

    @Override
    public Long createCommentStatistic(CommentStatisticSaveReqVO createReqVO) {
        // 插入
        CommentStatisticDO commentStatistic = BeanUtils.toBean(createReqVO, CommentStatisticDO.class);
        commentStatisticMapper.insert(commentStatistic);
        // 返回
        return commentStatistic.getId();
    }

    @Override
    public void updateCommentStatistic(CommentStatisticSaveReqVO updateReqVO) {
        // 校验存在
        validateCommentStatisticExists(updateReqVO.getId());
        // 更新
        CommentStatisticDO updateObj = BeanUtils.toBean(updateReqVO, CommentStatisticDO.class);
        commentStatisticMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommentStatistic(Long id) {
        // 校验存在
        validateCommentStatisticExists(id);
        // 删除
        commentStatisticMapper.deleteById(id);
    }

    private void validateCommentStatisticExists(Long id) {
        if (commentStatisticMapper.selectById(id) == null) {
            throw exception(COMMENT_STATISTIC_NOT_EXISTS);
        }
    }

    @Override
    public CommentStatisticDO getCommentStatistic(Long id) {
        return commentStatisticMapper.selectById(id);
    }

    @Override
    public PageResult<CommentStatisticDO> getCommentStatisticPage(CommentStatisticPageReqVO pageReqVO) {
        // 先查询去重后的 item_id 和 object_id 组合（用于分页）
        List<PatrolInspectionDO> patrolList = selectPatrolGroupList(pageReqVO);

        if (patrolList.isEmpty()) {
            return PageResult.empty();
        }

        // 统计每个组合的数量，并保存到数据库
        List<CommentStatisticDO> resultList = new ArrayList<>();
        for (PatrolInspectionDO patrol : patrolList) {
            // 查询是否已存在记录
            CommentStatisticDO existRecord = commentStatisticMapper.selectOne(
                    new LambdaQueryWrapperX<CommentStatisticDO>()
                            .eq(CommentStatisticDO::getItemId, patrol.getItemId())
                            .eq(CommentStatisticDO::getObjectId, patrol.getObjectId()));

            Long count = commentStatisticMapper.selectCountByItemIdAndObjectId(patrol.getItemId(), patrol.getObjectId());
            count = count != null ? count : 0L;

            if (existRecord != null) {
                // 更新现有记录
                existRecord.setCount(count);
                existRecord.setAddressCoding(patrol.getAddressCoding());
                commentStatisticMapper.updateById(existRecord);
                resultList.add(existRecord);
            } else {
                // 创建新记录
                CommentStatisticDO statistic = new CommentStatisticDO();
                statistic.setItemId(patrol.getItemId());
                statistic.setObjectId(patrol.getObjectId());
                statistic.setAddressCoding(patrol.getAddressCoding());
                statistic.setCount(count);
                statistic.setStatus("1");
                commentStatisticMapper.insert(statistic);
                resultList.add(statistic);
            }
        }

        // 查询总数
        Long totalCount = selectPatrolGroupCount(pageReqVO);

        PageResult<CommentStatisticDO> pageResult = new PageResult<>(resultList, totalCount);
        return pageResult;
    }

    /**
     * 查询去重后的 item_id 和 object_id 组合列表（用于分页）
     */
    private List<PatrolInspectionDO> selectPatrolGroupList(CommentStatisticPageReqVO reqVO) {
        Long offset = (long) (reqVO.getPageNo() - 1) * reqVO.getPageSize();
        return patrolInspectionMapper.selectPatrolGroupList(
                reqVO.getItemId(),
                reqVO.getObjectId(),
                reqVO.getAddressCoding(),
                reqVO.getPageSize(),
                offset);
    }

    /**
     * 统计去重后的 item_id 和 object_id 组合总数
     */
    private Long selectPatrolGroupCount(CommentStatisticPageReqVO reqVO) {
        return patrolInspectionMapper.selectPatrolGroupCount(
                reqVO.getItemId(),
                reqVO.getObjectId(),
                reqVO.getAddressCoding());
    }

    @Override
    public void incrementCount(Long itemId, Long objectId, String addressCoding) {
        // 查询是否存在对应的统计记录
        CommentStatisticDO existRecord = commentStatisticMapper.selectOne(new LambdaQueryWrapperX<CommentStatisticDO>()
                .eq(CommentStatisticDO::getItemId, itemId)
                .eq(CommentStatisticDO::getObjectId, objectId));

        if (existRecord != null) {
            // 记录存在，count + 1
            CommentStatisticDO updateRecord = new CommentStatisticDO();
            updateRecord.setId(existRecord.getId());
            updateRecord.setCount(existRecord.getCount() + 1);
            commentStatisticMapper.updateById(updateRecord);
        } else {
            // 记录不存在，创建新记录，count = 1
            CommentStatisticDO newRecord = new CommentStatisticDO();
            newRecord.setItemId(itemId);
            newRecord.setObjectId(objectId);
            newRecord.setCount(1L);
            newRecord.setAddressCoding(addressCoding);
            newRecord.setStatus("1"); // 默认待审核状态
            commentStatisticMapper.insert(newRecord);
        }
    }

    @Override
    public void decrementCount(Long itemId, Long objectId) {
        // 查询是否存在对应的统计记录
        CommentStatisticDO existRecord = commentStatisticMapper.selectOne(new LambdaQueryWrapperX<CommentStatisticDO>()
                .eq(CommentStatisticDO::getItemId, itemId)
                .eq(CommentStatisticDO::getObjectId, objectId));

        if (existRecord != null) {
            if (existRecord.getCount() <= 1) {
                // count小于等于1，删除该记录
                commentStatisticMapper.deleteById(existRecord.getId());
            } else {
                // count > 1，count - 1
                CommentStatisticDO updateRecord = new CommentStatisticDO();
                updateRecord.setId(existRecord.getId());
                updateRecord.setCount(existRecord.getCount() - 1);
                commentStatisticMapper.updateById(updateRecord);
            }
        }
    }

    @Override
    public void syncCount(Long itemId, Long objectId, String addressCoding) {
        // 从巡查表重新统计数量
        Long count = commentStatisticMapper.selectCountByItemIdAndObjectId(itemId, objectId);
        count = count != null ? count : 0L;

        // 查询是否存在对应的统计记录
        CommentStatisticDO existRecord = commentStatisticMapper.selectOne(new LambdaQueryWrapperX<CommentStatisticDO>()
                .eq(CommentStatisticDO::getItemId, itemId)
                .eq(CommentStatisticDO::getObjectId, objectId));

        if (existRecord != null) {
            if (count == 0) {
                // 统计数量为0，删除该记录
                commentStatisticMapper.deleteById(existRecord.getId());
            } else {
                // 更新统计数量
                existRecord.setCount(count);
                existRecord.setAddressCoding(addressCoding);
                commentStatisticMapper.updateById(existRecord);
            }
        } else if (count > 0) {
            // 记录不存在但统计数量大于0，创建新记录
            CommentStatisticDO newRecord = new CommentStatisticDO();
            newRecord.setItemId(itemId);
            newRecord.setObjectId(objectId);
            newRecord.setCount(count);
            newRecord.setAddressCoding(addressCoding);
            newRecord.setStatus("1");
            commentStatisticMapper.insert(newRecord);
        }
    }

}