package pe.com.user.administrator.infrastructure.mapper;

import pe.com.user.administrator.domain.model.Phone;
import pe.com.user.administrator.infrastructure.out.persistence.user.PhoneJpaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneMapper {
    public static List<PhoneJpaEntity> toEntity(List<Phone> phones, String userId) {
        return phones.stream().map(phone ->
            PhoneJpaEntity.builder()
                    .userId(userId)
                    .number(phone.getNumber())
                    .cityCode(phone.getCitycode())
                    .contryCode(phone.getContrycode())
                    .build())
        .collect(Collectors.toList());
    }

    public static List<Phone> toDomain(List<PhoneJpaEntity> phoneJpaEntities) {
        return phoneJpaEntities.stream().map(phone ->
                        Phone.builder()
                                .number(phone.getNumber())
                                .citycode(phone.getCityCode())
                                .contrycode(phone.getContryCode())
                                .build())
                .collect(Collectors.toList());
    }
}
