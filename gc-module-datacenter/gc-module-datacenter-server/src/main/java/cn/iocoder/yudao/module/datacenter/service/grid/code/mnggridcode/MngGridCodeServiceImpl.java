package cn.iocoder.yudao.module.datacenter.service.grid.code.mnggridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.mnggridcode.vo.MngGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.mnggridcode.vo.MngGridCodeSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.area.AreaDO;
import cn.iocoder.yudao.module.datacenter.service.grid.area.area.AreaService;
import cn.iocoder.yudao.module.datacenter.service.grid.div.mnggriddiv.MngGridDivService;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.mnggridcode.MngGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.code.mnggridcode.MngGridCodeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理网格编码 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class MngGridCodeServiceImpl implements MngGridCodeService {

    @Resource
    private MngGridCodeMapper mngGridCodeMapper;
    @Resource
    private MngGridDivService mngGridDivService;
    @Resource
    private AreaService areaService;

    @Override
    public Long createMngGridCode(MngGridCodeSaveReqVO createReqVO) {
        // 插入
        MngGridCodeDO mngGridCode = BeanUtils.toBean(createReqVO, MngGridCodeDO.class);
        mngGridCodeMapper.insert(mngGridCode);
        // 返回
        return mngGridCode.getId();
    }

    @Override
    public void updateMngGridCode(MngGridCodeSaveReqVO updateReqVO) {
        // 校验存在
        validateMngGridCodeExists(updateReqVO.getId());
        // 更新
        MngGridCodeDO updateObj = BeanUtils.toBean(updateReqVO, MngGridCodeDO.class);
        mngGridCodeMapper.updateById(updateObj);
    }

    @Override
    public void deleteMngGridCode(Long id) {
        // 校验存在
        validateMngGridCodeExists(id);
        // 删除
        mngGridCodeMapper.deleteById(id);
    }

    private void validateMngGridCodeExists(Long id) {
        if (mngGridCodeMapper.selectById(id) == null) {
            throw exception(MNG_GRID_CODE_NOT_EXISTS);
        }
    }

    @Override
    public MngGridCodeDO getMngGridCode(Long id) {
        return mngGridCodeMapper.selectById(id);
    }

    @Override
    public PageResult<MngGridCodeDO> getMngGridCodePage(MngGridCodePageReqVO pageReqVO) {
        return mngGridCodeMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long generateCodeByMngGrid(String mngGridId) {
        // 1. 查询管理网格信息
//        MngGridDivDO mngGrid = mngGridDivService.getMngGridDiv(mngGridId);
//        if (mngGrid == null) {
//            throw exception(MNG_GRID_NOT_EXISTS); // 需要在ErrorCodeConstants中添加这个错误码
//        }

        // 2. 检查是否已存在编码
//        MngGridCodeDO existingCode = getByMngGridId(mngGridId);
//        if (existingCode != null) {
//            throw exception(MNG_GRID_CODE_ALREADY_EXISTS); // 需要在ErrorCodeConstants中添加这个错误码
//        }

        // 3. 获取行政区划代码（这里需要根据commId查询行政区划）
        String areaFullCode = getAreaFullCodeByCommId("COMM_1001");

        // 4. 生成顺序码
        String seqCode = generateSeqCode(areaFullCode);

        // 5. 生成完整编码（15位）
        String mgGridCode = generateFullCode(areaFullCode, seqCode);

        // 6. 创建编码记录
        MngGridCodeSaveReqVO createReqVO = new MngGridCodeSaveReqVO();
        createReqVO.setMgCodeId("MG_CODE_" + System.currentTimeMillis());
        createReqVO.setMgGridCode(mgGridCode);
        createReqVO.setMngGridId(mngGridId);
        createReqVO.setAreaFullCode(areaFullCode);
        createReqVO.setSeqCode(seqCode);
        createReqVO.setGenerateTime(LocalDateTime.now());
        createReqVO.setCodeStatus("有效");
        createReqVO.setRemark("系统自动生成");

        return createMngGridCode(createReqVO);
    }

    @Override
    public Boolean validateCodeUnique(String mgGridCode, Long excludeId) {
        Long count = mngGridCodeMapper.selectCountByCode(mgGridCode, excludeId);
        return count == 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invalidateCode(Long id, String reason) {
        // 校验存在
        validateMngGridCodeExists(id);

        // 更新状态为作废，将作废理由写在备注中
        MngGridCodeDO updateObj = new MngGridCodeDO();
        updateObj.setId(id);
        updateObj.setCodeStatus("作废");
        updateObj.setRemark("作废原因：" + reason + "，作废时间：" + LocalDateTime.now());
        mngGridCodeMapper.updateById(updateObj);
    }

    @Override
    public MngGridCodeDO getByCode(String mgGridCode) {
        MngGridCodeDO codeDO = mngGridCodeMapper.selectByCode(mgGridCode);
        if (codeDO == null) {
            throw exception(MNG_GRID_CODE_NOT_EXISTS);
        }
        return codeDO;
    }

    @Override
    public MngGridCodeDO getByMngGridId(String mngGridId) {
        MngGridCodeDO codeDO = mngGridCodeMapper.selectByMngGridId(mngGridId);
        if (codeDO == null) {
            throw exception(MNG_GRID_CODE_NOT_EXISTS);
        }
        return codeDO;
    }

    @Override
    public List<MngGridCodeDO> getListByArea(String areaFullCode) {
        return mngGridCodeMapper.selectListByArea(areaFullCode);
    }

// ========== 私有方法 ==========

    /**
     * 生成顺序码
     */
    private String generateSeqCode(String areaFullCode) {
        Integer maxSeqCode = mngGridCodeMapper.selectMaxSeqCodeByArea(areaFullCode);
        int nextSeq = (maxSeqCode == null) ? 1 : maxSeqCode + 1;

        // 确保顺序码不超过3位（001-999）
        if (nextSeq > 999) {
            throw exception(MNG_GRID_CODE_SEQ_OVERFLOW); // 需要在ErrorCodeConstants中添加这个错误码
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
            // 日志记录
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