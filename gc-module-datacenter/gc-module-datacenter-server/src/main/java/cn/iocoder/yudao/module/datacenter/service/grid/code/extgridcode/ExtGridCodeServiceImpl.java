package cn.iocoder.yudao.module.datacenter.service.grid.code.extgridcode;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo.ExtGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.extgridcode.vo.ExtGridCodeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.extgridcode.ExtGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.code.extgridcode.ExtGridCodeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 扩展网格编码 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class ExtGridCodeServiceImpl implements ExtGridCodeService {

    @Resource
    private ExtGridCodeMapper extGridCodeMapper;

    @Override
    public Long createExtGridCode(ExtGridCodeSaveReqVO createReqVO) {
        // 插入
        ExtGridCodeDO extGridCode = BeanUtils.toBean(createReqVO, ExtGridCodeDO.class);
        extGridCodeMapper.insert(extGridCode);
        // 返回
        return extGridCode.getId();
    }

    @Override
    public void updateExtGridCode(ExtGridCodeSaveReqVO updateReqVO) {
        // 校验存在
        validateExtGridCodeExists(updateReqVO.getId());
        // 更新
        ExtGridCodeDO updateObj = BeanUtils.toBean(updateReqVO, ExtGridCodeDO.class);
        extGridCodeMapper.updateById(updateObj);
    }

    @Override
    public void deleteExtGridCode(Long id) {
        // 校验存在
        validateExtGridCodeExists(id);
        // 删除
        extGridCodeMapper.deleteById(id);
    }

    private void validateExtGridCodeExists(Long id) {
        if (extGridCodeMapper.selectById(id) == null) {
            throw exception(EXT_GRID_CODE_NOT_EXISTS);
        }
    }

    @Override
    public ExtGridCodeDO getExtGridCode(Long id) {
        return extGridCodeMapper.selectById(id);
    }

    @Override
    public PageResult<ExtGridCodeDO> getExtGridCodePage(ExtGridCodePageReqVO pageReqVO) {
        return extGridCodeMapper.selectPage(pageReqVO);
    }

}