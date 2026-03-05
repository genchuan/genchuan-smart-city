package cn.iocoder.yudao.module.facility.service.road.roadwarn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn.vo.RoadWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn.vo.RoadWarnPageRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn.vo.WarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn.vo.WarnSaveReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadwarn.RoadWarnDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.roadwarn.RoadWarnMapper;
import cn.iocoder.yudao.module.facility.service.syswarn.SysWarnService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.WARN_NOT_EXISTS;

/**
 * 预警 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RoadWarnServiceImpl implements RoadWarnService {

    @Resource
    private RoadWarnMapper roadWarnMapper;

    @Resource
    private SysWarnService sysWarnService;


    @Override
    public Long createWarn(SysWarnSaveReqVO createReqVO) {
        // 插入
//        SysWarnDO warn = BeanUtils.toBean(createReqVO, SysWarnDO.class);
//        roadWarnMapper.insert(warn);
        //调用通用预警接口
        Long warnId = sysWarnService.createSysWarn(createReqVO);

        // 返回
        return warnId;
    }

    @Override
    public void updateWarn(WarnSaveReqVO updateReqVO) {
        // 校验存在
        validateWarnExists(updateReqVO.getId());
        // 更新
        RoadWarnDO updateObj = BeanUtils.toBean(updateReqVO, RoadWarnDO.class);
        roadWarnMapper.updateById(updateObj);
    }

    @Override
    public void deleteWarn(Long id) {
        // 校验存在
        validateWarnExists(id);
        // 删除
        roadWarnMapper.deleteById(id);
    }

    private void validateWarnExists(Long id) {
        if (roadWarnMapper.selectById(id) == null) {
            throw exception(WARN_NOT_EXISTS);
        }
    }

    @Override
    public RoadWarnDO getWarn(Long id) {
        return roadWarnMapper.selectById(id);
    }

    @Override
    public PageResult<RoadWarnDO> getWarnPage(WarnPageReqVO pageReqVO) {
        return roadWarnMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<RoadWarnPageRespVO> pageRoadWarn(RoadWarnPageReqVO reqVO) {
        System.out.println("cs2026-03-03 15:53:46:" + reqVO);
        PageResult<SysWarnRespVO> sysWarnPage = sysWarnService.getSysWarnPage(reqVO);


        PageResult<RoadWarnPageRespVO> roadWarn = BeanUtils.toBean(sysWarnPage, RoadWarnPageRespVO.class);

        return roadWarn;
    }

}
