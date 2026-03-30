package cn.iocoder.yudao.module.park.service.park.basicAssociation.apptype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo.AppTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.apptype.vo.AppTypeSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.apptype.AppTypeDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.apptype.AppTypeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.APP_TYPE_NOT_EXISTS;

/**
 * 行业应用类别 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class AppTypeServiceImpl implements AppTypeService {

    @Resource
    private AppTypeMapper appTypeMapper;

    @Override
    public Long createAppType(AppTypeSaveReqVO createReqVO) {
        // 插入
        AppTypeDO appType = BeanUtils.toBean(createReqVO, AppTypeDO.class);
        appTypeMapper.insert(appType);
        // 返回
        return appType.getId();
    }

    @Override
    public void updateAppType(AppTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateAppTypeExists(updateReqVO.getId());
        // 更新
        AppTypeDO updateObj = BeanUtils.toBean(updateReqVO, AppTypeDO.class);
        appTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteAppType(Long id) {
        // 校验存在
        validateAppTypeExists(id);
        // 删除
        appTypeMapper.deleteById(id);
    }

    private void validateAppTypeExists(Long id) {
        if (appTypeMapper.selectById(id) == null) {
            throw exception(APP_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public AppTypeDO getAppType(Long id) {
        return appTypeMapper.selectById(id);
    }

    @Override
    public PageResult<AppTypeDO> getAppTypePage(AppTypePageReqVO pageReqVO) {
        return appTypeMapper.selectPage(pageReqVO);
    }

}
