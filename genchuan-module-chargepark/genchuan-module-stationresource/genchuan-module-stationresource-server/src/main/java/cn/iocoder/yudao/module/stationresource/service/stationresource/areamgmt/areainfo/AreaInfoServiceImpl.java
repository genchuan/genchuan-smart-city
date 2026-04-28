package cn.iocoder.yudao.module.stationresource.service.stationresource.areamgmt.areainfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.AddReq;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.AreaInfoUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.ops.ImportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics.AreaChartCardVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics.AreaInfoChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics.AreaMapItemVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.statistics.StationCountBarItemVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.areamgmt.areainfo.AreaInfoMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import cn.iocoder.yudao.module.stationresource.vrv.utils.procom.address.AddressToLatLonUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beust.ah.A;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUser;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

/**
 * 片区信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AreaInfoServiceImpl implements AreaInfoService {

    @Resource
    private AreaInfoMapper areaInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArea(AreaInfoUpdateReqVO reqVO) {
        // 1. 校验存在
        AreaInfoDO areaInfo = validateAreaInfoDOExists(reqVO.getId());

        // 2. 校验片区编号唯一（排除自己）
        AreaInfoDO byAreaNo = areaInfoMapper.selectOne(new LambdaQueryWrapper<AreaInfoDO>()
                .eq(AreaInfoDO::getAreaNo, reqVO.getAreaNo())
                .ne(AreaInfoDO::getId, reqVO.getId())
                .last("LIMIT 1")
        );
        if (byAreaNo != null) {
            throw exception("片区编号已存在:"+ reqVO.getAreaNo());
        }

        // 3. 构造 DO（完全匹配表结构）
        AreaInfoDO updateObj = BeanUtils.toBean(reqVO, AreaInfoDO.class);

//        updateObj.setId(reqVO.getId());
//        updateObj.setAreaNo(reqVO.getAreaNo());
//        updateObj.setName(reqVO.getName());
//        updateObj.setDistrict(reqVO.getDistrict()); // 区县
//        updateObj.setLeaderId(reqVO.getUserId());   // 负责人ID → 对应表 leader_id
//        updateObj.setPhone(reqVO.getPhone());
//        updateObj.setRemark(reqVO.getRemark());
//        updateObj.setReserve1(reqVO.getReserve1());
//        updateObj.setReserve2(reqVO.getReserve2());

        // 4. 更新
        areaInfoMapper.updateById(updateObj);
    }

    @Override
    public AreaInfoChartRespVO getAreaInfoChart() {
        AreaInfoChartRespVO resp = new AreaInfoChartRespVO();

        // ===================== 1. 查询所有有效片区（租户隔离 + 未删除） =====================
//        List<AreaInfoDO> areaList = areaInfoMapper.selectList(new LambdaQueryWrapper<AreaInfoDO>()
//                .eq(AreaInfoDO::getDeleted, false)
//                .orderByDesc(AreaInfoDO::getId)
//        );

        AreaInfoPageReqVO areaInfoPageReqVO =new AreaInfoPageReqVO();
        areaInfoPageReqVO.setPageSize(9999);
        List<AreaInfoDO> areaList = getAreaInfoPage(areaInfoPageReqVO).getList();

        //如果为空
        if (CollUtil.isEmpty(areaList)) {
            resp.setAreaMapList(Collections.emptyList());
            resp.setStationCountBarList(Collections.emptyList());
            resp.setCardData(new AreaChartCardVO());
            return resp;
        }

        // ===================== 2. 地图数据 =====================
        List<AreaMapItemVO> mapList = BeanUtils.toBean(areaList, AreaMapItemVO.class);
        // 地图默认经纬度（你可从address解析，这里先给默认值）
        mapList.forEach(item -> {
            if (item.getLon() == null) item.setLon(118.675324);
            if (item.getLat() == null) item.setLat(24.896541);
        });

        // ===================== 3. 柱状图数据 =====================
        List<StationCountBarItemVO> barList = areaList.stream().map(area -> {
            StationCountBarItemVO bar = new StationCountBarItemVO();
            bar.setAreaId(area.getId());
            bar.setName(area.getName());
            bar.setValue(area.getStationCount() == null ? 0 : area.getStationCount());
            return bar;
        }).toList();

        // ===================== 4. 卡片统计 =====================
        AreaChartCardVO card = new AreaChartCardVO();
        // 总片区数
        card.setTotalAreaCount(areaList.size());
        // 总场站数（求和）
        int totalStation = areaList.stream()
                .mapToInt(area -> area.getStationCount() == null ? 0 : area.getStationCount())
                .sum();
        card.setTotalStationCount(totalStation);

        // ===================== 封装返回 =====================
        resp.setAreaMapList(mapList);
        resp.setStationCountBarList(barList);
        resp.setCardData(card);
        return resp;
    }

    /**
     * 校验片区是否存在
     */
    private AreaInfoDO validateAreaInfoDOExists(Long id) {
        AreaInfoDO areaInfo = areaInfoMapper.selectById(id);
        if (areaInfo == null) {
            throw exception("片区信息不存在");
        }
        return areaInfo;
    }
    @Override
    public Long createAreaInfo(AreaInfoSaveReqVO createReqVO) {
        // 插入
        AreaInfoDO areaInfo = BeanUtils.toBean(createReqVO, AreaInfoDO.class);
        areaInfoMapper.insert(areaInfo);

        // 返回
        return areaInfo.getId();
    }

