package cn.iocoder.yudao.module.smartcity.service.componentinformation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation.vo.ComponentInformationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.componentinformation.vo.ComponentInformationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.componentinformation.ComponentInformationDO;
import cn.iocoder.yudao.module.smartcity.dal.mysql.componentinformation.ComponentInformationMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.COMPONENT_INFORMATION_NOT_EXISTS;

/**
 * 部件信息 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ComponentInformationServiceImpl implements ComponentInformationService {

    @Resource
    private ComponentInformationMapper componentInformationMapper;

    @Override
    public Long createComponentInformation(ComponentInformationSaveReqVO createReqVO) {
        // 插入
        ComponentInformationDO componentInformation = BeanUtils.toBean(createReqVO, ComponentInformationDO.class);
        componentInformationMapper.insert(componentInformation);
        // 返回
        return componentInformation.getId();
    }

    @Override
    public void updateComponentInformation(ComponentInformationSaveReqVO updateReqVO) {
        // 校验存在
        validateComponentInformationExists(updateReqVO.getId());
        // 更新
        ComponentInformationDO updateObj = BeanUtils.toBean(updateReqVO, ComponentInformationDO.class);
        componentInformationMapper.updateById(updateObj);
    }

    @Override
    public void deleteComponentInformation(Long id) {
        // 校验存在
        validateComponentInformationExists(id);
        // 删除
        componentInformationMapper.deleteById(id);
    }

    private void validateComponentInformationExists(Long id) {
        if (componentInformationMapper.selectById(id) == null) {
            throw exception(COMPONENT_INFORMATION_NOT_EXISTS);
        }
    }

    @Override
    public ComponentInformationDO getComponentInformation(Long id) {
        return componentInformationMapper.selectById(id);
    }

    @Override
    public PageResult<ComponentInformationDO> getComponentInformationPage(ComponentInformationPageReqVO pageReqVO) {
        return componentInformationMapper.selectPage(pageReqVO);
    }

}