package cn.iocoder.yudao.module.evaluate.service.patrolinspection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo.PatrolInspectionPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo.PatrolInspectionSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.patrolinspection.PatrolInspectionDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.patrolinspection.PatrolInspectionMapper;
import cn.iocoder.yudao.module.evaluate.service.commentstatistic.CommentStatisticService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.PATROL_INSPECTION_NOT_EXISTS;

/**
 * 巡查巡检 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PatrolInspectionServiceImpl implements PatrolInspectionService {

    @Resource
    private PatrolInspectionMapper patrolInspectionMapper;

    @Resource
    private CommentStatisticService commentStatisticService;

    @Override
    public Long createPatrolInspection(PatrolInspectionSaveReqVO createReqVO) {
        // 插入
        PatrolInspectionDO patrolInspection = BeanUtils.toBean(createReqVO, PatrolInspectionDO.class);
        patrolInspectionMapper.insert(patrolInspection);

        // 创建巡查巡检后，增加评价统计数量
        commentStatisticService.incrementCount(
                patrolInspection.getItemId(),
                patrolInspection.getObjectId(),
                patrolInspection.getAddressCoding()
        );

        // 返回
        return patrolInspection.getId();
    }

    @Override
    public void updatePatrolInspection(PatrolInspectionSaveReqVO updateReqVO) {
        // 校验存在
        PatrolInspectionDO existPatrol = validatePatrolInspectionExists(updateReqVO.getId());

        // 更新前的itemId和objectId（用于统计）
        Long oldItemId = existPatrol.getItemId();
        Long oldObjectId = existPatrol.getObjectId();

        // 更新
        PatrolInspectionDO updateObj = BeanUtils.toBean(updateReqVO, PatrolInspectionDO.class);
        patrolInspectionMapper.updateById(updateObj);

        // 如果itemId或objectId发生变化，需要同步更新统计
        Long newItemId = updateReqVO.getItemId();
        Long newObjectId = updateReqVO.getObjectId();

        if (!oldItemId.equals(newItemId) || !oldObjectId.equals(newObjectId)) {
            // 原组合count - 1
            commentStatisticService.decrementCount(oldItemId, oldObjectId);
            // 新组合count + 1
            commentStatisticService.incrementCount(newItemId, newObjectId, updateReqVO.getAddressCoding());
        } else {
            // 如果itemId和objectId没变，但addressCoding变了，同步统计
            commentStatisticService.syncCount(newItemId, newObjectId, updateReqVO.getAddressCoding());
        }
    }

    @Override
    public void deletePatrolInspection(Long id) {
        // 校验存在，获取删除前的数据用于统计
        PatrolInspectionDO existPatrol = validatePatrolInspectionExists(id);

        // 删除
        patrolInspectionMapper.deleteById(id);

        // 删除后，减少对应统计数量
        commentStatisticService.decrementCount(existPatrol.getItemId(), existPatrol.getObjectId());
    }

    private PatrolInspectionDO validatePatrolInspectionExists(Long id) {
        PatrolInspectionDO patrolInspection = patrolInspectionMapper.selectById(id);
        if (patrolInspection == null) {
            throw exception(PATROL_INSPECTION_NOT_EXISTS);
        }
        return patrolInspection;
    }

    @Override
    public PatrolInspectionDO getPatrolInspection(Long id) {
        return patrolInspectionMapper.selectById(id);
    }

    @Override
    public PageResult<PatrolInspectionDO> getPatrolInspectionPage(PatrolInspectionPageReqVO pageReqVO) {
        return patrolInspectionMapper.selectPage(pageReqVO);
    }

}