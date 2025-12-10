package cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.faulthdl;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlRespVO;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.faulthdl.vo.FaultHdlUpdateReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.urban.dashboard.topic.munifac.urbancomp.UrbanCompDO;
import cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.faulthdl.FaultHdlMapper;
import cn.iocoder.yudao.module.industry.dal.mysql.urban.dashboard.topic.munifac.urbancomp.UrbanCompMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.MNG_COMP_NOT_EXISTS;

/**
 * 市政设施专题-标记故障处置完成 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 FaultHdlService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
@Slf4j
public class FaultHdlServiceImpl implements FaultHdlService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private FaultHdlMapper faultHdlMapper;

    @Resource
    private UrbanCompMapper urbanCompMapper;

    /**
     * 查询市政设施专题-标记故障处置完成数据
     *
     * @param faultHdlQueryReqVO 查询条件 VO 对象
     * @return FaultHdlRespVO 查询结果 VO 对象
     */
    @Override
    public FaultHdlRespVO getFaultHdl(FaultHdlQueryReqVO faultHdlQueryReqVO) {
        // 调用 Mapper 方法查询数据库并返回结果
        return faultHdlMapper.getFaultHdl(faultHdlQueryReqVO);
    }

    /**
     * 将指定管理部件的状态标记为“完好”
     * <p>
     * - 查询管理部件是否存在，不存在抛出 MNG_COMP_NOT_EXISTS 异常
     * - 仅允许将“破损”状态的部件标记为“完好”，否则抛出自定义异常
     * - 更新数据库，并在更新失败或异常时回滚事务
     * - 使用 @Transactional(rollbackFor = Exception.class) 确保事务回滚
     *
     * @param faultHdlUpdateReqVO 包含 mngCompId 的请求对象
     */
    @Transactional(rollbackFor = Exception.class) // 事务回滚，捕获到任何异常都会回滚
    @Override
    public void markFaultHandled(FaultHdlUpdateReqVO faultHdlUpdateReqVO) {
        try {
            // 1. 根据 mng_comp_id 查询对应的部件
            UrbanCompDO urbanCompDO = urbanCompMapper.selectOne(
                    new LambdaQueryWrapper<UrbanCompDO>()
                            .eq(UrbanCompDO::getMngCompId, faultHdlUpdateReqVO.getMngCompId())
            );

            if (urbanCompDO == null) {
                throw exception(MNG_COMP_NOT_EXISTS);
            }
            if (!urbanCompDO.getCompStatus().equals("破损")){
                throw exception(new ErrorCode(500, "该管理部件不是破损状态"));
            }

            // 2. 修改状态为完好
            urbanCompDO.setCompStatus("完好");

            // 3. 写回数据库
            urbanCompMapper.update(
                    urbanCompDO,
                    new LambdaUpdateWrapper<UrbanCompDO>()
                            .eq(UrbanCompDO::getMngCompId, faultHdlUpdateReqVO.getMngCompId())
            );
        } catch (Exception e) {
            // 打印日志
            log.error("标记故障处理失败, mngCompId={}", faultHdlUpdateReqVO.getMngCompId(), e);
            // 手动抛出异常以触发事务回滚
            throw e;
        }
    }

}
