package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.prizemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PrizeMgmtMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;

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
        prizeMgmt.setStatus("正常状态");
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
        if (!"禁用状态".equals(prizeMgmt.getStatus())) {
            throw exception(PRIZE_MGMT_NOT_EXISTS);
        }
        prizeMgmt.setStatus("正常状态");
        prizeMgmtMapper.updateById(prizeMgmt);
    }

    @Override
    public void disable(Long id) {
        PrizeMgmtDO prizeMgmt = validateExists(id);
        if (!"正常状态".equals(prizeMgmt.getStatus())) {
            throw exception(PRIZE_MGMT_NOT_EXISTS);
        }
        prizeMgmt.setStatus("禁用状态");
        prizeMgmtMapper.updateById(prizeMgmt);
    }

    @Override
    public PrizeMgmtChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑
        PrizeMgmtChartRespVO respVO = new PrizeMgmtChartRespVO();
        respVO.setPrizeCount(0);
        respVO.setSendCount(0);
        respVO.setTypeList(new ArrayList<>());
        return respVO;
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
