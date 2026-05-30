package com.wcod.webflux.ms_client.config;

import com.wcod.webflux.ms_client.model.DocumentType;
import org.jspecify.annotations.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class DocumentTypeReadConverter implements Converter<String, DocumentType> {
    @Override
    public DocumentType convert(String source) {
        return DocumentType.valueOf(source);
    }
}
