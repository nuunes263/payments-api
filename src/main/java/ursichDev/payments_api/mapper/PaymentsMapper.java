package ursichDev.payments_api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ursichDev.payments_api.dto.PaymentsRequestDTO;
import ursichDev.payments_api.model.Payments;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentsMapper {

    @Mapping(target = "payment_id", ignore = true)
    Payments toEntity(PaymentsRequestDTO input);

    /**
     * Campos nulos no DTO sao ignorados: o valor que ja esta na base e preservado.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "payment_id", ignore = true)
    void updateEntityFromDto(PaymentsRequestDTO input, @MappingTarget Payments payment);
}
