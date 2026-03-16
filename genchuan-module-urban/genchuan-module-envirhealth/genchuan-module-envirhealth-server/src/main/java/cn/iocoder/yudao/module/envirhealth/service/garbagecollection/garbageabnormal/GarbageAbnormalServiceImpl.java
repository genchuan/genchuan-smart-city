package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbageabnormal;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageAbnormalDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageAbnormalMapper;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.garbagecollection.GarbageAbnormalCodeGenerator;
import jakarta.annotation.Resource;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 垃圾异常记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GarbageAbnormalServiceImpl implements GarbageAbnormalService {

    @Resource
    private GarbageAbnormalMapper garbageAbnormalMapper;
    @Resource
    private GarbageAbnormalCodeGenerator codeGenerator;
    @Resource
    private Validator validator;

    @Override
    public Long createGarbageAbnormal(GarbageAbnormalSaveReqVO createReqVO) {
        // 插入
        GarbageAbnormalDO garbageAbnormal = BeanUtils.toBean(createReqVO, GarbageAbnormalDO.class);

        garbageAbnormal.setAbnormalId(codeGenerator.generateAbnormalId());

        garbageAbnormalMapper.insert(garbageAbnormal);
        // 返回
        return garbageAbnormal.getId();
    }

    @Override
    public void updateGarbageAbnormal(GarbageAbnormalSaveReqVO updateReqVO) {
        // 校验存在
        validateGarbageAbnormalExists(updateReqVO.getId());

        // 更新
        GarbageAbnormalDO updateObj = BeanUtils.toBean(updateReqVO, GarbageAbnormalDO.class);
        garbageAbnormalMapper.updateById(updateObj);
    }

    @Override
    public void deleteGarbageAbnormal(Long id) {
        // 校验存在
        validateGarbageAbnormalExists(id);
        // 删除
        garbageAbnormalMapper.deleteById(id);
    }

    @Override
    public void deleteGarbageAbnormalBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 校验所有计划是否存在
        List<GarbageAbnormalDO> garbageAbnormals = garbageAbnormalMapper.selectBatchIds(ids);
        if (garbageAbnormals.size() != ids.size()) {
            throw exception(GARBAGE_ABNORMAL_NOT_EXISTS);
        }

        // 批量删除
        garbageAbnormalMapper.deleteBatchIds(ids);
    }

    private void validateGarbageAbnormalExists(Long id) {
        if (garbageAbnormalMapper.selectById(id) == null) {
            throw exception(GARBAGE_ABNORMAL_NOT_EXISTS);
        }
    }

    @Override
    public GarbageAbnormalDO getGarbageAbnormal(Long id) {
        return garbageAbnormalMapper.selectById(id);
    }

    @Override
    public PageResult<GarbageAbnormalDO> getGarbageAbnormalPage(GarbageAbnormalPageReqVO pageReqVO) {
        return garbageAbnormalMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<GarbageAbnormalDetailDO> getGarbageAbnormalDetailPage(GarbageAbnormalPageReqVO pageReqVO) {
        Long total = garbageAbnormalMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<GarbageAbnormalDetailDO> list = garbageAbnormalMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public GarbageAbnormalCardRespVO getGarbageAbnormalCardAbnormal() {
        GarbageAbnormalCardRespVO statistics = new GarbageAbnormalCardRespVO();

        // 1. 待处置异常总数（handle_status = 待处置）
        GarbageAbnormalPageReqVO toHandleReq = new GarbageAbnormalPageReqVO();
        toHandleReq.setHandleStatus("待处置");
        statistics.setToHandleTotal(garbageAbnormalMapper.selectCount(toHandleReq));

        // 2. 高优先级数（priority = 高 且 handle_status = 待处置）
        GarbageAbnormalPageReqVO highPriorityReq = new GarbageAbnormalPageReqVO();
        highPriorityReq.setPriority("高");
        highPriorityReq.setHandleStatus("待处置"); // 添加待处置状态条件
        statistics.setHighPriorityTotal(garbageAbnormalMapper.selectCount(highPriorityReq));

        // 3. 超时未处理数（is_timeout = 是 且 handle_status = 待处置）
        GarbageAbnormalPageReqVO timeoutReq = new GarbageAbnormalPageReqVO();
        timeoutReq.setIsTimeout("是");
        timeoutReq.setHandleStatus("待处置"); // 添加待处置状态条件
        statistics.setTimeoutTotal(garbageAbnormalMapper.selectCount(timeoutReq));

        return statistics;
    }

    @Override
    public List<GarbageAbnormalCircleAbnormalVO> getGarbageAbnormalTypeCircleAbnormal() {
        return garbageAbnormalMapper.selectAbnormalTypeCircle();
    }

    @Override
    public List<GarbageAbnormalCircleAbnormalVO> getGarbageAbnormalAreaDistributionCircle() {
        return garbageAbnormalMapper.selectAreaDistributionCircle();
    }

    @Override
    public List<GarbageAbnormalColumnAbnormalVO> getHandlerAbnormalColumn() {
        return garbageAbnormalMapper.selectHandlerAbnormalColumn();
    }

    @Override
    public GarbageAbnormalCardReviewRespVO getGarbageAbnormalCardReview() {
        return garbageAbnormalMapper.selectCardReview();
    }

    @Override
    public List<GarbageAbnormalCircleReviewVO> getGarbageAbnormalReviewResultCircle() {
        return garbageAbnormalMapper.selectReviewResultCircle();
    }

    @Override
    public List<GarbageAbnormalCircleAbnormalVO> getGarbageAbnormalTypeCircleForReview() {
        return garbageAbnormalMapper.selectAbnormalTypeCircleForReview();
    }

    @Override
    public List<GarbageAbnormalColumnHandleTimeVO> getAvgHandleTimeColumn() {
        return garbageAbnormalMapper.selectAvgHandleTimeByArea();
    }

    @Override
    public void batchReviewGarbageAbnormal(GarbageAbnormalBatchReviewReqVO batchReviewReqVO) {
        // 1. 校验参数
        List<Long> ids = batchReviewReqVO.getIds();
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        if (batchReviewReqVO.getReviewStatus() == null) {
            throw exception(GARBAGE_ABNORMAL_REVIEW_STATUS_EMPTY);
        }

        // 2. 查询并校验所有待复核的异常记录
        List<GarbageAbnormalDO> abnormalList = garbageAbnormalMapper.selectBatchIds(ids);
        if (abnormalList.size() != ids.size()) {
            throw exception(GARBAGE_ABNORMAL_NOT_EXISTS);
        }

        // 3. 校验状态：必须都是"待复核"
        for (GarbageAbnormalDO abnormal : abnormalList) {
            if (!"待复核".equals(abnormal.getHandleStatus())) {
                throw exception(GARBAGE_ABNORMAL_REVIEW_STATUS_ERROR, abnormal.getAbnormalId());
            }
        }

        // 4. 获取当前复核人信息
        Long currentReviewerId = SecurityFrameworkUtils.getLoginUserId();
        String currentReviewer = currentReviewerId != null ? currentReviewerId.toString() : null;
        LocalDateTime now = LocalDateTime.now();

        // 5. 构建批量更新的DO列表
        List<GarbageAbnormalDO> updateList = new ArrayList<>();
        for (GarbageAbnormalDO abnormal : abnormalList) {
            GarbageAbnormalDO updateObj = new GarbageAbnormalDO();
            updateObj.setId(abnormal.getId());
            updateObj.setReviewStatus(batchReviewReqVO.getReviewStatus());
            updateObj.setReviewBy(currentReviewer);
            updateObj.setReviewDesc(batchReviewReqVO.getReviewDesc());
            updateObj.setReviewTime(now);

            // 根据复核结果更新处理状态
            if ("通过".equals(batchReviewReqVO.getReviewStatus())) {
                updateObj.setHandleStatus("已办结");
            }else if("退回".equals(batchReviewReqVO.getReviewStatus())) {
                updateObj.setHandleStatus("待处置");
            }

            updateList.add(updateObj);
        }

        // 6. 调用BaseMapperX提供的批量更新方法
        boolean updateResult = garbageAbnormalMapper.updateBatch(updateList);
        if (!updateResult) {
            throw exception(GARBAGE_ABNORMAL_REVIEW_BATCH_UPDATE_FAILED);
        }
    }

    @Override
    public void batchUpdateHandleStatus(GarbageAbnormalBatchHandleReqVO batchHandleReqVO) {
        List<Long> ids = batchHandleReqVO.getIds();
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 1. 查询所有记录
        List<GarbageAbnormalDO> abnormalList = garbageAbnormalMapper.selectBatchIds(ids);
        if (abnormalList.size() != ids.size()) {
            throw exception(GARBAGE_ABNORMAL_NOT_EXISTS);
        }

        // 2. 构建批量更新的DO列表
        List<GarbageAbnormalDO> updateList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();
        String currentUser = currentUserId != null ? currentUserId.toString() : null;

        for (GarbageAbnormalDO abnormal : abnormalList) {
            GarbageAbnormalDO updateObj = new GarbageAbnormalDO();
            updateObj.setId(abnormal.getId());
            updateObj.setHandleStatus(batchHandleReqVO.getHandleStatus());
            updateObj.setUpdater(currentUser);
            updateObj.setUpdateTime(now);

            updateList.add(updateObj);
        }

        // 3. 批量更新
        boolean updateResult = garbageAbnormalMapper.updateBatch(updateList);
        if (!updateResult) {
            throw exception(GARBAGE_ABNORMAL_UPDATE_FAILED);
        }
    }
}