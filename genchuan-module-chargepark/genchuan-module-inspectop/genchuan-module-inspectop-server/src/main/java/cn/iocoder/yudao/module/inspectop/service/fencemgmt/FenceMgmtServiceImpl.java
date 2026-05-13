package cn.iocoder.yudao.module.inspectop.service.fencemgmt;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.fencemgmt.FenceMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.fencemgmt.FenceMgmtMapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

/**
 * 电子围栏 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class FenceMgmtServiceImpl implements FenceMgmtService {

    @Resource
    private FenceMgmtMapper fenceMgmtMapper;

    @Override
    @LogRecord(type = FENCE_MGMT_TYPE, subType = FENCE_MGMT_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = FENCE_MGMT_CREATE_SUCCESS)
    public Long createFenceMgmt(FenceMgmtSaveReqVO createReqVO) {
        // 插入
        FenceMgmtDO fenceMgmt = BeanUtils.toBean(createReqVO, FenceMgmtDO.class);
        fenceMgmtMapper.insert(fenceMgmt);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return fenceMgmt.getId();
    }

    @Override
    @LogRecord(type = FENCE_MGMT_TYPE, subType = FENCE_MGMT_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = FENCE_MGMT_UPDATE_SUCCESS)
    public void updateFenceMgmt(FenceMgmtSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        FenceMgmtDO oldFenceMgmt = validateFenceMgmtExists(updateReqVO.getId());

        // 2. 更新
        FenceMgmtDO updateObj = BeanUtils.toBean(updateReqVO, FenceMgmtDO.class);
        fenceMgmtMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        FenceMgmtSaveReqVO oldVO = BeanUtils.toBean(oldFenceMgmt, FenceMgmtSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }

    @Override
    @LogRecord(type = FENCE_MGMT_TYPE, subType = FENCE_MGMT_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = FENCE_MGMT_DELETE_SUCCESS)
    public void deleteFenceMgmt(Long id) {
        // 校验存在
        validateFenceMgmtExists(id);
        // 删除
        fenceMgmtMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = FENCE_MGMT_TYPE, subType = FENCE_MGMT_DELETE_LIST_SUB_TYPE,
            success = FENCE_MGMT_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteFenceMgmtListByIds(List<Long> ids) {
        // 删除
        fenceMgmtMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }

    // 修改验证方法，使其返回FenceMgmtDO对象，用于update方法的日志对比
    private FenceMgmtDO validateFenceMgmtExists(Long id) {
        FenceMgmtDO fenceMgmt = fenceMgmtMapper.selectById(id);
        if (fenceMgmt == null) {
            throw exception(FENCE_MGMT_NOT_EXISTS);
        }
        return fenceMgmt; // 返回查询到的对象
    }

    @Override
    public FenceMgmtDO getFenceMgmt(Long id) {
        return fenceMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<FenceMgmtRespVO> getFenceMgmtPage(FenceMgmtPageReqVO pageReqVO) {
        // 创建 MyBatis-Plus 分页对象
        Page<FenceMgmtRespVO> mpPage
                = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用 Mapper 的关联查询方法
        Page<FenceMgmtRespVO> resultPage =
                fenceMgmtMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 构造返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = FENCE_MGMT_TYPE, subType = FENCE_MGMT_ENABLE_SUB_TYPE,
            bizNo = "{{#id}}", success = FENCE_MGMT_ENABLE_SUCCESS)
    public void enableFenceMgmt(Long id) {
        // 校验存在
        validateFenceMgmtExists(id);

        // 创建更新对象
        FenceMgmtDO updateObj = new FenceMgmtDO();
        updateObj.setId(id);
        updateObj.setStatus("2"); // 生效状态设为2

        // 执行更新
        fenceMgmtMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = FENCE_MGMT_TYPE, subType = FENCE_MGMT_DISABLE_SUB_TYPE,
            bizNo = "{{#id}}", success = FENCE_MGMT_DISABLE_SUCCESS)
    public void disableFenceMgmt(Long id) {
        // 校验存在
        validateFenceMgmtExists(id);

        // 创建更新对象
        FenceMgmtDO updateObj = new FenceMgmtDO();
        updateObj.setId(id);
        updateObj.setStatus("0"); // 禁用状态设为0

        // 执行更新
        fenceMgmtMapper.updateById(updateObj);
    }

    @Override
    public FenceMgmtChartRespVO getFenceMgmtChart() {
        FenceMgmtChartRespVO respVO = new FenceMgmtChartRespVO();

        // 1. 获取地图数据
        List<FenceMgmtChartRespVO.MapDataVO> mapData = getMapData();
        respVO.setMapData(mapData);

        // 2. 获取卡片数据
        FenceMgmtChartRespVO.CardDataVO cardData = getCardData();
        respVO.setCardData(cardData);

        return respVO;
    }

    /**
     * 获取地图数据
     */
    private List<FenceMgmtChartRespVO.MapDataVO> getMapData() {
        // 1. 查询数据库获取原始数据
        List<FenceMgmtMapper.MapDataDTO> dataList = fenceMgmtMapper.selectFenceDataForMap();

        // 2. 转换数据格式
        List<FenceMgmtChartRespVO.MapDataVO> result = new ArrayList<>();
        for (FenceMgmtMapper.MapDataDTO dto : dataList) {
            FenceMgmtChartRespVO.MapDataVO mapData = new FenceMgmtChartRespVO.MapDataVO();
            mapData.setId(dto.getId());
            mapData.setName(dto.getName());
            mapData.setArea(formatArea(dto.getArea())); // 格式化area字段
            mapData.setStatus(getStatusText(dto.getStatus())); // 转换状态为中文
            result.add(mapData);
        }

        return result;
    }

    /**
     * 格式化围栏区域坐标
     * 从任何可能的格式（"118.5865,24.9132|118.5880,24.9100" 或 "[118.5865,24.9132]|[118.5880,24.9100]" 等）
     * 统一转换为前端地图所需的格式："[[118.5865,24.9132],[118.5880,24.9100]]"
     */
    private String formatArea(String area) {
        if (area == null || area.trim().isEmpty()) {
            return "[]";
        }
        String trimmedArea = area.trim();
        // 如果字符串已经是合法的JSON数组格式（以[[开头，以]]结尾），尝试直接解析并重新规范格式
        // 这是为了处理数据库里可能已存储了JSON格式的数据
        if (trimmedArea.startsWith("[[") && trimmedArea.endsWith("]]")) {
            try {
                // 使用简单的字符串处理来清理，避免引入JSON解析库的依赖
                // 移除最外层的方括号
                String inner = trimmedArea.substring(1, trimmedArea.length() - 1);
                List<String> points = new ArrayList<>();
                // 这是一个简化的解析逻辑，假设格式相对规整
                // 更健壮的做法是使用如 Jackson 的 ObjectMapper，但这里为保持轻量，用字符串处理
                inner = inner.replaceAll("\\[", "").replaceAll("\\]", "");
                String[] latLonPairs = inner.split(",");
                // 每两个数字组成一个点
                for (int i = 0; i < latLonPairs.length; i += 2) {
                    if (i + 1 < latLonPairs.length) {
                        points.add("[" + latLonPairs[i].trim() + "," + latLonPairs[i+1].trim() + "]");
                    }
                }
                return "[" + String.join(",", points) + "]";
            } catch (Exception e) {
                // 如果解析失败，回退到原始的分割逻辑
                // 记录日志 e.printStackTrace(); (生产环境应使用日志框架)
            }
        }
        // 原始的分割处理逻辑（兼容“lon,lat|lon,lat”格式）
        // 同时清理掉可能存在的单个方括号
        String cleanedArea = trimmedArea.replaceAll("[\\[\\]]", "");
        StringBuilder sb = new StringBuilder("[");
        String[] pointArray = cleanedArea.split("\\|");

        for (int i = 0; i < pointArray.length; i++) {
            String point = pointArray[i].trim();
            if (!point.isEmpty()) {
                String[] coordinates = point.split(",");
                if (coordinates.length >= 2) {
                    sb.append("[")
                            .append(coordinates[0].trim()).append(",")
                            .append(coordinates[1].trim()).append("]");
                    if (i < pointArray.length - 1) {
                        sb.append(",");
                    }
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 获取卡片统计数据
     */
    private FenceMgmtChartRespVO.CardDataVO getCardData() {
        FenceMgmtMapper.CardDataDTO dto = fenceMgmtMapper.selectChartCardData();

        FenceMgmtChartRespVO.CardDataVO cardData = new FenceMgmtChartRespVO.CardDataVO();
        if (dto != null) {
            cardData.setFenceCount(dto.getFenceCount() != null ? dto.getFenceCount() : 0);
            cardData.setAlarmCount(dto.getAlarmCount() != null ? dto.getAlarmCount() : 0);
        } else {
            cardData.setFenceCount(0);
            cardData.setAlarmCount(0);
        }

        return cardData;
    }

    /**
     * 转换状态值为中文文本
     */
    private String getStatusText(String status) {
        if (status == null) {
            return "未知";
        }

        // 根据实际业务状态转换
        switch (status) {
            case "1":
                return "未生效";
            case "0":
                return "已禁用";
            case "2":
                return "已生效"; // 根据之前的接口文档
            default:
                return status; // 如果数据库存储的是中文，直接返回
        }
    }

}