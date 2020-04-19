package com.rcore.domain.database.memory.picture.port;

import com.rcore.domain.database.memory.base.port.BaseUserIdGeneratorImpl;
import com.rcore.domain.picture.port.PictureStorage;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PictureStorageImpl implements PictureStorage {

    private BaseUserIdGeneratorImpl idGenerator = new BaseUserIdGeneratorImpl();
    private Map<String, InputStream> container = new HashMap<>();

    @Override
    public String store(InputStream content, String fileName, String contentType) {
        container.put(fileName, content);
        return fileName;
    }

    @Override
    public void remove(String filePath) {
        container.keySet()
                .removeIf(id -> id.equals(filePath));
    }

    @Override
    public Optional<InputStream> getInputStream(String filePath) {
        return Optional.ofNullable(container.get(filePath));
    }
}