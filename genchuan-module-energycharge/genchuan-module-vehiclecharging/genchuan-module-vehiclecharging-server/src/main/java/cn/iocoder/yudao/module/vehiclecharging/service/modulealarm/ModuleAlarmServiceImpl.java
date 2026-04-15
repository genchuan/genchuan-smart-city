package cn.iocoder.yudao.module.vehiclecharging.service.modulealarm;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.time.LocalDateTime;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.modulealarm.ModuleAlarmDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.modulealarm.ModuleAlarmMapper;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertMap;

/**
 * 模块告警记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ModuleAlarmServiceImpl implements ModuleAlarmService {

    @Resource
    private ModuleAlarmMapper moduleAlarmMapper;

    @Autowired(required = false)
    private AdminUserApi adminUserApi;

    @Override
    public Long createModuleAlarm(ModuleAlarmSaveReqVO createReqVO) {
        // 插入
        ModuleAlarmDO moduleAlarm = BeanUtils.toBean(createReqVO, ModuleAlarmDO.class);
        moduleAlarmMapper.insert(moduleAlarm);

        // 返回
        return moduleAlarm.getId();
    }

    @Override
    public void updateModuleAlarm(ModuleAlarmSaveReqVO updateReqVO) {
        // 校验存在
        validateModuleAlarmExists(updateReqVO.getId());
        // 更新
        ModuleAlarmDO updateObj = BeanUtils.toBean(updateReqVO, ModuleAlarmDO.class);
        moduleAlarmMapper.updateById(updateObj);
    }

    @Override
    public void deleteModuleAlarm(Long id) {
        // 校验存在
        validateModuleAlarmExists(id);
        // 删除
        moduleAlarmMapper.deleteById(id);
    }

    @Override
    public void deleteModuleAlarmListByIds(List<Long> ids) {
        // 删除
        moduleAlarmMapper.deleteByIds(ids);
    }

    private void validateModuleAlarmExists(Long id) {
        if (moduleAlarmMapper.selectById(id) == null) {
            throw exception(MODULE_ALARM_NOT_EXISTS);
        }
    }

    @Override
    public ModuleAlarmRespVO getModuleAlarm(Long id) {
        ModuleAlarmRespVO respVO = moduleAlarmMapper.selectWithNamesById(id);
        if (respVO == null) {
            return null;
        }
        fillCreatorName(respVO);
        return respVO;
    }

    @Override
    public PageResult<ModuleAlarmRespVO> getModuleAlarmPage(ModuleAlarmPageReqVO pageReqVO) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ModuleAlarmRespVO> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<ModuleAlarmRespVO> result = moduleAlarmMapper.selectPageWithNames(page, pageReqVO);
        List<ModuleAlarmRespVO> list = result.getRecords();
        fillCreatorNames(list);
        return new PageResult<>(list, result.getTotal());
    }

    /**
     * 填充单条记录的创建者/更新者昵称
     */
    private void fillCreatorName(ModuleAlarmRespVO respVO) {
        if (respVO.getCreator() != null && adminUserApi != null) {
            try {
                AdminUserRespDTO user = adminUserApi.getUser(Long.valueOf(respVO.getCreator())).getCheckedData();
                if (user != null) {
                    respVO.setCreatorName(user.getNickname());
                }
            } catch (Exception ignored) {
            }
        }
        if (respVO.getUpdater() != null && adminUserApi != null) {
            try {
                AdminUserRespDTO user = adminUserApi.getUser(Long.valueOf(respVO.getUpdater())).getCheckedData();
                if (user != null) {
                    respVO.setUpdaterName(user.getNickname());
                }
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 批量填充创建者/更新者昵称
     */
    private void fillCreatorNames(List<ModuleAlarmRespVO> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        // 收集所有 creator 和 updater，转为 Long
        Set<Long> allUserIds = new HashSet<>();
        for (ModuleAlarmRespVO vo : list) {
            if (vo.getCreator() != null) {
                try { allUserIds.add(Long.valueOf(vo.getCreator())); } catch (Exception ignored) {}
            }
            if (vo.getUpdater() != null) {
                try { allUserIds.add(Long.valueOf(vo.getUpdater())); } catch (Exception ignored) {}
            }
        }
        if (CollUtil.isEmpty(allUserIds) || adminUserApi == null) {
            return;
        }
        // 批量查询用户昵称
        Map<Long, String> userNameMap = convertMap(
                adminUserApi.getUserList(allUserIds).getCheckedData(),
                AdminUserRespDTO::getId,
                AdminUserRespDTO::getNickname);
        // 逐条填充
        for (ModuleAlarmRespVO vo : list) {
            if (vo.getCreator() != null) {
                try { vo.setCreatorName(userNameMap.get(Long.valueOf(vo.getCreator()))); } catch (Exception ignored) {}
            }
            if (vo.getUpdater() != null) {
                try { vo.setUpdaterName(userNameMap.get(Long.valueOf(vo.getUpdater()))); } catch (Exception ignored) {}
            }
        }
    }

    @Override
    public void checkModuleAlarm(ModuleAlarmCheckReqVO checkReqVO) {
        ModuleAlarmDO alarm = moduleAlarmMapper.selectById(checkReqVO.getId());
        if (alarm == null) {
            throw exception(MODULE_ALARM_NOT_EXISTS);
        }
        if (alarm.getAlarmStatusId() != null && alarm.getAlarmStatusId() != 1) {
            throw exception(MODULE_ALARM_STATUS_NOT_UNCHECKED);
        }
        ModuleAlarmDO update = ModuleAlarmDO.builder()
                .id(checkReqVO.getId())
                .alarmStatusId(2L)
                .checkReason(checkReqVO.getCheckReason())
                .build();
        moduleAlarmMapper.updateById(update);
    }

    @Override
    public void repairModuleAlarm(ModuleAlarmRepairReqVO repairReqVO) {
        ModuleAlarmDO alarm = moduleAlarmMapper.selectById(repairReqVO.getId());
        if (alarm == null) {
            throw exception(MODULE_ALARM_NOT_EXISTS);
        }
        // 状态必须为已排查（2）才能执行修复
        if (alarm.getAlarmStatusId() == null || alarm.getAlarmStatusId() != 2) {
            throw exception(MODULE_ALARM_STATUS_NOT_CHECKED);
        }
        ModuleAlarmDO update = ModuleAlarmDO.builder()
                .id(repairReqVO.getId())
                .alarmStatusId(3L)
                .repairVoucher(repairReqVO.getRepairVoucher())
                .build();
        moduleAlarmMapper.updateById(update);
    }

    @Override
    public void closeModuleAlarm(ModuleAlarmCloseReqVO closeReqVO) {
        ModuleAlarmDO alarm = moduleAlarmMapper.selectById(closeReqVO.getId());
        if (alarm == null) {
            throw exception(MODULE_ALARM_NOT_EXISTS);
        }
        // 状态必须为修复中（3）才能执行销账
        if (alarm.getAlarmStatusId() == null || alarm.getAlarmStatusId() != 3) {
            throw exception(MODULE_ALARM_STATUS_NOT_REPAIRING);
        }
        ModuleAlarmDO update = ModuleAlarmDO.builder()
                .id(closeReqVO.getId())
                .alarmStatusId(4L)
                .repairTime(LocalDateTime.now())
                .build();
        moduleAlarmMapper.updateById(update);
    }

    @Override
    public void updateRemark(ModuleAlarmRemarkReqVO remarkReqVO) {
        validateModuleAlarmExists(remarkReqVO.getId());
        ModuleAlarmDO update = ModuleAlarmDO.builder()
                .id(remarkReqVO.getId())
                .remark(remarkReqVO.getRemark())
                .build();
        moduleAlarmMapper.updateById(update);
    }

    @Override
    public String getRepairVoucher(ModuleAlarmRepairVoucherReqVO reqVO) {
        ModuleAlarmDO alarm = moduleAlarmMapper.selectById(reqVO.getId());
        if (alarm == null) {
            throw exception(MODULE_ALARM_NOT_EXISTS);
        }
        if (alarm.getRepairVoucher() == null || alarm.getRepairVoucher().isBlank()) {
            throw exception(MODULE_ALARM_REPAIR_VOUCHER_NOT_EXISTS);
        }
        return alarm.getRepairVoucher();
    }

    @Override
    public ModuleAlarmChartRespVO getChartData(ModuleAlarmChartReqVO reqVO) {
        // 1. 查询卡片数据（按状态分组计数）
        List<Map<String, Object>> cardDataList = moduleAlarmMapper.selectChartCardData(reqVO.getStartTime(), reqVO.getEndTime());

        long unCheckCount = 0;
        long checkedCount = 0;
        long repairingCount = 0;
        long closedCount = 0;
        long totalCount = 0;

        for (Map<String, Object> row : cardDataList) {
            Long statusId = ((Number) row.get("alarmStatusId")).longValue();
            long cnt = ((Number) row.get("count")).longValue();
            totalCount += cnt;
            if (statusId == 1) {
                unCheckCount = cnt;
            } else if (statusId == 2) {
                checkedCount = cnt;
            } else if (statusId == 3) {
                repairingCount = cnt;
            } else if (statusId == 4) {
                closedCount = cnt;
            }
        }

        // 2. 计算修复率
        java.math.BigDecimal repairRate = totalCount > 0
                ? java.math.BigDecimal.valueOf((double) closedCount / totalCount).setScale(2, java.math.RoundingMode.HALF_UP)
                : java.math.BigDecimal.ZERO;

        // 3. 查询柱状图数据（按模块统计）
        List<ModuleAlarmChartRespVO.BarData> barData = moduleAlarmMapper.selectChartBarData(
                reqVO.getStartTime(), reqVO.getEndTime());

        // 4. 查询折线图数据（每日平均修复时长）
        List<ModuleAlarmChartRespVO.LineData> lineData = moduleAlarmMapper.selectChartLineData(
                reqVO.getStartTime(), reqVO.getEndTime());

        // 5. 组装响应
        ModuleAlarmChartRespVO.CardData cardData = new ModuleAlarmChartRespVO.CardData(
                unCheckCount, checkedCount, repairingCount, closedCount);

        return new ModuleAlarmChartRespVO((int) totalCount, (int) closedCount, repairRate, barData, lineData, cardData);
    }

    @Override
    public List<ModuleAlarmChartRespVO.BarData> getModuleCount(ModuleAlarmModuleCountReqVO reqVO) {
        return moduleAlarmMapper.selectChartBarData(reqVO.getStartTime(), reqVO.getEndTime());
    }

    @Override
    public ModuleAlarmCountRespVO getStatusCount(ModuleAlarmCountReqVO reqVO) {
        List<Map<String, Object>> cardDataList = moduleAlarmMapper.selectChartCardData(reqVO.getStartTime(), reqVO.getEndTime());

        long unCheckCount = 0;
        long checkedCount = 0;
        long repairingCount = 0;
        long closedCount = 0;
        long totalCount = 0;

        for (Map<String, Object> row : cardDataList) {
            Long statusId = ((Number) row.get("alarmStatusId")).longValue();
            long cnt = ((Number) row.get("count")).longValue();
            totalCount += cnt;
            if (statusId == 1) {
                unCheckCount = cnt;
            } else if (statusId == 2) {
                checkedCount = cnt;
            } else if (statusId == 3) {
                repairingCount = cnt;
            } else if (statusId == 4) {
                closedCount = cnt;
            }
        }

        return new ModuleAlarmCountRespVO(unCheckCount, checkedCount, repairingCount, closedCount, totalCount);
    }

}
