package cn.iocoder.yudao.module.datacenter.service.grid.div.extgriddiv;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.extgriddiv.vo.ExtGridDivPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.extgriddiv.vo.ExtGridDivSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.extgriddiv.ExtGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.div.extgriddiv.ExtGridDivMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 扩展网格划分 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class ExtGridDivServiceImpl implements ExtGridDivService {

    @Resource
    private ExtGridDivMapper extGridDivMapper;

    @Override
    public Long createExtGridDiv(ExtGridDivSaveReqVO createReqVO) {
        // 插入
        ExtGridDivDO extGridDiv = BeanUtils.toBean(createReqVO, ExtGridDivDO.class);
        extGridDivMapper.insert(extGridDiv);
        // 返回
        return extGridDiv.getId();
    }

    @Override
    public void updateExtGridDiv(ExtGridDivSaveReqVO updateReqVO) {
        // 校验存在
        validateExtGridDivExists(updateReqVO.getId());
        // 更新
        ExtGridDivDO updateObj = BeanUtils.toBean(updateReqVO, ExtGridDivDO.class);
        extGridDivMapper.updateById(updateObj);
    }

    @Override
    public void deleteExtGridDiv(Long id) {
        // 校验存在
        validateExtGridDivExists(id);
        // 删除
        extGridDivMapper.deleteById(id);
    }

    private void validateExtGridDivExists(Long id) {
        if (extGridDivMapper.selectById(id) == null) {
            throw exception(EXT_GRID_DIV_NOT_EXISTS);
        }
    }

    @Override
    public ExtGridDivDO getExtGridDiv(Long id) {
        return extGridDivMapper.selectById(id);
    }

    @Override
    public PageResult<ExtGridDivDO> getExtGridDivPage(ExtGridDivPageReqVO pageReqVO) {
        return extGridDivMapper.selectPage(pageReqVO);
    }

}