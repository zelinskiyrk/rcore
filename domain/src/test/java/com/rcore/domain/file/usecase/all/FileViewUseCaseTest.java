package com.rcore.domain.file.usecase.all;

import com.rcore.domain.file.FileAppConfig;
import com.rcore.domain.file.entity.FileEntity;
import com.rcore.domain.file.exception.FileAccessException;
import com.rcore.domain.file.exception.FileNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.Optional;

import static org.junit.Assert.*;

public class FileViewUseCaseTest {

    private final FileAppConfig fileAppConfig = new FileAppConfig();
    private FileEntity fileEntity;

    @Before
    public void setUp() throws Exception {
        fileEntity = fileAppConfig.getFileConfig().all.createUseCase().create(new File(fileAppConfig.getFILE_PATH()));
    }

    @Test
    public void findById() throws FileAccessException {
        Optional<FileEntity> file = fileAppConfig.getFileConfig().all.viewUseCase().findById(fileEntity.getId());
        Assert.assertTrue(file.isPresent());
        Assert.assertEquals(fileEntity.getId(), file.get().getId());
    }

    @Test
    public void getInputStream() throws FileNotFoundException, FileAccessException {
        Optional<InputStream> inputStream = fileAppConfig.getFileConfig().all.viewUseCase().getInputStream(fileEntity.getId());
        Assert.assertTrue(inputStream.isPresent());
    }
}