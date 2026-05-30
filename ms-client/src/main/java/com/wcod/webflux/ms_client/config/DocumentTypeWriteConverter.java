package com.wcod.webflux.ms_client.config;


import com.wcod.webflux.ms_client.model.DocumentType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class DocumentTypeWriteConverter implements Converter<DocumentType, String> {

    @Override
    public String convert(DocumentType documentType) {
        return documentType.name();
    }
}
