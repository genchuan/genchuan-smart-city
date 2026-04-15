package cn.iocoder.yudao.module.stationresource.service.stationresource.stationmgmt.stationinfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.StationInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.StationInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.ops.StationInfoCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.ops.StationInfoUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics.CardDataRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics.StationInfoChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics.StationMapRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.statistics.TypeCountBarRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.areamgmt.areainfo.AreaInfoMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.stationinfo.StationInfoMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 场站信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class StationInfoServiceImpl implements StationInfoService {

    @Resource
    private StationInfoMapper stationInfoMapper;

    @Resource
    private AreaInfoMapper areaInfoMapper;

    @Override
    public StationInfoChartRespVO getStationInfoChart() {
        // ===================== 1. 获取当前租户所有有效场站（已过滤删除）=====================
        LambdaQueryWrapper<StationInfoDO> queryWrapper = new LambdaQueryWrapper<StationInfoDO>()
                .eq(StationInfoDO::getDeleted, false);
        List<StationInfoDO> allList = stationInfoMapper.selectList(queryWrapper);

        if (CollUtil.isEmpty(allList)) {
            return new StationInfoChartRespVO();
        }

        // ===================== 【新增】批量获取片区经纬度 =====================
        // 1. 提取所有 areaId
        List<Long> areaIds = allList.stream()
                .map(StationInfoDO::getAreaId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // 2. 批量查询片区（你自己的 areaInfoMapper）
        List<AreaInfoDO> areaList = areaInfoMapper.selectBatchIds(areaIds);

        // 3. 转成 Map<areaId, AreaInfoDO> 方便快速获取
        Map<Long, AreaInfoDO> areaMap = areaList.stream()
                .collect(Collectors.toMap(AreaInfoDO::getId, area -> area));

        // ===================== 2. 地图数据（从片区取经纬度）=====================
        List<StationMapRespVO> mapList = allList.stream().map(doInfo -> {
            StationMapRespVO map = new StationMapRespVO();
            map.setId(doInfo.getId());
            map.setName(doInfo.getName());
            map.setStatus(doInfo.getStatus());

            // ====== 核心：从片区取经纬度 ======
            AreaInfoDO area = areaMap.get(doInfo.getAreaId());
            if (area != null) {
                map.setLon(area.getLon());    // 片区经度
                map.setLat(area.getLat());    // 片区纬度
            } else {
                // 无片区时给默认值，避免地图报错
                map.setLon(0.0);
                map.setLat(0.0);
            }
            return map;
        }).collect(Collectors.toList());

        // ===================== 3. 类型统计柱状图 =====================
        Map<String, Long> typeMap = allList.stream()
                .collect(Collectors.groupingBy(StationInfoDO::getType, Collectors.counting()));

        List<TypeCountBarRespVO> barList = typeMap.entrySet().stream().map(entry -> {
            TypeCountBarRespVO bar = new TypeCountBarRespVO();
            bar.setName(entry.getKey());
            bar.setValue(entry.getValue());
            return bar;
        }).collect(Collectors.toList());

        // ===================== 4. 卡片统计 =====================
        CardDataRespVO card = new CardDataRespVO();
        card.setTotalStationCount((long) allList.size());
        // 正常运营 = 已生效
        long normal = allList.stream()
                .filter(s -> "已生效".equals(s.getStatus()))
                .count();
        card.setNormalOperateCount(normal);

        // ===================== 5. 封装返回 =====================
        StationInfoChartRespVO resp = new StationInfoChartRespVO();
        resp.setStationMapList(mapList);
        resp.setTypeCountBarList(barList);
        resp.setCardData(card);
        return resp;
    }
    @Override
    public Long createStationInfo(StationInfoSaveReqVO createReqVO) {
        // 插入
        StationInfoDO stationInfo = BeanUtils.toBean(createReqVO, StationInfoDO.class);
        stationInfoMapper.insert(stationInfo);

        // 返回
        return stationInfo.getId();
    }

    @Override
    public void updateStationInfo(StationInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateStationInfoExists(updateReqVO.getId());
        // 更新
        StationInfoDO updateObj = BeanUtils.toBean(updateReqVO, StationInfoDO.class);
        stationInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteStationInfo(Long id) {
        // 校验存在
        validateStationInfoExists(id);
        // 删除
        stationInfoMapper.deleteById(id);
    }

    @Override
        public void deleteStationInfoListByIds(List<Long> ids) {
        // 删除
        stationInfoMapper.deleteByIds(ids);
        }


    private void validateStationInfoExists(Long id) {
        if (stationInfoMapper.selectById(id) == null) {
            throw exception(STATION_INFO_NOT_EXISTS);
        }
    }

    @Override
    public StationInfoDO getStationInfo(Long id) {
        return stationInfoMapper.selectById(id);
    }

    @Override
    public PageResult<StationInfoDO> getStationInfoPage(StationInfoPageReqVO pageReqVO) {
        return stationInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public Long addStationInfo(StationInfoCreateReqVO reqVO) {
        // 1. 校验场站编号唯一
        validateStationNoUnique(null, reqVO.getStationNo());

        // 2. 构造 DO
        StationInfoDO entity = BeanUtil.copyProperties(reqVO, StationInfoDO.class);

        // 3. 默认状态：未生效
        entity.setStatus("未生效");
        // 绑定数默认 0
        entity.setDeviceCount(0);
        entity.setSpaceCount(0);

        //4.绑定时间、绑定人
        Long userId = getLoginUserId();
        entity.setBindUserId(userId);
        entity.setBindTime(LocalDateTime.now());

        // 4. 插入数据库
        stationInfoMapper.insert(entity);
        return entity.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImportRespVO importStationInfo(MultipartFile file, boolean updateSupport) {
        ImportRespVO resp = new ImportRespVO();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            // 【VrvExcelUtils 导入】和你完全一致
            Map<String, Object> resultMap = VrvExcelUtils.importExcelAndReturnEntity(
                    file,
                    StationInfoCreateReqVO.class.getName()
            );

            // 获取导入列表
            List<StationInfoCreateReqVO> reqList = (List<StationInfoCreateReqVO>) resultMap.get("entityList");

            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }

            int successCount = 0;
            List<ImportRespVO.ImportFailure> failures = new ArrayList<>();

            // 遍历处理
            for (int i = 0; i < reqList.size(); i++) {
                StationInfoCreateReqVO req = reqList.get(i);
                int rowIndex = i + 2;

                try {
                    if (updateSupport) {
                        updateStationInfoIfExists(req); // 存在则更新，不存在新增
                    } else {
                        addStationInfo(req); // 仅新增
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

    @Override
    public void updateStationStatus(List<Long> ids, String status) {
        stationInfoMapper.update(null,
                new LambdaUpdateWrapper<StationInfoDO>()
                        .in(StationInfoDO::getId, ids)
                        .set(StationInfoDO::getStatus, status)
        );
    }

    @Override
    public void updateStation(StationInfoUpdateReqVO reqVO) {
        // 1. 校验存在
        StationInfoDO oldStation = validateStationExists(reqVO.getId());
        // 2. 校验编号唯一（排除自己）
        validateStationNoUnique(reqVO.getId(), reqVO.getStationNo());

        // 3. 构造更新 DO
        StationInfoDO updateObj = BeanUtil.copyProperties(reqVO, StationInfoDO.class);

        // 4. 执行更新
        stationInfoMapper.updateById(updateObj);
    }

    /**
     * 校验场站信息是否存在
     */
    private StationInfoDO validateStationExists(Long id) {
        StationInfoDO stationInfo = stationInfoMapper.selectById(id);
        if (stationInfo == null) {
            throw exception("场站信息不存在");
        }
        return stationInfo;
    }

    /**
     * 存在则更新，不存在则新增（按 stationNo 判断）
     */
    private void updateStationInfoIfExists(StationInfoCreateReqVO req) {
        // 按 场站编号 判断是否已存在
        StationInfoDO existing = stationInfoMapper.selectOne(new LambdaQueryWrapper<StationInfoDO>()
                .eq(StationInfoDO::getStationNo, req.getStationNo())
                .last("LIMIT 1")
        );

        if (existing == null) {
            // 不存在 → 新增
            addStationInfo(req);
        } else {
            // 存在 → 更新
            StationInfoDO updateEntity = new StationInfoDO();
            BeanUtil.copyProperties(req, updateEntity);
            updateEntity.setId(existing.getId());
            stationInfoMapper.updateById(updateEntity);
        }
    }


    /**
     * 校验场站编号唯一
     */
    private void validateStationNoUnique(Long id, String stationNo) {
        StationInfoDO stationInfo = stationInfoMapper.selectOne(
                new LambdaQueryWrapper<StationInfoDO>()
                        .eq(StationInfoDO::getStationNo, stationNo)
        );
        if (stationInfo == null) {
            return;
        }
        // 如果是更新，则 id 不同才重复
        if (id == null || !stationInfo.getId().equals(id)) {
            throw exception("场站编号已存在");
        }
    }

}
