package cn.iocoder.yudao.module.datacenter.service.grid.data.griddataqualck;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo.GridDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo.GridDataQualCkSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.griddataqualck.GridDataQualCkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.griddataqualck.GridDataQualCkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 网格数据质量检查 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class GridDataQualCkServiceImpl implements GridDataQualCkService {

    @Resource
    private GridDataQualCkMapper gridDataQualCkMapper;

    @Override
    public Long createGridDataQualCk(GridDataQualCkSaveReqVO createReqVO) {
        // 插入
        GridDataQualCkDO gridDataQualCk = BeanUtils.toBean(createReqVO, GridDataQualCkDO.class);
        gridDataQualCkMapper.insert(gridDataQualCk);
        // 返回
        return gridDataQualCk.getId();
    }

    @Override
    public void updateGridDataQualCk(GridDataQualCkSaveReqVO updateReqVO) {
        // 校验存在
        validateGridDataQualCkExists(updateReqVO.getId());
        // 更新
        GridDataQualCkDO updateObj = BeanUtils.toBean(updateReqVO, GridDataQualCkDO.class);
        gridDataQualCkMapper.updateById(updateObj);
    }

    @Override
    public void deleteGridDataQualCk(Long id) {
        // 校验存在
        validateGridDataQualCkExists(id);
        // 删除
        gridDataQualCkMapper.deleteById(id);
    }

    private void validateGridDataQualCkExists(Long id) {
        if (gridDataQualCkMapper.selectById(id) == null) {
            throw exception(GRID_DATA_QUAL_CK_NOT_EXISTS);
        }
    }

    @Override
    public GridDataQualCkDO getGridDataQualCk(Long id) {
        return gridDataQualCkMapper.selectById(id);
    }

    @Override
    public PageResult<GridDataQualCkDO> getGridDataQualCkPage(GridDataQualCkPageReqVO pageReqVO) {
        return gridDataQualCkMapper.selectPage(pageReqVO);
    }

}