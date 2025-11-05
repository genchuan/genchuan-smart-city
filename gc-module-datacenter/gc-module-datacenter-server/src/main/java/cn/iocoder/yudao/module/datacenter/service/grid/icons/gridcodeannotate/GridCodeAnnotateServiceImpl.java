package cn.iocoder.yudao.module.datacenter.service.grid.icons.gridcodeannotate;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo.GridCodeAnnotatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo.GridCodeAnnotateSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridcodeannotate.GridCodeAnnotateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.icons.gridcodeannotate.GridCodeAnnotateMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 网格标识码注记配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GridCodeAnnotateServiceImpl implements GridCodeAnnotateService {

    @Resource
    private GridCodeAnnotateMapper gridCodeAnnotateMapper;

    @Override
    public Long createGridCodeAnnotate(GridCodeAnnotateSaveReqVO createReqVO) {
        // 插入
        GridCodeAnnotateDO gridCodeAnnotate = BeanUtils.toBean(createReqVO, GridCodeAnnotateDO.class);
        gridCodeAnnotateMapper.insert(gridCodeAnnotate);
        // 返回
        return gridCodeAnnotate.getId();
    }

    @Override
    public void updateGridCodeAnnotate(GridCodeAnnotateSaveReqVO updateReqVO) {
        // 校验存在
        validateGridCodeAnnotateExists(updateReqVO.getId());
        // 更新
        GridCodeAnnotateDO updateObj = BeanUtils.toBean(updateReqVO, GridCodeAnnotateDO.class);
        gridCodeAnnotateMapper.updateById(updateObj);
    }

    @Override
    public void deleteGridCodeAnnotate(Long id) {
        // 校验存在
        validateGridCodeAnnotateExists(id);
        // 删除
        gridCodeAnnotateMapper.deleteById(id);
    }

    private void validateGridCodeAnnotateExists(Long id) {
        if (gridCodeAnnotateMapper.selectById(id) == null) {
            throw exception(GRID_CODE_ANNOTATE_NOT_EXISTS);
        }
    }

    @Override
    public GridCodeAnnotateDO getGridCodeAnnotate(Long id) {
        return gridCodeAnnotateMapper.selectById(id);
    }

    @Override
    public PageResult<GridCodeAnnotateDO> getGridCodeAnnotatePage(GridCodeAnnotatePageReqVO pageReqVO) {
        return gridCodeAnnotateMapper.selectPage(pageReqVO);
    }

}