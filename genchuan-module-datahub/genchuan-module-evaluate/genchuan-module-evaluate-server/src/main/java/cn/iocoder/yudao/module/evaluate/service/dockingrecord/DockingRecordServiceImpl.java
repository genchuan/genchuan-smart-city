package cn.iocoder.yudao.module.evaluate.service.dockingrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo.DockingRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.dockingrecord.vo.DockingRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.dockingrecord.DockingRecordDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.dockingrecord.DockingRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.DOCKING_RECORD_NOT_EXISTS;

/**
 * 系统对接记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DockingRecordServiceImpl implements DockingRecordService {

    @Resource
    private DockingRecordMapper dockingRecordMapper;

    @Override
    public Long createDockingRecord(DockingRecordSaveReqVO createReqVO) {
        // 插入
        DockingRecordDO dockingRecord = BeanUtils.toBean(createReqVO, DockingRecordDO.class);
        dockingRecordMapper.insert(dockingRecord);
        // 返回
        return dockingRecord.getId();
    }

    @Override
    public void updateDockingRecord(DockingRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateDockingRecordExists(updateReqVO.getId());
        // 更新
        DockingRecordDO updateObj = BeanUtils.toBean(updateReqVO, DockingRecordDO.class);
        dockingRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteDockingRecord(Long id) {
        // 校验存在
        validateDockingRecordExists(id);
        // 删除
        dockingRecordMapper.deleteById(id);
    }

    private void validateDockingRecordExists(Long id) {
        if (dockingRecordMapper.selectById(id) == null) {
            throw exception(DOCKING_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public DockingRecordDO getDockingRecord(Long id) {
        return dockingRecordMapper.selectById(id);
    }

    @Override
    public PageResult<DockingRecordDO> getDockingRecordPage(DockingRecordPageReqVO pageReqVO) {
        return dockingRecordMapper.selectPage(pageReqVO);
    }

}