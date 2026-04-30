package cn.iocoder.yudao.module.inspectop.service.fencemgmt;

import cn.hutool.core.collection.CollUtil;
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

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

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
    public Long createFenceMgmt(FenceMgmtSaveReqVO createReqVO) {
        // 插入
        FenceMgmtDO fenceMgmt = BeanUtils.toBean(createReqVO, FenceMgmtDO.class);
        fenceMgmtMapper.insert(fenceMgmt);

        // 返回
        return fenceMgmt.getId();
    }

    @Override
    public void updateFenceMgmt(FenceMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateFenceMgmtExists(updateReqVO.getId());
        // 更新
        FenceMgmtDO updateObj = BeanUtils.toBean(updateReqVO, FenceMgmtDO.class);
        fenceMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteFenceMgmt(Long id) {
        // 校验存在
        validateFenceMgmtExists(id);
        // 删除
        fenceMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteFenceMgmtListByIds(List<Long> ids) {
        // 删除
        fenceMgmtMapper.deleteByIds(ids);
        }


    private void validateFenceMgmtExists(Long id) {
        if (fenceMgmtMapper.selectById(id) == null) {
            throw exception(FENCE_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public FenceMgmtDO getFenceMgmt(Long id) {
        return fenceMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<FenceMgmtRespVO> getFenceMgmtPage(FenceMgmtPageReqVO pageReqVO) {
        // 创建 MyBatis-Plus 分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<FenceMgmtRespVO> mpPage
                = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用 Mapper 的关联查询方法
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<FenceMgmtRespVO> resultPage =
                fenceMgmtMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 构造返回结果
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
     * 从"118.5865,24.9132|118.5880,24.9100"转换为"[[118.5865,24.9132],[118.5880,24.9100]]"
     */
    private String formatArea(String area) {
        if (area == null || area.trim().isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        String[] pointArray = area.split("\\|");

        for (int i = 0; i < pointArray.length; i++) {
            String point = pointArray[i].trim();
            if (!point.isEmpty()) {
                String[] coordinates = point.split(",");
                if (coordinates.length >= 2) {
                    sb.append("[").append(coordinates[0].trim()).append(",")
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