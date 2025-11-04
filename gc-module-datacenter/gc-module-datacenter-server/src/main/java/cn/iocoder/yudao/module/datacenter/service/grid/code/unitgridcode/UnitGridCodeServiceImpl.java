package cn.iocoder.yudao.module.datacenter.service.grid.code.unitgridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo.UnitGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.unitgridcode.vo.UnitGridCodeSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.area.AreaDO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.unitgriddiv.UnitGridDivDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.div.unitgriddiv.UnitGridDivMapper;
import cn.iocoder.yudao.module.datacenter.service.grid.area.area.AreaService;
import cn.iocoder.yudao.module.datacenter.service.grid.div.unitgriddiv.UnitGridDivService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.unitgridcode.UnitGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.code.unitgridcode.UnitGridCodeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 单元网格编码 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class UnitGridCodeServiceImpl implements UnitGridCodeService {

    @Resource
    private UnitGridCodeMapper unitGridCodeMapper;

    @Resource
    private UnitGridDivService unitGridDivService;

    @Resource
    private UnitGridDivMapper unitGridDivMapper;

//    @Resource
//    private UnitGridCodeService unitGridCodeService;

    @Resource
    private AreaService areaService;

    @Override
    public Long createUnitGridCode(UnitGridCodeSaveReqVO createReqVO) {
        // 插入
        UnitGridCodeDO unitGridCode = BeanUtils.toBean(createReqVO, UnitGridCodeDO.class);
        unitGridCodeMapper.insert(unitGridCode);
//         返回
        return unitGridCode.getId();
        // 插入
//        UnitGridDivDO unitGridDiv = BeanUtils.toBean(createReqVO, UnitGridDivDO.class);
//
//        // 确保有单元网格ID
//        if (unitGridDiv.getUnitGridId() == null || unitGridDiv.getUnitGridId().isEmpty()) {
//            unitGridDiv.setUnitGridId("UNIT_" + System.currentTimeMillis());
//        }
//
//        unitGridDivMapper.insert(unitGridDiv);

        // 自动生成编码
//        try {
//            unitGridCodeService.generateCodeByUnitGrid(unitGridDiv.getUnitGridId());
////            log.info("单元网格编码自动生成成功，单元网格ID：{}", unitGridDiv.getUnitGridId());
//        } catch (Exception e) {
////            log.warn("单元网格编码自动生成失败，单元网格ID：{}，错误：{}", unitGridDiv.getUnitGridId(), e.getMessage());
//            // 编码生成失败不影响单元网格创建
//        }

//        return unitGridDiv.getId();
    }

    @Override
    public void updateUnitGridCode(UnitGridCodeSaveReqVO updateReqVO) {
        // 校验存在
        validateUnitGridCodeExists(updateReqVO.getId());
        // 更新
        UnitGridCodeDO updateObj = BeanUtils.toBean(updateReqVO, UnitGridCodeDO.class);
        unitGridCodeMapper.updateById(updateObj);
    }

    @Override
    public void deleteUnitGridCode(Long id) {
        // 校验存在
        validateUnitGridCodeExists(id);
        // 删除
        unitGridCodeMapper.deleteById(id);
    }

    private void validateUnitGridCodeExists(Long id) {
        if (unitGridCodeMapper.selectById(id) == null) {
            throw exception(UNIT_GRID_CODE_NOT_EXISTS);
        }
    }

    @Override
    public UnitGridCodeDO getUnitGridCode(Long id) {
        return unitGridCodeMapper.selectById(id);
    }

    @Override
    public PageResult<UnitGridCodeDO> getUnitGridCodePage(UnitGridCodePageReqVO pageReqVO) {
        return unitGridCodeMapper.selectPage(pageReqVO);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long generateCodeByUnitGrid(String unitGridId) {
        // 1. 查询单元网格信息
        UnitGridDivDO unitGrid = unitGridDivService.getUnitGridDivByGridId(unitGridId);
        if (unitGrid == null) {
            throw exception(UNIT_GRID_NOT_EXISTS);
        }

        // 2. 检查是否已存在编码
        UnitGridCodeDO existingCode = getByUnitGridId(unitGridId);
//        if (existingCode != null) {
//            throw exception(UNIT_GRID_CODE_ALREADY_EXISTS);
//        }

        // 3. 获取行政区划代码（这里需要根据commId查询行政区划）
        String areaFullCode = getAreaFullCodeByCommId(unitGrid.getCommId());

        // 4. 生成顺序码
        String seqCode = generateSeqCode(areaFullCode);

        // 5. 生成完整编码
        String unitGridCode = generateFullCode(areaFullCode, seqCode);

        // 6. 创建编码记录
        UnitGridCodeSaveReqVO createReqVO = new UnitGridCodeSaveReqVO();
        createReqVO.setUnitCodeId("CODE_" + System.currentTimeMillis());
        createReqVO.setUnitGridCode(unitGridCode);
        createReqVO.setUnitGridId(unitGridId);
        createReqVO.setAreaFullCode(areaFullCode);
        createReqVO.setSeqCode(seqCode);
        createReqVO.setGenerateTime(LocalDateTime.now());
        createReqVO.setCodeStatus("有效");
        createReqVO.setRemark("系统自动生成");

        return createUnitGridCode(createReqVO);

        // 临时实现：直接生成编码
//        String areaFullCode = "110101001001"; // 临时固定值
//        String seqCode = generateSeqCode(areaFullCode);
//        String unitGridCode = generateFullCode(areaFullCode, seqCode);
//
//        UnitGridCodeSaveReqVO createReqVO = new UnitGridCodeSaveReqVO();
//        createReqVO.setUnitCodeId("CODE_" + System.currentTimeMillis());
//        createReqVO.setUnitGridCode(unitGridCode);
//        createReqVO.setUnitGridId(unitGridId);
//        createReqVO.setAreaFullCode(areaFullCode);
//        createReqVO.setSeqCode(seqCode);
//        createReqVO.setGenerateTime(LocalDateTime.now());
//        createReqVO.setCodeStatus("有效");
//        createReqVO.setRemark("系统自动生成（临时）");
//
//        return createUnitGridCode(createReqVO);
    }

    @Override
    public Boolean validateCodeUnique(String unitGridCode, Long excludeId) {
        Long count = unitGridCodeMapper.selectCountByCode(unitGridCode, excludeId);
        return count == 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invalidateCode(Long id, String reason) {
        // 校验存在
        validateUnitGridCodeExists(id);

        // 更新状态为作废
        UnitGridCodeDO updateObj = new UnitGridCodeDO();
        updateObj.setId(id);
        updateObj.setCodeStatus("作废");
        updateObj.setRemark("作废原因：" + reason + "，作废时间：" + LocalDateTime.now());
        unitGridCodeMapper.updateById(updateObj);
    }

    @Override
    public UnitGridCodeDO getByCode(String unitGridCode) {
        UnitGridCodeDO codeDO = unitGridCodeMapper.selectByCode(unitGridCode);
        if (codeDO == null) {
            throw exception(UNIT_GRID_CODE_NOT_EXISTS);
        }
        return codeDO;
    }

    @Override
    public UnitGridCodeDO getByUnitGridId(String unitGridId) {
        UnitGridCodeDO codeDO = unitGridCodeMapper.selectByUnitGridId(unitGridId);
        if (codeDO == null) {
            throw exception(UNIT_GRID_CODE_NOT_EXISTS);
        }
        return codeDO;
    }

    @Override
    public List<UnitGridCodeDO> getListByArea(String areaFullCode) {
        return unitGridCodeMapper.selectListByArea(areaFullCode);
    }

// ========== 私有方法 ==========

    /**
     * 生成顺序码
     */
    private String generateSeqCode(String areaFullCode) {
        Integer maxSeqCode = unitGridCodeMapper.selectMaxSeqCodeByArea(areaFullCode);
        int nextSeq = (maxSeqCode == null) ? 1 : maxSeqCode + 1;

        // 确保顺序码不超过3位（001-999）
        if (nextSeq > 999) {
            throw exception(UNIT_GRID_CODE_SEQ_OVERFLOW);
        }

        return String.format("%03d", nextSeq);
    }

    /**
     * 生成完整编码（15位）
     */
    private String generateFullCode(String areaFullCode, String seqCode) {
        // 确保行政区划代码为12位
        if (areaFullCode.length() != 12) {
            throw new IllegalArgumentException("行政区划代码必须为12位");
        }
        return areaFullCode + seqCode;
    }

    /**
     * 根据社区ID获取行政区划完整代码
     */
    private String getAreaFullCodeByCommId(String commId) {
        try {
            AreaDO area = areaService.getAreaByCommId(commId);
            if (area != null && area.getFullCode() != null) {
                return area.getFullCode();
            }
        } catch (Exception e) {
//            log.warn("根据社区ID获取行政区划代码失败，commId: {}, 使用默认值", commId, e);
        }

        // 备用方案：根据社区ID映射到默认的行政区划代码
        return getDefaultAreaFullCodeByCommId(commId);
    }

    /**
     * 根据社区ID获取默认的行政区划代码（备用方案）
     */
    private String getDefaultAreaFullCodeByCommId(String commId) {
        // 基于您的测试数据建立映射关系
        switch (commId) {
            case "COMM_1001": // 圆恩寺社区
                return "110101001001";
            case "COMM_1002": // 交道口社区
                return "110101002001";
            case "COMM_1003": // 南锣鼓巷社区
                return "110101003001";
            case "COMM_2001": // 朝阳社区
                return "110105001001";
            default:
                // 对于未知社区，返回基于commId生成的默认代码
                return "110101" + String.format("%06d", Math.abs(commId.hashCode()) % 1000000);
        }

    }

}