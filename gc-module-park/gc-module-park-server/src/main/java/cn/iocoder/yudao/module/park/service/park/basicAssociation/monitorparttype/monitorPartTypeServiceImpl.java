package cn.iocoder.yudao.module.park.service.park.basicAssociation.monitorparttype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo.monitorPartTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.monitorparttype.vo.monitorPartTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.monitorparttype.monitorPartTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.monitorparttype.monitorPartTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 监测部件类别 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class monitorPartTypeServiceImpl implements monitorPartTypeService {

    @Resource
    private monitorPartTypeMapper monitorPartTypeMapper;

    @Override
    public Long createmonitorPartType(monitorPartTypeSaveReqVO createReqVO) {
        // 插入
        monitorPartTypeDO monitorPartType = BeanUtils.toBean(createReqVO, monitorPartTypeDO.class);
        monitorPartTypeMapper.insert(monitorPartType);
        // 返回
        return monitorPartType.getId();
    }

    @Override
    public void updatemonitorPartType(monitorPartTypeSaveReqVO updateReqVO) {
        // 校验存在
        validatemonitorPartTypeExists(updateReqVO.getId());
        // 更新
        monitorPartTypeDO updateObj = BeanUtils.toBean(updateReqVO, monitorPartTypeDO.class);
        monitorPartTypeMapper.updateById(updateObj);
    }

    @Override
    public void deletemonitorPartType(Long id) {
        // 校验存在
        validatemonitorPartTypeExists(id);
        // 删除
        monitorPartTypeMapper.deleteById(id);
    }

    private void validatemonitorPartTypeExists(Long id) {
        if (monitorPartTypeMapper.selectById(id) == null) {
            throw exception(MONITOR_PART_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public monitorPartTypeDO getmonitorPartType(Long id) {
        return monitorPartTypeMapper.selectById(id);
    }

    @Override
    public PageResult<monitorPartTypeDO> getmonitorPartTypePage(monitorPartTypePageReqVO pageReqVO) {
        return monitorPartTypeMapper.selectPage(pageReqVO);
    }

}