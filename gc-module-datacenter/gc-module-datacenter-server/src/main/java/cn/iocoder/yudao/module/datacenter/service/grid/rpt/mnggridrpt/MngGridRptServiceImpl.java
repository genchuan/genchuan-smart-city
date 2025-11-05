package cn.iocoder.yudao.module.datacenter.service.grid.rpt.mnggridrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt.vo.MngGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.mnggridrpt.vo.MngGridRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.mnggridrpt.MngGridRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.rpt.mnggridrpt.MngGridRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理网格统计 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class MngGridRptServiceImpl implements MngGridRptService {

    @Resource
    private MngGridRptMapper mngGridRptMapper;

    @Override
    public Long createMngGridRpt(MngGridRptSaveReqVO createReqVO) {
        // 插入
        MngGridRptDO mngGridRpt = BeanUtils.toBean(createReqVO, MngGridRptDO.class);
        mngGridRptMapper.insert(mngGridRpt);
        // 返回
        return mngGridRpt.getId();
    }

    @Override
    public void updateMngGridRpt(MngGridRptSaveReqVO updateReqVO) {
        // 校验存在
        validateMngGridRptExists(updateReqVO.getId());
        // 更新
        MngGridRptDO updateObj = BeanUtils.toBean(updateReqVO, MngGridRptDO.class);
        mngGridRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteMngGridRpt(Long id) {
        // 校验存在
        validateMngGridRptExists(id);
        // 删除
        mngGridRptMapper.deleteById(id);
    }

    private void validateMngGridRptExists(Long id) {
        if (mngGridRptMapper.selectById(id) == null) {
            throw exception(MNG_GRID_RPT_NOT_EXISTS);
        }
    }

    @Override
    public MngGridRptDO getMngGridRpt(Long id) {
        return mngGridRptMapper.selectById(id);
    }

    @Override
    public PageResult<MngGridRptDO> getMngGridRptPage(MngGridRptPageReqVO pageReqVO) {
        return mngGridRptMapper.selectPage(pageReqVO);
    }

}