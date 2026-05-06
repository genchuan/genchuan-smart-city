package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.timepermission;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.TimePermissionSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops.TimePermissionCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.ops.TimePermissionUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.statistics.TimePermissionChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.timepermission.TimePermissionDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.timepermission.TimePermissionMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.TIME_PERMISSION_NOT_EXISTS;

/**
 * 时段权限 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class TimePermissionServiceImpl implements TimePermissionService {

    @Resource
    private TimePermissionMapper timePermissionMapper;

    @Override
    public TimePermissionChartRespVO getTimePermissionChart() {
        TimePermissionChartRespVO resp = new TimePermissionChartRespVO();

        // 1. 卡片统计
        TimePermissionChartRespVO.CardData cardData = new TimePermissionChartRespVO.CardData();
        long enableCount = timePermissionMapper.selectCount(new LambdaQueryWrapperX<TimePermissionDO>()
                .eq(TimePermissionDO::getStatus, "已生效"));
        cardData.setEnableRuleCount((int) enableCount);
        Integer totalUseCount = timePermissionMapper.selectTotalUseCount();
        cardData.setTotalUseCount(totalUseCount == null ? 0 : totalUseCount);
        resp.setCardData(cardData);

        // 2. 按月折线统计
        List<TimePermissionChartRespVO.UseLine> useLineList = timePermissionMapper.selectUseCountGroupByMonth();
        resp.setUseLineList(useLineList);

        return resp;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void myUpdateTimePermission(TimePermissionUpdateReqVO updateReqVO) {
        // 1. 校验记录是否存在
        validateTimePermissionExists(updateReqVO.getId());

        // 2. 校验唯一性（排除自身）
        validateTimePermissionUnique(
                updateReqVO.getStationId(),
                updateReqVO.getTimeRange(),
                updateReqVO.getPermission(),
                updateReqVO.getId()
        );

        // 3. 转换 DO
        TimePermissionDO updateObj = BeanUtils.toBean(updateReqVO, TimePermissionDO.class);

        // 4. 执行更新
        timePermissionMapper.updateById(updateObj);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableTimePermission(List<Long> ids) {
        timePermissionMapper.update(new LambdaUpdateWrapper<TimePermissionDO>()
                .in(TimePermissionDO::getId, ids)
                .set(TimePermissionDO::getStatus, "已生效")
                .set(TimePermissionDO::getAuditTime, LocalDateTime.now())
                .set(TimePermissionDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableTimePermission(List<Long> ids) {
        timePermissionMapper.update(new LambdaUpdateWrapper<TimePermissionDO>()
                .in(TimePermissionDO::getId, ids)
                .set(TimePermissionDO::getStatus, "已禁用")
                .set(TimePermissionDO::getAuditTime, LocalDateTime.now())
                .set(TimePermissionDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImportRespVO importTimePermission(MultipartFile file, boolean updateSupport) {
        ImportRespVO resp = new ImportRespVO();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            // 解析 Excel
            Map<String, Object> resultMap = VrvExcelUtils.importExcelAndReturnEntity(
                    file,
                    TimePermissionCreateReqVO.class.getName()
            );

            List<TimePermissionCreateReqVO> reqList = (List<TimePermissionCreateReqVO>) resultMap.get("entityList");

            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }

            int successCount = 0;
            List<ImportRespVO.ImportFailure> failures = new ArrayList<>();

            for (int i = 0; i < reqList.size(); i++) {
                TimePermissionCreateReqVO req = reqList.get(i);
                int rowIndex = i + 2;

                try {
                    if (updateSupport) {
                        updateTimePermissionIfExists(req);
                    } else {
                        addTimePermission(req);
                    }
                    successCount++;
                } catch (Exception e) {
                    ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
                    failure.setRowIndex(rowIndex);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            resp.setSuccessCount(successCount);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;

        } catch (Exception e) {
            ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }

    /**
     * 存在则更新，不存在则新增
     */
    private void updateTimePermissionIfExists(TimePermissionCreateReqVO req) {
        // 唯一判断：stationId + timeRange + permission
        TimePermissionDO existing = timePermissionMapper.selectOne(
                new LambdaQueryWrapperX<TimePermissionDO>()
                        .eq(TimePermissionDO::getStationId, req.getStationId())
                        .eq(TimePermissionDO::getTimeRange, req.getTimeRange())
                        .eq(TimePermissionDO::getPermission, req.getPermission())
        );

        if (existing == null) {
            // 新增
            addTimePermission(req);
        } else {
            // 更新
            TimePermissionDO updateObj = BeanUtils.toBean(req, TimePermissionDO.class);
            updateObj.setId(existing.getId());
            // 保留原有状态、使用次数
            updateObj.setStatus(existing.getStatus());
            updateObj.setUseCount(existing.getUseCount());
            timePermissionMapper.updateById(updateObj);
        }
    }
    @Override
    public Long createTimePermission(TimePermissionSaveReqVO createReqVO) {
        // 插入
        TimePermissionDO timePermission = BeanUtils.toBean(createReqVO, TimePermissionDO.class);
        timePermissionMapper.insert(timePermission);

        // 返回
        return timePermission.getId();
    }

    @Override
    public void updateTimePermission(TimePermissionSaveReqVO updateReqVO) {
        // 校验存在
        validateTimePermissionExists(updateReqVO.getId());
        // 更新
        TimePermissionDO updateObj = BeanUtils.toBean(updateReqVO, TimePermissionDO.class);
        timePermissionMapper.updateById(updateObj);
    }

    @Override
    public void deleteTimePermission(Long id) {
        // 校验存在
        validateTimePermissionExists(id);
        // 删除
        timePermissionMapper.deleteById(id);
    }

    @Override
        public void deleteTimePermissionListByIds(List<Long> ids) {
        // 删除
        timePermissionMapper.deleteByIds(ids);
        }


    private void validateTimePermissionExists(Long id) {
        if (timePermissionMapper.selectById(id) == null) {
            throw exception(TIME_PERMISSION_NOT_EXISTS);
        }
    }

    @Override
    public TimePermissionDO getTimePermission(Long id) {
        return timePermissionMapper.selectById(id);
    }

    @Override
    public PageResult<TimePermissionRespVO> getTimePermissionPage(TimePermissionPageReqVO pageReqVO) {
        Page<TimePermissionRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        Page<TimePermissionRespVO> resultPage = timePermissionMapper.getPage(page, pageReqVO);
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    // ==================== 【新增 + 唯一性校验】 ====================
    @Override
    public void addTimePermission(TimePermissionCreateReqVO createReqVO) {
        // 1. 校验唯一性：同一个场站 + 时段 + 权限 不能重复
        validateTimePermissionUnique(
                createReqVO.getStationId(),
                createReqVO.getTimeRange(),
                createReqVO.getPermission(),
                null // 新增传 null
        );

        // 2. 转换 DO
        TimePermissionDO timePermission = BeanUtils.toBean(createReqVO, TimePermissionDO.class);
        timePermission.setStatus("待生效");
        timePermission.setUseCount(0);

        // 3. 插入
        timePermissionMapper.insert(timePermission);

    }

    /**
     * 【核心】校验时段权限唯一性
     * @param stationId 场站ID
     * @param timeRange 时段
     * @param permission 准入权限
     * @param excludeId 排除ID（修改时用）
     */
    private void validateTimePermissionUnique(Long stationId, String timeRange, String permission, Long excludeId) {
        TimePermissionDO exist = timePermissionMapper.selectOne(
                new LambdaQueryWrapperX<TimePermissionDO>()
                        .eq(TimePermissionDO::getStationId, stationId)
                        .eq(TimePermissionDO::getTimeRange, timeRange)
                        .eq(TimePermissionDO::getPermission, permission)
                        .neIfPresent(TimePermissionDO::getId, excludeId)
        );

        if (exist != null) {
            throw exception("当前场站、生效时段、准入权限的配置已存在，不允许重复添加！");
        }
    }

}
