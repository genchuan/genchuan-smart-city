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

        // 创建巡查巡检后，增加评价统计数量（只有当itemId和objectId都不为null时才执行）
        if (patrolInspection.getItemId() != null && patrolInspection.getObjectId() != null) {
            commentStatisticService.incrementCount(
                    patrolInspection.getSystemId(),
                    patrolInspection.getItemId(),
                    patrolInspection.getObjectId(),
                    patrolInspection.getAddressCoding()
            );
        }

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
        Long oldSystemId = existPatrol.getSystemId();
        String oldAddressCoding = existPatrol.getAddressCoding();

        // 更新
        PatrolInspectionDO updateObj = BeanUtils.toBean(updateReqVO, PatrolInspectionDO.class);
        patrolInspectionMapper.updateById(updateObj);

        // 如果itemId或objectId发生变化，需要同步更新统计
        Long newItemId = updateReqVO.getItemId();
        Long newObjectId = updateReqVO.getObjectId();
        Long newSystemId = updateReqVO.getSystemId();
        String newAddressCoding = updateReqVO.getAddressCoding();

        // 使用 Objects.equals 处理 null 情况
        if (!Objects.equals(oldItemId, newItemId) || !Objects.equals(oldObjectId, newObjectId)) {
            // 原组合count - 1
            if (oldItemId != null && oldObjectId != null) {
                commentStatisticService.decrementCount(oldSystemId, oldItemId, oldObjectId);
            }
            // 新组合count + 1
            if (newItemId != null && newObjectId != null) {
                commentStatisticService.incrementCount(newSystemId, newItemId, newObjectId, newAddressCoding);
            }
        } else if (!Objects.equals(oldSystemId, newSystemId) || !Objects.equals(oldAddressCoding, newAddressCoding)) {
            // 如果systemId或addressCoding变化，同步统计
            if (newItemId != null && newObjectId != null) {
                commentStatisticService.syncCount(newSystemId, newItemId, newObjectId, newAddressCoding);
            }
        }
    }

    @Override
    public void deletePatrolInspection(Long id) {
        // 校验存在，获取删除前的数据用于统计
        PatrolInspectionDO existPatrol = validatePatrolInspectionExists(id);

        // 删除
        patrolInspectionMapper.deleteById(id);

        // 删除后，减少对应统计数量（只有当itemId和objectId都不为null时才执行）
        if (existPatrol.getItemId() != null && existPatrol.getObjectId() != null) {
            commentStatisticService.decrementCount(existPatrol.getSystemId(), existPatrol.getItemId(), existPatrol.getObjectId());
        }
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