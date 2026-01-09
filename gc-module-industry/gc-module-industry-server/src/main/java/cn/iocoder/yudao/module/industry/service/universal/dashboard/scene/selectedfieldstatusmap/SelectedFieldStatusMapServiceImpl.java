package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.selectedfieldstatusmap;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo.SelectedFieldStatusMapPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.selectedfieldstatusmap.vo.SelectedFieldStatusMapSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.selectedfieldstatusmap.SelectedFieldStatusMapDO;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.selectedfieldstatusmap.SelectedFieldStatusMapMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 场景字段状态映射 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class SelectedFieldStatusMapServiceImpl implements SelectedFieldStatusMapService {

    @Resource
    private SelectedFieldStatusMapMapper selectedFieldStatusMapMapper;

    @Override
    public Long createSelectedFieldStatusMap(SelectedFieldStatusMapSaveReqVO createReqVO) {
        // 插入
        SelectedFieldStatusMapDO selectedFieldStatusMap = BeanUtils.toBean(createReqVO, SelectedFieldStatusMapDO.class);
        selectedFieldStatusMapMapper.insert(selectedFieldStatusMap);
        // 返回
        return selectedFieldStatusMap.getId();
    }

    @Override
    public void updateSelectedFieldStatusMap(SelectedFieldStatusMapSaveReqVO updateReqVO) {
        // 校验存在
        validateSelectedFieldStatusMapExists(updateReqVO.getId());
        // 更新
        SelectedFieldStatusMapDO updateObj = BeanUtils.toBean(updateReqVO, SelectedFieldStatusMapDO.class);
        selectedFieldStatusMapMapper.updateById(updateObj);
    }

    @Override
    public void deleteSelectedFieldStatusMap(Long id) {
        // 校验存在
        validateSelectedFieldStatusMapExists(id);
        // 删除
        selectedFieldStatusMapMapper.deleteById(id);
    }

    private void validateSelectedFieldStatusMapExists(Long id) {
        if (selectedFieldStatusMapMapper.selectById(id) == null) {
            throw exception(SELECTED_FIELD_STATUS_MAP_NOT_EXISTS);
        }
    }

    @Override
    public SelectedFieldStatusMapDO getSelectedFieldStatusMap(Long id) {
        return selectedFieldStatusMapMapper.selectById(id);
    }

    @Override
    public PageResult<SelectedFieldStatusMapDO> getSelectedFieldStatusMapPage(SelectedFieldStatusMapPageReqVO pageReqVO) {
        return selectedFieldStatusMapMapper.selectPage(pageReqVO);
    }

}
