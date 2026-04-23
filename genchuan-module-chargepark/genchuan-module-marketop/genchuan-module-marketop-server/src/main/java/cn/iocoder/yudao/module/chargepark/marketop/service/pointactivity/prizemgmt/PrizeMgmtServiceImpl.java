package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.prizemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtImportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PrizeMgmtMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.PrizeMgmtStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PrizeMgmtServiceImpl implements PrizeMgmtService {

    @Resource
    private PrizeMgmtMapper prizeMgmtMapper;

    @Override
    public PageResult<PrizeMgmtDO> getPage(PrizeMgmtPageReqVO reqVO) {
        return prizeMgmtMapper.selectPage(reqVO);
    }

    @Override
    public PrizeMgmtDO get(Long id) {
        return prizeMgmtMapper.selectById(id);
    }

    @Override
    public Long create(PrizeMgmtCreateReqVO reqVO) {
        validateNameUnique(null, reqVO.getName());
        PrizeMgmtDO prizeMgmt = BeanUtils.toBean(reqVO, PrizeMgmtDO.class);
        prizeMgmt.setStatus(PrizeMgmtStatusEnum.NORMAL.getValue());
        prizeMgmt.setSendCount(0);
        prizeMgmtMapper.insert(prizeMgmt);
        return prizeMgmt.getId();
    }

    @Override
    public void update(PrizeMgmtUpdateReqVO reqVO) {
        validateExists(reqVO.getId());
        if (reqVO.getName() != null) {
            validateNameUnique(reqVO.getId(), reqVO.getName());
        }
        PrizeMgmtDO updateObj = BeanUtils.toBean(reqVO, PrizeMgmtDO.class);
        prizeMgmtMapper.updateById(updateObj);
    }

    @Override
    public void enable(Long id) {
        PrizeMgmtDO prizeMgmt = validateExists(id);
        if (!Objects.equals(PrizeMgmtStatusEnum.DISABLED.getValue(), prizeMgmt.getStatus())) {
            throw exception(PRIZE_MGMT_NOT_EXISTS);
        }
        prizeMgmt.setStatus(PrizeMgmtStatusEnum.NORMAL.getValue());
        prizeMgmtMapper.updateById(prizeMgmt);
    }

    @Override
    public void disable(Long id) {
        PrizeMgmtDO prizeMgmt = validateExists(id);
        if (!Objects.equals(PrizeMgmtStatusEnum.NORMAL.getValue(), prizeMgmt.getStatus())) {
            throw exception(PRIZE_MGMT_NOT_EXISTS);
        }
        prizeMgmt.setStatus(PrizeMgmtStatusEnum.DISABLED.getValue());
        prizeMgmtMapper.updateById(prizeMgmt);
    }

    @Override
    public PrizeMgmtChartRespVO getChart() {
        // TypeList: 按 type 分组统计数量
        List<Map<String, Object>> typeCountList = prizeMgmtMapper.selectTypeCountList();
        List<PrizeMgmtChartRespVO.TypeCountItem> typeList = typeCountList.stream().map(m -> {
            PrizeMgmtChartRespVO.TypeCountItem item = new PrizeMgmtChartRespVO.TypeCountItem();
            item.setType((String) m.get("type"));
            item.setCount(((Number) m.get("count")).intValue());
            return item;
        }).toList();
        PrizeMgmtChartRespVO respVO = new PrizeMgmtChartRespVO();
        respVO.setPrizeCount(0);
        respVO.setSendCount(0);
        respVO.setTypeList(typeList);
        return respVO;
    }

    @Override
    public void importPrizeMgmtList(List<PrizeMgmtImportExcelVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (PrizeMgmtImportExcelVO excelVO : list) {
            validateNameUnique(null, excelVO.getName());
            PrizeMgmtDO prizeMgmt = BeanUtils.toBean(excelVO, PrizeMgmtDO.class);
            prizeMgmt.setStatus(PrizeMgmtStatusEnum.NORMAL.getValue());
            prizeMgmt.setSendCount(0);
            prizeMgmtMapper.insert(prizeMgmt);
        }
    }

    private PrizeMgmtDO validateExists(Long id) {
        PrizeMgmtDO prizeMgmt = prizeMgmtMapper.selectById(id);
        if (prizeMgmt == null) {
            throw exception(PRIZE_MGMT_NOT_EXISTS);
        }
        return prizeMgmt;
    }

    private void validateNameUnique(Long id, String name) {
        PrizeMgmtDO existing = prizeMgmtMapper.selectOne(PrizeMgmtDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(PRIZE_MGMT_NAME_EXISTS);
        }
    }

}
