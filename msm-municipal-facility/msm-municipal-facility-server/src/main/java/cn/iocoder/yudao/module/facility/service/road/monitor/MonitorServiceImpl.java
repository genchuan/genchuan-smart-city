package cn.iocoder.yudao.module.facility.service.road.monitor;

import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.monitor.MonitorDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadconfig.RoadConfigDO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.monitor.MonitorMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadconfig.RoadConfigMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 道路监测 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonitorServiceImpl implements MonitorService {

    @Resource
    private MonitorMapper monitorMapper;

    @Resource
    private RoadConfigMapper roadConfigMapper;

    @Override
    public Long createMonitor(MonitorSaveReqVO createReqVO) {
        // 1. 查询 road_config（用于做快照）
        RoadConfigDO config = roadConfigMapper.selectById(createReqVO.getConfigId());
        if (config == null) {
            throw exception(500,"道路配置road_config不存在，请输入正确道路配置");
        }

        // 2. VO -> DO
        MonitorDO monitor = BeanUtils.toBean(createReqVO, MonitorDO.class);

        // 3. monitorCode：UUID
        monitor.setMonitorCode(UUID.randomUUID().toString().replace("-", ""));

        // 4. 阈值快照（非常关键：冻结当时规则）
        monitor.setPotholeNumThreshold(config.getPotholeNumThreshold());
        monitor.setCrackLengthThreshold(config.getCrackLengthThreshold());
        monitor.setRoadTempThreshold(config.getRoadTempThreshold());
        monitor.setTrafficFlowThreshold(config.getTrafficFlowThreshold());
        monitor.setCollectFrequencySnapshot(
                config.getCollectFrequency() == null
                        ? null
                        : BigDecimal.valueOf(config.getCollectFrequency())
        );

        // 5. 是否预警（TODO：后续接入真实预警逻辑）
        monitor.setIsWarning(0);
        monitor.setWarningIdListStr(null);

        // 6. 监测状态（默认运行中）
        monitor.setMonitorStatus("运行中");

        // 7. 同步时长（0~10 秒）
        monitor.setSyncDuration(BigDecimal.valueOf(new Random().nextInt(11)));

        // 8. 记录时间（采集时间）
        monitor.setRecordTime(LocalDateTime.now());

        // 9. 插入（id 由数据库生成）
        monitorMapper.insert(monitor);

        // 10. 返回主键
        return monitor.getId();
    }

    @Override
    public void updateMonitor(MonitorUpdateReqVO updateReqVO) {
        // 校验存在
        validateMonitorExists(updateReqVO.getId());
        // 更新
        MonitorDO updateObj = BeanUtils.toBean(updateReqVO, MonitorDO.class);
        monitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonitor(Long id) {
        // 校验存在
        validateMonitorExists(id);
        // 删除
        monitorMapper.deleteById(id);
    }

    private void validateMonitorExists(Long id) {
        if (monitorMapper.selectById(id) == null) {
            throw exception(MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public MonitorDO getMonitor(Long id) {
        return monitorMapper.selectById(id);
    }

    @Override
    public PageResult<MonitorDO> getMonitorPage(MonitorPageReqVO pageReqVO) {
        return monitorMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<RealtimePageRespVO> getRealtimePage(RealtimePageReqVO reqVO) {
        // 1. 参数兜底（防止前端乱传分页参数）
        //    如果 pageNo 为空或小于 1，则默认第一页
        int pageNo = reqVO.getPageNo() == null || reqVO.getPageNo() < 1
                ? 1
                : reqVO.getPageNo();

        //    如果 pageSize 为空或小于 1，则默认每页 10 条
        int pageSize = reqVO.getPageSize() == null || reqVO.getPageSize() < 1
                ? 10
                : reqVO.getPageSize();

        // 2. 计算 SQL 偏移量 offset
        //    用于 LIMIT 子句：OFFSET = (当前页码 - 1) * 每页数量
        int offset = (pageNo - 1) * pageSize;

        // 3. 设置分页参数（传给 Mapper SQL 使用）
        reqVO.setOffset(offset);
        reqVO.setLimit(pageSize);

        // 4. 查询分页数据
        //    从数据库获取当前页的道路监测数据列表
        List<RealtimePageRespVO> list = monitorMapper.getRealtimePage(reqVO);

        // 5. 填充“指标阈值范围”字段
        //    该字段用于前端统一显示阈值信息，如“坑洼≤10；裂缝≤5m”
        if (list != null && !list.isEmpty()) {
            for (RealtimePageRespVO vo : list) {
                vo.setIndicatorThresholdRange(buildIndicatorThresholdRange(vo));
            }
        }

        // 6. 查询总记录数（分页必须单独查总数）
        long total = monitorMapper.countRealtimePage(reqVO);

        // 7. 构造并返回分页结果
        //    PageResult 包含列表数据和总记录数
        return new PageResult<>(list, total);
    }

    /**
     * 批量修改道路运行监测状态
     *
     * <p>业务规则说明：</p>
     * <ul>
     *     <li>monitorStatus：必填，只允许“运行中 / 已停止”</li>
     *     <li>roadIdList：
     *          <ul>
     *              <li>null：表示修改全部道路监测状态（⚠️高危操作）</li>
     *              <li>非空集合：表示修改指定道路</li>
     *              <li>空集合：非法参数，不允许</li>
     *          </ul>
     *     </li>
     * </ul>
     *
     * @param reqVO 批量修改监测状态请求参数
     * @return 实际受影响的记录条数
     */
    @Override
    public int batchUpdateMonitorStatus(BatchUpdateMonitorStatusReqVO reqVO) {
        // ==================== 1. 基础校验 ====================
        if (reqVO == null) {
            // 请求体不能为空，否则无法判断修改目标和状态
            throw exception(500, "请求参数不能为空");
        }

        // ==================== 2. 校验监测状态（字符串） ====================
        String monitorStatus = reqVO.getMonitorStatus();
        if (monitorStatus == null || monitorStatus.isBlank()) {
            // 运行监测状态是核心业务参数，不能为空
            throw exception(500, "运行监测状态不能为空");
        }

        // ==================== 3. 校验状态值合法性 ====================
        // 当前只允许两种状态：
        //   - 运行中
        //   - 已停止
        // 注意：若后续新增状态（如：暂停、维护中），需同步调整此处校验逻辑
        if (!"运行中".equals(monitorStatus)
                && !"已停止".equals(monitorStatus)) {
            throw exception(500, "运行监测状态不合法");
        }

        // ==================== 4. 处理道路ID列表 ====================
        List<Long> roadIdList = reqVO.getRoadIdList();

        // roadIdList == null：表示对【全部道路】执行修改操作（高风险）
        // 必须通过明确的 null 语义触发，避免误操作
        if (roadIdList == null) {
            return monitorMapper.updateAllMonitorStatus(monitorStatus);
        }

        // roadIdList 为空集合：语义不明确，直接判定为非法参数
        if (roadIdList.isEmpty()) {
            throw exception(500, "道路ID列表不能为空");
        }

        // ==================== 5. 修改指定道路监测状态 ====================
        return monitorMapper.batchUpdateMonitorStatus(roadIdList, monitorStatus);
    }

    /**
     * 构建【指标阈值范围】展示字段
     * 说明：
     *  1. 基于“阈值快照”字段拼接字符串
     *  2. 页面显示格式：
     *     - 坑洼≤阈值
     *     - 裂缝≤阈值 m
     *     - 温度≤阈值 ℃
     *     - 流量≤阈值 辆/h
     *  3. 多个指标用“；”分隔，最后去掉多余分号
     *
     * @param vo 道路监测记录 VO
     * @return 拼接好的指标阈值范围字符串
     */
    private String buildIndicatorThresholdRange(RealtimePageRespVO vo) {
        StringBuilder sb = new StringBuilder();

        if (vo.getPotholeNumThreshold() != null) {
            sb.append("坑洼≤").append(vo.getPotholeNumThreshold()).append("；");
        }
        if (vo.getCrackLengthThreshold() != null) {
            sb.append("裂缝≤").append(vo.getCrackLengthThreshold()).append("m；");
        }
        if (vo.getRoadTempThreshold() != null) {
            sb.append("温度≤").append(vo.getRoadTempThreshold()).append("℃；");
        }
        if (vo.getTrafficFlowThreshold() != null) {
            sb.append("流量≤").append(vo.getTrafficFlowThreshold()).append("辆/h；");
        }

        // 去掉最后一个分号，防止显示多余符号
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

}
