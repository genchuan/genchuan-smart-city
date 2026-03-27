package cn.iocoder.yudao.module.envirhealth.service.roadcleaning.cleaningproblem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemBatchProcessReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemPendingRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.CleaningProblemDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.CleaningProblemDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning.CleaningProblemMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.roadcleaning.CleaningProblemCodeGenerator;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.CLEANING_PROBLEM_NOT_EXISTS;

/**
 * 道路清扫问题 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CleaningProblemServiceImpl implements CleaningProblemService {

    @Resource
    private CleaningProblemMapper cleaningProblemMapper;

    @Resource
    private CleaningProblemCodeGenerator codeGenerator;

    @Override
    public Long createCleaningProblem(CleaningProblemSaveReqVO createReqVO) {
        // 插入
        CleaningProblemDO cleaningProblem = BeanUtils.toBean(createReqVO, CleaningProblemDO.class);

        cleaningProblem.setId(null);
        cleaningProblem.setProblemId(codeGenerator.generateProblemId());

        cleaningProblemMapper.insert(cleaningProblem);
        // 返回
        return cleaningProblem.getId();
    }

    @Override
    public void updateCleaningProblem(CleaningProblemSaveReqVO updateReqVO) {
        // 校验存在
        validateCleaningProblemExists(updateReqVO.getId());
        // 更新
        CleaningProblemDO updateObj = BeanUtils.toBean(updateReqVO, CleaningProblemDO.class);
        cleaningProblemMapper.updateById(updateObj);
    }

    @Override
    public void deleteCleaningProblem(Long id) {
        // 校验存在
        validateCleaningProblemExists(id);
        // 删除
        cleaningProblemMapper.deleteById(id);
    }

    private void validateCleaningProblemExists(Long id) {
        if (cleaningProblemMapper.selectById(id) == null) {
            throw exception(CLEANING_PROBLEM_NOT_EXISTS);
        }
    }

    @Override
    public CleaningProblemDO getCleaningProblem(Long id) {
        return cleaningProblemMapper.selectById(id);
    }

    @Override
    public PageResult<CleaningProblemDO> getCleaningProblemPage(CleaningProblemPageReqVO pageReqVO) {
        return cleaningProblemMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CleaningProblemDetailDO> getCleaningProblemDetailPage(CleaningProblemPageReqVO pageReqVO) {
        Long total = cleaningProblemMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<CleaningProblemDetailDO> list = cleaningProblemMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    /**
     * 批量更新状态
     */
    @Override
    public void batchUpdateCleaningProblemStatus(CleaningProblemBatchProcessReqVO batchReqVO) {

        List<Long> problemIds = batchReqVO.getIds();
        if (CollectionUtils.isEmpty(problemIds)) {
            return;
        }

        // 查询所有需要更新状态的问题
        List<CleaningProblemDO> cleaningProblems = cleaningProblemMapper.selectList(
                new LambdaQueryWrapperX<CleaningProblemDO>()
                        .in(CleaningProblemDO::getId, problemIds)
                        .eq(CleaningProblemDO::getDeleted, 0)
        );

        if (cleaningProblems.size() != problemIds.size()) {
            throw exception(CLEANING_PROBLEM_NOT_EXISTS);
        }

        // 批量更新状态
        String targetStatus = batchReqVO.getHandleStatus();

        for (CleaningProblemDO cleaningProblem : cleaningProblems) {
            // 只更新状态不同的记录
            if (!targetStatus.equals(cleaningProblem.getHandleStatus())) {
                cleaningProblem.setHandleStatus(targetStatus);
                cleaningProblem.setUpdateTime(LocalDateTime.now());
                cleaningProblemMapper.updateById(cleaningProblem);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CleaningProblemPendingRespVO getCleaningProblemPendingDashboard() {
        CleaningProblemPendingRespVO respVO = new CleaningProblemPendingRespVO();

        // 1. 统计卡片数据
        respVO.setPendingProblemCount(cleaningProblemMapper.selectPendingProblemCount());
        respVO.setHighPriorityCount(cleaningProblemMapper.selectHighPriorityCount());
        respVO.setTimeoutCount(cleaningProblemMapper.selectTimeoutCount());

        // 2. 问题类型占比
        respVO.setProblemTypeDistribution(cleaningProblemMapper.selectProblemTypeDistribution());

        // 3. 区域分布占比
        respVO.setAreaDistribution(cleaningProblemMapper.selectAreaDistribution());

        // 4. 处置组待处置问题数量对比
        respVO.setTeamPendingDistribution(cleaningProblemMapper.selectTeamPendingDistribution());

        return respVO;
    }
}