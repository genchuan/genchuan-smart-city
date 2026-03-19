package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcleaningtask;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.framework.file.FileFeignClient;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletCleaningTaskMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.publictoilet.ToiletCleaningTaskCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.convert.UrlConvert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 公厕保洁任务 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class ToiletCleaningTaskServiceImpl implements ToiletCleaningTaskService {

    @Resource
    private ToiletCleaningTaskMapper toiletCleaningTaskMapper;

    @Resource
    private ToiletCleaningTaskCodeGenerator codeGenerator;

    @Resource
    private FileFeignClient fileFeignClient;

    @Resource
    private UrlConvert urlConvertUtil;

    @Override
    public Long createToiletCleaningTask(ToiletCleaningTaskSaveReqVO createReqVO) {
        //CleanerIds空值处理
        if (createReqVO.getCleanerIds() == null || createReqVO.getCleanerIds().trim().isEmpty()) {
            createReqVO.setCleanerIds("[]");
        }
        //ProofUrls空值处理
        if (createReqVO.getProofUrls() == null || createReqVO.getProofUrls().trim().isEmpty()) {
            createReqVO.setProofUrls("[]");
        }
        // 插入
        ToiletCleaningTaskDO toiletCleaningTask = BeanUtils.toBean(createReqVO, ToiletCleaningTaskDO.class);

        toiletCleaningTask.setTaskNo(codeGenerator.generateTaskNo());
        toiletCleaningTaskMapper.insert(toiletCleaningTask);
        // 返回
        return toiletCleaningTask.getId();
    }

    @Override
    public void updateToiletCleaningTask(ToiletCleaningTaskSaveReqVO updateReqVO) {
        // 校验存在
        validateToiletCleaningTaskExists(updateReqVO.getId());
        if (updateReqVO.getCleanerIds() == null || updateReqVO.getCleanerIds().trim().isEmpty()) {
            updateReqVO.setCleanerIds("[]");
        }
        //ProofUrls空值处理
        if (updateReqVO.getProofUrls() == null || updateReqVO.getProofUrls().trim().isEmpty()) {
            updateReqVO.setProofUrls("[]");
        }
        // 更新
        ToiletCleaningTaskDO updateObj = BeanUtils.toBean(updateReqVO, ToiletCleaningTaskDO.class);
        toiletCleaningTaskMapper.updateById(updateObj);
    }

    @Override
    public void deleteToiletCleaningTask(Long id) {
        // 校验存在
        validateToiletCleaningTaskExists(id);
        // 删除
        toiletCleaningTaskMapper.deleteById(id);
    }

    @Override
    public void deleteToiletCleaningTaskBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        List<ToiletCleaningTaskDO> toiletCleaningTasks = toiletCleaningTaskMapper.selectBatchIds(ids);
        if (toiletCleaningTasks.size() != ids.size()) {
            throw exception(TOILET_CLEANING_TASK_NOT_EXISTS);
        }

        toiletCleaningTaskMapper.deleteBatchIds(ids);
    }

    private void validateToiletCleaningTaskExists(Long id) {
        if (toiletCleaningTaskMapper.selectById(id) == null) {
            throw exception(TOILET_CLEANING_TASK_NOT_EXISTS);
        }
    }

    @Override
    public ToiletCleaningTaskDO getToiletCleaningTask(Long id) {
        return toiletCleaningTaskMapper.selectById(id);
    }

    @Override
    public PageResult<ToiletCleaningTaskDO> getToiletCleaningTaskPage(ToiletCleaningTaskPageReqVO pageReqVO) {
        return toiletCleaningTaskMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ToiletCleaningTaskDetailDO> getToiletCleaningTaskDetailPage(ToiletCleaningTaskPageReqVO pageReqVO) {

        Long total = toiletCleaningTaskMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<ToiletCleaningTaskDetailDO> list = toiletCleaningTaskMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public List<String> uploadPhotos(Long id, List<MultipartFile> files) {
        // 校验任务存在
        validateToiletCleaningTaskExists(id);

        List<String> photoUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            CommonResult<String> result = fileFeignClient.uploadFile(file);
            if (result.isError()) {
                throw new RuntimeException("图片上传失败：" + result.getMsg());
            }

            // 转换为公网地址
            String publicUrl = urlConvertUtil.convertToPublicUrl(result.getData());
            photoUrls.add(publicUrl);
        }

        // 获取当前任务记录
        ToiletCleaningTaskDO task = toiletCleaningTaskMapper.selectById(id);

        // 解析现有的图片列表
        List<String> photoList = parsePhotoList(task.getProofUrls());

        // 添加新图片
        photoList.addAll(photoUrls);

        // 更新数据库
        task.setProofUrls(JSON.toJSONString(photoList));
        toiletCleaningTaskMapper.updateById(task);

        return photoUrls;
    }

    @Override
    public List<String> getPhotos(Long id) {
        validateToiletCleaningTaskExists(id);
        ToiletCleaningTaskDO task = toiletCleaningTaskMapper.selectById(id);
        return parsePhotoList(task.getProofUrls());
    }

    @Override
    public void deletePhoto(Long id, String photoUrl) {
        validateToiletCleaningTaskExists(id);

        ToiletCleaningTaskDO task = toiletCleaningTaskMapper.selectById(id);
        List<String> photoList = parsePhotoList(task.getProofUrls());

        if (photoList.remove(photoUrl)) {
            task.setProofUrls(JSON.toJSONString(photoList));
            toiletCleaningTaskMapper.updateById(task);
        }
    }

    /**
     * 解析图片列表
     */
    private List<String> parsePhotoList(String photoJson) {
        if (photoJson == null || photoJson.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return JSON.parseArray(photoJson, String.class);
        } catch (Exception e) {
            // 如果是单个图片地址，转为列表
            List<String> list = new ArrayList<>();
            list.add(photoJson);
            return list;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAdjustToiletCleaningTask(ToiletCleaningTaskBatchAdjustReqVO reqVO) {
        // 1. 参数校验
        List<Long> ids = reqVO.getIds();
        Map<String, Object> updateFields = reqVO.getUpdateFields();

        if (CollectionUtils.isEmpty(ids)) {
            throw exception(TOILET_CLEANING_TASK_IDS_NOT_EMPTY);
        }
        if (updateFields == null || updateFields.isEmpty()) {
            throw exception(UPDATE_FIELDS_NOT_EMPTY);
        }

        // 2. 校验任务是否存在
        List<ToiletCleaningTaskDO> existingTasks = toiletCleaningTaskMapper.selectBatchIds(ids);
        if (existingTasks.size() != ids.size()) {
            throw exception(TOILET_CLEANING_TASK_NOT_EXISTS);
        }

        // 3. 构建更新条件
        LambdaUpdateWrapper<ToiletCleaningTaskDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(ToiletCleaningTaskDO::getId, ids);

        // 4. 动态设置要更新的字段
        updateFields.forEach((field, value) -> {
            if (value == null) {
                return; // 跳过null值
            }

            switch (field) {
                case "cleaningFrequency":
                    validateStringField(field, value);
                    updateWrapper.set(ToiletCleaningTaskDO::getCleaningFrequency, value.toString());
                    break;

                case "cleaningTime":
                    validateStringField(field, value);
                    updateWrapper.set(ToiletCleaningTaskDO::getCleaningTime, value.toString());
                    break;

                case "cleaningContent":
                    validateStringField(field, value);
                    updateWrapper.set(ToiletCleaningTaskDO::getCleaningContent, value.toString());
                    break;

                case "cleaningStandard":
                    validateStringField(field, value);
                    updateWrapper.set(ToiletCleaningTaskDO::getCleaningStandard, value.toString());
                    break;

                case "cleanerIds":
                    // 处理保洁人员IDs（JSON格式）
                    String cleanerIdsJson = convertToJsonArray(value);
                    updateWrapper.set(ToiletCleaningTaskDO::getCleanerIds, cleanerIdsJson);
                    break;

                case "planStatusId":
                    validateStringField(field, value);
                    updateWrapper.set(ToiletCleaningTaskDO::getPlanStatusId, value.toString());
                    break;

                case "completionRate":
                    if (value instanceof Number) {
                        updateWrapper.set(ToiletCleaningTaskDO::getCompletionRate,
                                ((Number) value).doubleValue());
                    } else {
                        updateWrapper.set(ToiletCleaningTaskDO::getCompletionRate,
                                Double.parseDouble(value.toString()));
                    }
                    break;

                case "isAbnormal":
                    if (value instanceof Boolean) {
                        updateWrapper.set(ToiletCleaningTaskDO::getIsAbnormal, (Boolean) value);
                    } else {
                        updateWrapper.set(ToiletCleaningTaskDO::getIsAbnormal,
                                Boolean.parseBoolean(value.toString()));
                    }
                    break;

                case "abnormalDesc":
                    validateStringField(field, value);
                    updateWrapper.set(ToiletCleaningTaskDO::getAbnormalDesc, value.toString());
                    break;

                case "handleResult":
                    validateStringField(field, value);
                    updateWrapper.set(ToiletCleaningTaskDO::getHandleResult, value.toString());
                    break;

                case "satisfaction":
                    if (value instanceof Number) {
                        updateWrapper.set(ToiletCleaningTaskDO::getSatisfaction,
                                ((Number) value).intValue());
                    } else {
                        updateWrapper.set(ToiletCleaningTaskDO::getSatisfaction,
                                Integer.parseInt(value.toString()));
                    }
                    break;

                default:
                    log.warn("不支持的更新字段: {}", field);
                    throw exception(UNSUPPORTED_FIELD, field);
            }
        });

        // 5. 添加更新时间
        updateWrapper.set(ToiletCleaningTaskDO::getUpdateTime, LocalDateTime.now());

        // 6. 执行批量更新
        int updateCount = toiletCleaningTaskMapper.update(updateWrapper);

        // 7. 记录操作日志
        log.info("批量调整公厕保洁任务完成，IDs: {}, 更新字段: {}, 影响行数: {}",
                ids, updateFields.keySet(), updateCount);
    }

    /**
     * 校验字符串字段
     */
    private void validateStringField(String field, Object value) {
        if (value == null || value.toString().trim().isEmpty()) {
            throw exception(FIELD_CANNOT_NULL, field);
        }
    }

    /**
     * 转换为JSON数组格式
     */
    private String convertToJsonArray(Object value) {
        if (value == null) {
            return "[]";
        }

        try {
            if (value instanceof List) {
                // 如果是List，直接转JSON
                return JSON.toJSONString(value);
            } else if (value instanceof String) {
                String strValue = value.toString().trim();
                // 如果已经是JSON数组格式，直接返回
                if (strValue.startsWith("[") && strValue.endsWith("]")) {
                    return strValue;
                }
                // 如果是单个ID，转换为数组
                return String.format("[\"%s\"]", strValue);
            } else {
                // 其他类型，转换为字符串数组
                return String.format("[\"%s\"]", value.toString());
            }
        } catch (Exception e) {
            log.error("转换为JSON数组失败", e);
            return "[]";
        }
    }

    /**
     * 卡片/圆环图/柱状图统计(待执行)
     */
    @Override
    public ToiletCleaningTaskPendingRespVO getPending() {
        ToiletCleaningTaskPendingRespVO resp = new ToiletCleaningTaskPendingRespVO();
        resp.setPendingCount(toiletCleaningTaskMapper.countPending());
        resp.setPendingByArea(toiletCleaningTaskMapper.countPendingAreaNum());
        resp.setAssignedByCleaner(countAssignedCleanerNumForPending());
        resp.setFrequencyDistribution(toiletCleaningTaskMapper.selectFrequencyDistribution());
        resp.setAreaDistribution(toiletCleaningTaskMapper.selectAreaDistribution());
        resp.setPlanCountByTimeSlot(toiletCleaningTaskMapper.selectPlanCountByTimeSlot());
        return resp;
    }

    /**
     * 处理待执行人员json
     */
    private Long countAssignedCleanerNumForPending() {
        List<String> rows = toiletCleaningTaskMapper.selectPendingCleanerIdsJson();
        java.util.Set<String> set = new java.util.HashSet<>();
        for (String json : rows) {
            if (json == null || json.trim().isEmpty() || "null".equalsIgnoreCase(json.trim())) {
                continue;
            }
            try {
                List<String> ids = JSON.parseArray(json, String.class);
                if (ids == null) continue;
                for (String id : ids) {
                    if (id != null && !id.trim().isEmpty()) {
                        set.add(id.trim());
                    }
                }
            } catch (Exception ignore) {
            }
        }
        return (long) set.size();
    }

    @Override
    public ToiletCleaningTaskSummaryRespVO getSummary() {
        ToiletCleaningTaskSummaryRespVO resp = new ToiletCleaningTaskSummaryRespVO();
        resp.setCompletedTotal(toiletCleaningTaskMapper.countCompleted());
        resp.setCleaningQualifiedRate(toiletCleaningTaskMapper.calcCleaningQualifiedRate());
        resp.setComplaintFinishRate(toiletCleaningTaskMapper.calcComplaintFinishRate());
        resp.setFacilityGoodRate(toiletCleaningTaskMapper.calcFacilityGoodRate());
        resp.setCompletionCountByPeriod(toiletCleaningTaskMapper.selectCompletionCountByPeriod());
        resp.setCleaningQualifiedTrend(toiletCleaningTaskMapper.selectCleaningQualifiedTrend());
        resp.setTaskTypeDistribution(toiletCleaningTaskMapper.selectTaskTypeDistribution());
        resp.setAreaCompletionDistribution(toiletCleaningTaskMapper.selectAreaCompletionDistribution());
        return resp;
    }
}