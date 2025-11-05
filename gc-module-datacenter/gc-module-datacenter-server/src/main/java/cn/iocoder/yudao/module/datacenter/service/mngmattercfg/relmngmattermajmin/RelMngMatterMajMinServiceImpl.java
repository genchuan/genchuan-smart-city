package cn.iocoder.yudao.module.datacenter.service.mngmattercfg.relmngmattermajmin;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo.RelMngMatterMajMinPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo.RelMngMatterMajMinSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.relmngmattermajmin.RelMngMatterMajMinDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.relmngmattermajmin.RelMngMatterMajMinMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理事项大小类关联 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RelMngMatterMajMinServiceImpl implements RelMngMatterMajMinService {

    @Resource
    private RelMngMatterMajMinMapper relMngMatterMajMinMapper;

    @Override
    public Long createRelMngMatterMajMin(RelMngMatterMajMinSaveReqVO createReqVO) {
        // 插入
        RelMngMatterMajMinDO relMngMatterMajMin = BeanUtils.toBean(createReqVO, RelMngMatterMajMinDO.class);
        relMngMatterMajMinMapper.insert(relMngMatterMajMin);
        // 返回
        return relMngMatterMajMin.getId();
    }

    @Override
    public void updateRelMngMatterMajMin(RelMngMatterMajMinSaveReqVO updateReqVO) {
        // 校验存在
        validateRelMngMatterMajMinExists(updateReqVO.getId());
        // 更新
        RelMngMatterMajMinDO updateObj = BeanUtils.toBean(updateReqVO, RelMngMatterMajMinDO.class);
        relMngMatterMajMinMapper.updateById(updateObj);
    }

    @Override
    public void deleteRelMngMatterMajMin(Long id) {
        // 校验存在
        validateRelMngMatterMajMinExists(id);
        // 删除
        relMngMatterMajMinMapper.deleteById(id);
    }

    private void validateRelMngMatterMajMinExists(Long id) {
        if (relMngMatterMajMinMapper.selectById(id) == null) {
            throw exception(REL_MNG_MATTER_MAJ_MIN_NOT_EXISTS);
        }
    }

    @Override
    public RelMngMatterMajMinDO getRelMngMatterMajMin(Long id) {
        return relMngMatterMajMinMapper.selectById(id);
    }

    @Override
    public PageResult<RelMngMatterMajMinDO> getRelMngMatterMajMinPage(RelMngMatterMajMinPageReqVO pageReqVO) {
        return relMngMatterMajMinMapper.selectPage(pageReqVO);
    }

}