package ir.mehdihosseini.basicframework.base.service;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.infrastructure.BasicInfrastructureService;
import ir.mehdihosseini.basicframework.base.mapper.BasicMapper;
import ir.mehdihosseini.basicframework.base.service.dto.BasicDtoService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * پیاده‌سازی پایه‌ای سرویس‌های DTO.
 * از Mapper برای تبدیل بین DTO و Entity استفاده می‌کند و عملیات پایه‌ای Infrastructure را فراخوانی می‌کند.
 * کلاس‌های فرزند می‌توانند متدهای خاص موجودیت را اضافه کنند.
 *<p></p>
 * Base DTO service implementation.
 * Uses a Mapper to convert between DTO and Entity, and calls basic infrastructure operations.
 * Child classes can add entity-specific methods.
 *
 * @param <ENTITY> نوع Entity
 * @param <ID> نوع شناسه Entity
 * @param <DTO> نوع DTO
 * @param <MAPPER> نوع Mapper بین Entity و DTO
 * @param <INFRA> نوع Infrastructure Service مربوطه
 */
public abstract class AbstractDtoService<ENTITY extends BasicEntity<ID>, ID, DTO,
        MAPPER extends BasicMapper<ENTITY, DTO>,
        INFRA extends BasicInfrastructureService<ENTITY, ID>>
        implements BasicDtoService<DTO> {

    protected MAPPER mapper;
    protected INFRA infrastructureService;

    public AbstractDtoService(MAPPER mapper, INFRA infrastructureService) {
        this.mapper = mapper;
        this.infrastructureService = infrastructureService;
    }

    @Override
    public DTO save(DTO dto) {
        return mapper.toDto(infrastructureService.save(mapper.toEntity(dto)));
    }

    @Override
    public List<DTO> saveList(List<DTO> listDto) {
        if (listDto == null || listDto.isEmpty())
            return null;

        return listDto.stream()
                .map(this::save)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<DTO> findAll() {
        List<ENTITY> allEntity = infrastructureService.findAll();
        return (allEntity == null || allEntity.isEmpty()) ? null : mapper.toDto(allEntity);
    }

}