//    @Override
//    public void updateAreaInfo(AreaInfoSaveReqVO updateReqVO) {
//        // 校验存在
//        validateAreaInfoExists(updateReqVO.getId());
//        // 更新
//        AreaInfoDO updateObj = BeanUtils.toBean(updateReqVO, AreaInfoDO.class);
//        areaInfoMapper.updateById(updateObj);
//    }

    @Override
    public void deleteAreaInfo(Long id) {
        // 校验存在
        validateAreaInfoExists(id);
        // 删除
        areaInfoMapper.deleteById(id);
    }

    @Override
        public void deleteAreaInfoListByIds(List<Long> ids) {
        // 删除
        areaInfoMapper.deleteByIds(ids);
        }


    private void validateAreaInfoExists(Long id) {
        if (areaInfoMapper.selectById(id) == null) {
            throw exception(AREA_INFO_NOT_EXISTS);
        }
    }

    /**
     * 根据ID查询单条片区信息（复用分页接口，保证数据结构一致：包含实时场站数、负责人名称）
     * @param id 片区ID
     * @return 片区详情DO
     */
    @Override
    public AreaInfoDO getAreaInfo(Long id) {
        // 构造分页查询条件，只查当前ID，限制1条
        AreaInfoPageReqVO req = new AreaInfoPageReqVO();
        req.setId(id);
        req.setPageSize(1);

        System.out.println("cs2026-04-28 15:03:34:"+req);
        // 调用分页接口获取列表，返回第一条数据（保证与列表展示的字段一致）
        return getAreaInfoPage(req).getList().stream().findFirst().orElse(null);
    }

    @Override
    public PageResult<AreaInfoDO> getAreaInfoPage(AreaInfoPageReqVO pageReqVO) {
        Page<AreaInfoDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        Page<AreaInfoDO> resultPage = areaInfoMapper.getPage(page, pageReqVO);
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addAreaInfo(AddReq createReqVO) {
        // 插入
        AreaInfoDO areaInfo = BeanUtils.toBean(createReqVO, AreaInfoDO.class);
        areaInfo.setStatus("未生效");

        // ==================== 新增：后端自动赋值 ====================
        // 1. 设置负责人 userId（和 leaderId 保持一致，你也可以改成当前登录人）
        Long userId = getLoginUserId();
        areaInfo.setUserId(userId);
        // 2. 设置绑定时间（当前时间）
        areaInfo.setBindTime(LocalDateTime.now());
        // 3. 设置绑定人ID（当前登录用户ID，标准芋道写法）
        areaInfo.setBindUserId(getLoginUserId());

        // ==================== 【核心：经纬度判断逻辑】 ====================
        // 如果前端没传 lon / lat  → 自动计算
        if (areaInfo.getLon() == null || areaInfo.getLat() == null) {
            double[] latLon = AddressToLatLonUtil.getLatLon(
                    areaInfo.getProvince(),
                    areaInfo.getCity(),
                    areaInfo.getDistrict()
            );
            areaInfo.setLon(latLon[0]);
            areaInfo.setLat(latLon[1]);
        }

        // 片区编号唯一性校验
        LambdaQueryWrapper<AreaInfoDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AreaInfoDO::getAreaNo, areaInfo.getAreaNo());
        if (areaInfoMapper.exists(wrapper)) {
            throw exception("片区编号不能和数据库存在的一样");
        }

        areaInfoMapper.insert(areaInfo);
        // 返回
        return areaInfo.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ImportRespVO importAreaInfo(MultipartFile file,boolean updateSupport) {
        ImportRespVO resp = new ImportRespVO();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            // ===================== 【完整使用 VrvExcelUtils】 =====================
            Map<String, Object> resultMap = VrvExcelUtils.importExcelAndReturnEntity(
                    file,
                    AddReq.class.getName()
            );

            // 获取实体列表
            List<AddReq> reqList = (List<AddReq>) resultMap.get("entityList");

            // 判空
            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }

            int successCount = 0;
            List<ImportRespVO.ImportFailure> failures = new ArrayList<>();

            // 遍历导入
            for (int i = 0; i < reqList.size(); i++) {
                AddReq req = reqList.get(i);
                int rowIndex = i + 2; // Excel 真实行号

                try {
                    // ==============================================
                    // 支持更新 / 仅新增 二合一逻辑
                    // ==============================================
                    if (updateSupport) {
                        updateAreaInfoIfExists(req); // 存在则更新，不存在则新增
                    } else {
                        addAreaInfo(req); // 仅新增（重复直接报错）
                    }
                    successCount++;

                } catch (Exception e) {
                    // 记录失败
                    ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
                    failure.setRowIndex(rowIndex);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            // 封装返回
            resp.setSuccessCount(successCount);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;

        } catch (Exception e) {
            // 文件解析异常
            ImportRespVO.ImportFailure failure = new ImportRespVO.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }




    /**
     * 存在则更新，不存在则新增（你自己实现判断依据）
     */
    /**
     * 存在则更新，不存在则新增
     */
    private void updateAreaInfoIfExists(AddReq req) {
        // 1. 根据 areaNo 查询是否存在
        AreaInfoDO existing = areaInfoMapper.selectOne(new LambdaQueryWrapper<AreaInfoDO>()
                .eq(AreaInfoDO::getAreaNo, req.getAreaNo())
                .eq(AreaInfoDO::getDeleted,0)
                .last("LIMIT 1")
        );

        if (existing == null) {
            // 不存在 → 新增
            addAreaInfo(req);
        } else {
            // ===================== 纯 MP 原生更新 =====================
            AreaInfoDO updateEntity = new AreaInfoDO();

            // 把 Excel 里的字段 复制到 DO
            BeanUtil.copyProperties(req, updateEntity);

            // 必须设置 ID（WHERE 条件）
            updateEntity.setId(existing.getId());

            // 直接调用 MyBatis-Plus 原生方法更新
            areaInfoMapper.updateById(updateEntity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAreaInfoStatus(List<Long> ids, boolean enabled) {
        if (CollUtil.isEmpty(ids)) {
            throw exception("片区ID列表不能为空");
        }
        areaInfoMapper.update(null, new LambdaUpdateWrapper<AreaInfoDO>()
                .in(AreaInfoDO::getId, ids)
                .set(AreaInfoDO::getStatus, enabled ? "已生效" : "已禁用")
        );
    }
}
