package fr.grapidee.application.utilitaire.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, Long> {

	@Override
	public Long convertToDatabaseColumn(Boolean attribute) {
		if (attribute != null && attribute) {
			return 1L;
		} else {
			return 0L;
		}
	}

	@Override
	public Boolean convertToEntityAttribute(Long dbData) {
		if (null != dbData) {
			return dbData.longValue() == 1L;
		} else {
			return false;
		}
	}
}