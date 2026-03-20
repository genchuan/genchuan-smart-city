package cn.iocoder.yudao.module.envirhealth.service.river.monitortype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitortype.MonitorTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitortype.MonitorTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.MonitorStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.MonitorTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.river.MonitorTypeMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.MONITOR_TYPE_NOT_EXISTS;

/**
 * 监测类型字典表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MonitorTypeServiceImpl implements MonitorTypeService {

    @Resource
    private MonitorTypeMapper monitorTypeMapper;

    @Override
    public Long createMonitorType(MonitorTypeSaveReqVO createReqVO) {
        // 插入
        MonitorTypeDO monitorType = BeanUtils.toBean(createReqVO, MonitorTypeDO.class);
        monitorTypeMapper.insert(monitorType);
        // 返回
        return monitorType.getId();
    }

    @Override
    public void updateMonitorType(MonitorTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateMonitorTypeExists(updateReqVO.getId());
        // 更新
        MonitorTypeDO updateObj = BeanUtils.toBean(updateReqVO, MonitorTypeDO.class);
        monitorTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonitorType(Long id) {
        // 校验存在
        validateMonitorTypeExists(id);
        // 删除
        monitorTypeMapper.deleteById(id);
    }

    private void validateMonitorTypeExists(Long id) {
        if (monitorTypeMapper.selectById(id) == null) {
            throw exception(MONITOR_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public MonitorTypeDO getMonitorType(Long id) {
        return monitorTypeMapper.selectById(id);
    }

    @Override
    public PageResult<MonitorTypeDO> getMonitorTypePage(MonitorTypePageReqVO pageReqVO) {
        return monitorTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getMonitorTypeOptions() {

        List<MonitorTypeDO> list;
        list = monitorTypeMapper.selectList(
                new LambdaQueryWrapperX<MonitorTypeDO>()
                        .eq(MonitorTypeDO::getDeleted, 0)
                        .orderByDesc(MonitorTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, monitorTypeDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(monitorTypeDO.getMonitorName());
            vo.setValue(monitorTypeDO.getMonitorTypeId());
            return vo;
        });
    }
}