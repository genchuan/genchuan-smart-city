package cn.iocoder.yudao.module.park.service.park.resource.roadsideberthmanage;

import cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo.RoadsideBerthManagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.roadsideberthmanage.vo.RoadsideBerthManageSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.roadsideberthmanage.RoadsideBerthManageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.park.dal.mysql.park.resource.roadsideberthmanage.RoadsideBerthManageMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 路测泊位管理 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class RoadsideBerthManageServiceImpl implements RoadsideBerthManageService {

    @Resource
    private RoadsideBerthManageMapper roadsideBerthManageMapper;

    @Override
    public Long createRoadsideBerthManage(RoadsideBerthManageSaveReqVO createReqVO) {
        // 插入
        RoadsideBerthManageDO roadsideBerthManage = BeanUtils.toBean(createReqVO, RoadsideBerthManageDO.class);
        roadsideBerthManageMapper.insert(roadsideBerthManage);
        // 返回
        return roadsideBerthManage.getId();
    }

    @Override
    public void updateRoadsideBerthManage(RoadsideBerthManageSaveReqVO updateReqVO) {
        // 校验存在
        validateRoadsideBerthManageExists(updateReqVO.getId());
        // 更新
        RoadsideBerthManageDO updateObj = BeanUtils.toBean(updateReqVO, RoadsideBerthManageDO.class);
        roadsideBerthManageMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoadsideBerthManage(Long id) {
        // 校验存在
        validateRoadsideBerthManageExists(id);
        // 删除
        roadsideBerthManageMapper.deleteById(id);
    }

    private void validateRoadsideBerthManageExists(Long id) {
        if (roadsideBerthManageMapper.selectById(id) == null) {
            throw exception(ROADSIDE_BERTH_MANAGE_NOT_EXISTS);
        }
    }

    @Override
    public RoadsideBerthManageDO getRoadsideBerthManage(Long id) {
        return roadsideBerthManageMapper.selectById(id);
    }

    @Override
    public PageResult<RoadsideBerthManageDO> getRoadsideBerthManagePage(RoadsideBerthManagePageReqVO pageReqVO) {
        return roadsideBerthManageMapper.selectPage(pageReqVO);
    }

}