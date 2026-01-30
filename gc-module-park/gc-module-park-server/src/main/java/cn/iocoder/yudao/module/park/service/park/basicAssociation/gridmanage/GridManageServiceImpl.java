package cn.iocoder.yudao.module.park.service.park.basicAssociation.gridmanage;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo.GridManagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo.GridManageSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.gridmanage.GridManageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.gridmanage.GridManageMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 网格管理 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class GridManageServiceImpl implements GridManageService {

    @Resource
    private GridManageMapper gridManageMapper;

    @Override
    public Long createGridManage(GridManageSaveReqVO createReqVO) {
        // 插入
        GridManageDO gridManage = BeanUtils.toBean(createReqVO, GridManageDO.class);
        gridManageMapper.insert(gridManage);
        // 返回
        return gridManage.getId();
    }

    @Override
    public void updateGridManage(GridManageSaveReqVO updateReqVO) {
        // 校验存在
        validateGridManageExists(updateReqVO.getId());
        // 更新
        GridManageDO updateObj = BeanUtils.toBean(updateReqVO, GridManageDO.class);
        gridManageMapper.updateById(updateObj);
    }

    @Override
    public void deleteGridManage(Long id) {
        // 校验存在
        validateGridManageExists(id);
        // 删除
        gridManageMapper.deleteById(id);
    }

    private void validateGridManageExists(Long id) {
        if (gridManageMapper.selectById(id) == null) {
            throw exception(GRID_MANAGE_NOT_EXISTS);
        }
    }

    @Override
    public GridManageDO getGridManage(Long id) {
        return gridManageMapper.selectById(id);
    }

    @Override
    public PageResult<GridManageDO> getGridManagePage(GridManagePageReqVO pageReqVO) {
        return gridManageMapper.selectPage(pageReqVO);
    }

}