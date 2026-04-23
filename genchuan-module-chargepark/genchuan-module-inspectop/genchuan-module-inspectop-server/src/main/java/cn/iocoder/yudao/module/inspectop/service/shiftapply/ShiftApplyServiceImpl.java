package cn.iocoder.yudao.module.inspectop.service.shiftapply;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.scheduleview.ScheduleViewDO;
import cn.iocoder.yudao.module.inspectop.dal.mysql.scheduleview.ScheduleViewMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.shiftapply.ShiftApplyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.shiftapply.ShiftApplyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 换班申请 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ShiftApplyServiceImpl implements ShiftApplyService {

    @Resource
    private ShiftApplyMapper shiftApplyMapper;

    @Resource
    private ScheduleViewMapper scheduleViewMapper;

    private static final Logger log = LoggerFactory.getLogger(ShiftApplyServiceImpl.class);

    @Override
    public Long createShiftApply(ShiftApplySaveReqVO createReqVO) {
        // 插入
        ShiftApplyDO shiftApply = BeanUtils.toBean(createReqVO, ShiftApplyDO.class);
        shiftApplyMapper.insert(shiftApply);

        // 返回
        return shiftApply.getId();
    }

    @Override
    public void updateShiftApply(ShiftApplySaveReqVO updateReqVO) {
        // 校验存在
        validateShiftApplyExists(updateReqVO.getId());
        // 更新
        ShiftApplyDO updateObj = BeanUtils.toBean(updateReqVO, ShiftApplyDO.class);
        shiftApplyMapper.updateById(updateObj);
    }

    @Override
    public void deleteShiftApply(Long id) {
        // 校验存在
        validateShiftApplyExists(id);
        // 删除
        shiftApplyMapper.deleteById(id);
    }

    @Override
        public void deleteShiftApplyListByIds(List<Long> ids) {
        // 删除
        shiftApplyMapper.deleteByIds(ids);
        }


    private void validateShiftApplyExists(Long id) {
        if (shiftApplyMapper.selectById(id) == null) {
            throw exception(SHIFT_APPLY_NOT_EXISTS);
        }
    }

    @Override
    public ShiftApplyDO getShiftApply(Long id) {
        return shiftApplyMapper.selectById(id);
    }

    // 在 ShiftApplyServiceImpl.java 中修改getShiftApplyPage方法
    @Override
    public PageResult<ShiftApplyRespVO> getShiftApplyPage(ShiftApplyPageReqVO pageReqVO) {
        // 创建分页对象
        Page<ShiftApplyRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用Mapper的关联查询方法
        Page<ShiftApplyRespVO> resultPage = shiftApplyMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 直接构造PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean batchAuditShiftApply(ShiftApplyBatchAuditReqVO reqVO) {
        // 1. 校验审核结果参数
        String auditResult = reqVO.getAuditResult();
        if (!"2".equals(auditResult) && !"3".equals(auditResult)) {
            throw exception("审核结果参数错误，只允许2(通过)或3(驳回)");
        }

        // 2. 获取当前登录用户ID（从安全上下文）
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();

        // 3. 批量处理申请
        List<Long> ids = reqVO.getIds();
        for (Long id : ids) {
            // 3.1 校验申请是否存在
            ShiftApplyDO shiftApply = shiftApplyMapper.selectById(id);
            if (shiftApply == null) {
                throw exception(SHIFT_APPLY_NOT_EXISTS);
            }

            // 3.2 构建更新对象
            ShiftApplyDO updateObj = new ShiftApplyDO();
            updateObj.setId(id);
            updateObj.setStatus(auditResult);  // 更新审核结果状态

            // 3.3 存储审核备注到reserve1字段
            updateObj.setReserve1(reqVO.getAuditRemark());

            // 3.4 设置审核人和审核时间
            updateObj.setAuditUserId(currentUserId);
            updateObj.setAuditTime(LocalDateTime.now());

            // 3.5 如果是审核通过(2)，设置生效时间为当前时间
            if ("2".equals(auditResult)) {
                updateObj.setEffectTime(LocalDateTime.now());
            }

            // 3.6 执行更新
            shiftApplyMapper.updateById(updateObj);

            // 3.7 如果是审核通过，需要更新原排班记录
            if ("2".equals(auditResult)) {
                updateOriginalSchedule(shiftApply);
            }
        }

        return true;
    }

    /**
     * 更新原排班记录
     * 当换班申请通过时，需要更新原排班记录的状态
     *
     * 逻辑说明：
     * 1. 通过申请人ID和原日期找到对应的排班记录
     * 2. 更新原排班记录的状态为"已换班"（假设字典值为3）
     * 3. 为换班目标用户创建新的排班记录
     */
    private void updateOriginalSchedule(ShiftApplyDO shiftApply) {
        try {
            // 1. 获取换班申请的必要信息
            Long applyUserId = shiftApply.getApplyUserId();  // 申请人ID
            Long targetUserId = shiftApply.getTargetUserId(); // 换班目标用户ID
            LocalDateTime oldDate = shiftApply.getOldDate();  // 原日期
            LocalDateTime newDate = shiftApply.getNewDate();  // 新日期

            // 2. 通过申请人ID和原日期查询原排班记录
            LambdaQueryWrapperX<ScheduleViewDO> queryWrapper = new LambdaQueryWrapperX<ScheduleViewDO>()
                    .eq(ScheduleViewDO::getUserId, applyUserId)
                    .eq(ScheduleViewDO::getScheduleDate, oldDate)
                    .eq(ScheduleViewDO::getDeleted, 0)  // 只查询未删除的记录
                    .last("LIMIT 1");

            ScheduleViewDO originalSchedule = scheduleViewMapper.selectOne(queryWrapper);

            if (originalSchedule == null) {
                log.warn("未找到原排班记录，申请ID：{}，申请人ID：{}，原日期：{}",
                        shiftApply.getId(), applyUserId, oldDate);
                return;  // 如果没有找到原排班记录，直接返回
            }

            // 3. 更新原排班记录状态为"已换班"（假设字典值为3）
            ScheduleViewDO updateSchedule = new ScheduleViewDO();
            updateSchedule.setId(originalSchedule.getId());
            updateSchedule.setStatus("2");  // 状态：2-已换班

            // 设置更新人和更新时间
            updateSchedule.setUpdater(String.valueOf(getCurrentUserName()));
            updateSchedule.setUpdateTime(LocalDateTime.now());

            scheduleViewMapper.updateById(updateSchedule);
            log.info("已更新原排班记录状态为已换班，排班ID：{}，申请ID：{}",
                    originalSchedule.getId(), shiftApply.getId());

            // 4. 为目标用户创建新的排班记录
            createNewScheduleForTargetUser(originalSchedule, targetUserId, newDate, shiftApply);

        } catch (Exception e) {
            log.error("更新原排班记录失败，申请ID：{}", shiftApply.getId(), e);
            // 根据业务需求决定是否抛出异常
            // 如果这里抛出异常，会导致整个事务回滚
            // 如果只是记录日志，则事务会继续
            throw new ServiceException(500,"更新原排班记录失败，申请ID：" + shiftApply.getId());
        }
    }

    /**
     * 为目标用户创建新的排班记录
     *
     * @param originalSchedule 原排班记录
     * @param targetUserId 目标用户ID
     * @param newDate 新日期
     * @param shiftApply 换班申请记录
     */
    private void createNewScheduleForTargetUser(ScheduleViewDO originalSchedule,
                                                Long targetUserId,
                                                LocalDateTime newDate,
                                                ShiftApplyDO shiftApply) {
        // 1. 检查目标用户在新日期是否已有排班
        LambdaQueryWrapperX<ScheduleViewDO> checkWrapper = new LambdaQueryWrapperX<ScheduleViewDO>()
                .eq(ScheduleViewDO::getUserId, targetUserId)
                .eq(ScheduleViewDO::getScheduleDate, newDate)
                .eq(ScheduleViewDO::getDeleted, 0);

        Long existingSchedule = scheduleViewMapper.selectCount(checkWrapper);

        if (existingSchedule > 0) {
            log.warn("目标用户在新日期已有排班，目标用户ID：{}，新日期：{}，申请ID：{}",
                    targetUserId, newDate, shiftApply.getId());
            // 可以选择跳过创建，或者更新现有记录
            // 这里我们选择记录日志并跳过
            return;
        }

        // 2. 创建新的排班记录
        ScheduleViewDO newSchedule = new ScheduleViewDO();

        // 复制原排班记录的大部分信息
        newSchedule.setUserId(targetUserId);  // 用户ID改为目标用户
        newSchedule.setScheduleDate(newDate); // 日期改为新日期
        newSchedule.setShiftType(originalSchedule.getShiftType()); // 班次类型保持不变
        newSchedule.setStatus("1");  // 状态：1-正常（或根据实际业务设置）

        // 复制备用字段
        newSchedule.setReserve1(originalSchedule.getReserve1());
        newSchedule.setReserve2(originalSchedule.getReserve2());

        // 设置创建信息
        newSchedule.setCreator(String.valueOf(getCurrentUserName()));
        newSchedule.setCreateTime(LocalDateTime.now());
        newSchedule.setUpdater(String.valueOf(getCurrentUserName()));
        newSchedule.setUpdateTime(LocalDateTime.now());

        // 3. 插入新的排班记录
        scheduleViewMapper.insert(newSchedule);

        log.info("已为目标用户创建新的排班记录，目标用户ID：{}，新日期：{}，新排班ID：{}，申请ID：{}",
                targetUserId, newDate, newSchedule.getId(), shiftApply.getId());
    }

    /**
     * 获取当前登录用户的用户名
     * 需要根据实际项目的安全框架实现
     */
    private Long getCurrentUserName() {
        // 这里需要根据实际项目的安全框架获取当前用户名
        return SecurityFrameworkUtils.getLoginUserId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean approveShiftApply(ShiftApplyApproveReqVO reqVO) {
        // 1. 获取申请ID和审核备注
        Long id = reqVO.getId();
        String auditRemark = reqVO.getAuditRemark();

        // 2. 查询换班申请记录
        ShiftApplyDO shiftApply = shiftApplyMapper.selectById(id);
        if (shiftApply == null) {
            throw exception(SHIFT_APPLY_NOT_EXISTS);
        }

        // 3. 校验当前状态，避免重复操作
        if ("2".equals(shiftApply.getStatus())) {
            throw new ServiceException(500,"换班申请已通过，请勿重复操作");
        }
        if ("3".equals(shiftApply.getStatus())) {
            throw new ServiceException(500,"换班申请已驳回，无法通过");
        }

        // 4. 获取当前用户ID
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();

        // 5. 构建更新对象
        ShiftApplyDO updateObj = new ShiftApplyDO();
        updateObj.setId(id);
        updateObj.setStatus("2");  // 状态：2-已通过

        // 6. 存储审核备注到reserve1字段
        updateObj.setReserve1(auditRemark);

        // 7. 设置审核人和审核时间
        updateObj.setAuditUserId(currentUserId);
        updateObj.setAuditTime(LocalDateTime.now());

        // 8. 执行更新
        int updateCount = shiftApplyMapper.updateById(updateObj);

        // 9. 记录操作日志
        log.info("换班申请已通过，申请ID：{}，审核人：{}，备注：{}",
                id, currentUserId, auditRemark);

        return updateCount > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean rejectShiftApply(ShiftApplyRejectReqVO reqVO) {
        // 1. 获取申请ID和驳回理由
        Long id = reqVO.getId();
        String auditRemark = reqVO.getAuditRemark();

        // 2. 查询换班申请记录
        ShiftApplyDO shiftApply = shiftApplyMapper.selectById(id);
        if (shiftApply == null) {
            throw exception(SHIFT_APPLY_NOT_EXISTS);
        }

        // 3. 校验当前状态，避免重复操作
        if ("3".equals(shiftApply.getStatus())) {
            throw new ServiceException(500,"换班申请已驳回，请勿重复操作");
        }
        if ("2".equals(shiftApply.getStatus())) {
            throw new ServiceException(500,"换班申请已通过，无法驳回");
        }

        // 4. 获取当前用户ID
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();

        // 5. 构建更新对象
        ShiftApplyDO updateObj = new ShiftApplyDO();
        updateObj.setId(id);
        updateObj.setStatus("3");  // 状态：3-已驳回

        // 6. 存储驳回理由到reserve1字段
        updateObj.setReserve1(auditRemark);

        // 7. 设置审核人和审核时间
        updateObj.setAuditUserId(currentUserId);
        updateObj.setAuditTime(LocalDateTime.now());

        // 8. 执行更新
        int updateCount = shiftApplyMapper.updateById(updateObj);

        // 9. 记录操作日志
        log.info("换班申请已驳回，申请ID：{}，审核人：{}，驳回理由：{}",
                id, currentUserId, auditRemark);

        return updateCount > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmShiftApply(ShiftApplyConfirmReqVO reqVO) {
        // 1. 获取申请ID
        Long id = reqVO.getId();

        // 2. 查询换班申请记录
        ShiftApplyDO shiftApply = shiftApplyMapper.selectById(id);
        if (shiftApply == null) {
            throw exception(SHIFT_APPLY_NOT_EXISTS);
        }

        // 3. 校验当前状态，只有已通过的申请才能确认
        if (!"2".equals(shiftApply.getStatus())) {
            throw new ServiceException(500,"只有已通过的换班申请才能确认");
        }

        // 4. 获取当前用户ID
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();

        // 5. 构建更新对象
        ShiftApplyDO updateObj = new ShiftApplyDO();
        updateObj.setId(id);

        // 6. 设置生效时间
        updateObj.setEffectTime(LocalDateTime.now());

        // 7. 设置更新人和更新时间
        updateObj.setUpdater(String.valueOf(getCurrentUserName()));
        updateObj.setUpdateTime(LocalDateTime.now());

        // 8. 执行更新
        int updateCount = shiftApplyMapper.updateById(updateObj);

        // 9. 更新原排班记录（换班生效）
        updateOriginalSchedule(shiftApply);

        // 10. 记录操作日志
        log.info("换班申请已确认生效，申请ID：{}，确认人：{}，生效时间：{}",
                id, currentUserId, LocalDateTime.now());

        return updateCount > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean reapplyShiftApply(ShiftApplyReapplyReqVO reqVO) {
        // 1. 获取原申请ID和新申请备注
        Long originalId = reqVO.getId();
        String newRemark = reqVO.getNewRemark();

        // 2. 查询原换班申请记录
        ShiftApplyDO originalApply = shiftApplyMapper.selectById(originalId);
        if (originalApply == null) {
            throw exception(SHIFT_APPLY_NOT_EXISTS);
        }

        // 3. 校验原申请状态，只有被驳回的申请才能重新申请
        if (!"3".equals(originalApply.getStatus())) {
            throw new ServiceException(500,"只有被驳回的换班申请才能重新申请");
        }

        // 4. 获取当前用户ID
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();

        // 5. 创建新的换班申请记录
        ShiftApplyDO newApply = new ShiftApplyDO();

        // 复制原申请的基本信息
        newApply.setApplyUserId(originalApply.getApplyUserId());
        newApply.setTargetUserId(originalApply.getTargetUserId());
        newApply.setOldDate(originalApply.getOldDate());
        newApply.setNewDate(originalApply.getNewDate());

        // 设置新申请的状态和备注
        newApply.setStatus("1");  // 状态：1-待审核
        newApply.setReserve1(newRemark);  // 新申请备注

        // 设置创建信息
        newApply.setCreator(String.valueOf(getCurrentUserName()));
        newApply.setCreateTime(LocalDateTime.now());
        newApply.setUpdater(String.valueOf(getCurrentUserName()));
        newApply.setUpdateTime(LocalDateTime.now());

        // 6. 插入新的申请记录
        shiftApplyMapper.insert(newApply);

        // 7. 更新原申请状态为已重新申请（状态4）
        ShiftApplyDO updateOriginal = new ShiftApplyDO();
        updateOriginal.setId(originalId);
        updateOriginal.setStatus("4");  // 状态：4-已重新申请
        updateOriginal.setUpdater(String.valueOf(getCurrentUserName()));
        updateOriginal.setUpdateTime(LocalDateTime.now());

        shiftApplyMapper.updateById(updateOriginal);

        // 8. 记录操作日志
        log.info("换班申请重新申请，原申请ID：{}，新申请ID：{}，申请人：{}，备注：{}",
                originalId, newApply.getId(), currentUserId, newRemark);

        return true;
    }

    @Override
    public ShiftApplyChartRespVO getShiftApplyChart(ShiftApplyChartReqVO reqVO) {
        ShiftApplyChartRespVO respVO = new ShiftApplyChartRespVO();

        // 获取时间范围
        LocalDateTime startTime = reqVO.getStartTime();
        LocalDateTime endTime = reqVO.getEndTime();

        // 1. 查询趋势统计数据
        List<Map<String, Object>> trendDataList = shiftApplyMapper.selectTrendStatistics(startTime, endTime);
        List<ShiftApplyChartRespVO.TrendData> trendData = new ArrayList<>();

        for (Map<String, Object> item : trendDataList) {
            ShiftApplyChartRespVO.TrendData trendItem = new ShiftApplyChartRespVO.TrendData();
            trendItem.setTime(item.get("time").toString());  // 格式：yyyy-MM-dd
            trendItem.setApplyCount(((Number) item.get("applyCount")).intValue());
            trendData.add(trendItem);
        }
        respVO.setTrendData(trendData);

        // 2. 查询卡片统计数据
        Map<String, Object> cardStats = shiftApplyMapper.selectCardStatistics(startTime, endTime);
        ShiftApplyChartRespVO.CardData cardData = new ShiftApplyChartRespVO.CardData();

        if (cardStats != null && !cardStats.isEmpty()) {
            Integer totalCount = ((Number) cardStats.get("totalCount")).intValue();
            Integer passedCount = cardStats.get("passedCount") != null ?
                    ((Number) cardStats.get("passedCount")).intValue() : 0;

            cardData.setApplyCount(totalCount);
            cardData.setAuditPassRate(passedCount, totalCount);
        } else {
            // 如果没有数据，设置默认值
            cardData.setApplyCount(0);
            cardData.setAuditPassRate(0, 0);
        }
        respVO.setCardData(cardData);

        return respVO;
    }

}