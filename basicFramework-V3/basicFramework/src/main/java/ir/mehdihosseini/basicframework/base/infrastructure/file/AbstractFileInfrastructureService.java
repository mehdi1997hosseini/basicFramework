package ir.mehdihosseini.basicframework.base.infrastructure.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import ir.mehdihosseini.basicframework.base.exceptionHandler.exception.AppRunTimeException;
import ir.mehdihosseini.basicframework.base.exceptionHandler.type.BasicInternalSystemExceptionType;
import ir.mehdihosseini.basicframework.base.utils.NumberUtils;
import org.springframework.context.annotation.Lazy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * پیاده‌سازی پایه‌ای Infrastructure با ذخیره‌سازی روی فایل.
 * عملیات پایه‌ای مانند save, findById, findAll ارائه شده و داده‌ها به صورت JSON در فایل ذخیره می‌شوند.
 * کلاس‌های فرزند باید متد getEntityClass را برای مشخص کردن نوع موجودیت پیاده‌سازی کنند.
 * <p></p>
 * Base file-based infrastructure implementation.
 * Provides basic operations like save, findById, findAll and stores data as JSON in files.
 * Child classes must implement getEntityClass() to specify the entity type.
 *
 * @param <ENTITY> نوع موجودیت
 * @param <ID>     نوع شناسه موجودیت
 */
@Lazy
public abstract class AbstractFileInfrastructureService<ENTITY extends BasicEntity<ID>, ID>
        implements BasicFileInfrastructureService<ENTITY, ID> {

    private static final String DEFAULT_PATH_FILE = "data/";
    private static final String DEFAULT_NAME_FILE = "data.txt";
    private static final String HEADER = "=== File Header ===";

    protected final Path dirPathFile;
    protected final Path filePath;
    protected final ObjectMapper objectMapper;

    protected AbstractFileInfrastructureService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.dirPathFile = Path.of(DEFAULT_PATH_FILE);
        this.filePath = dirPathFile.resolve(DEFAULT_NAME_FILE);
        initialize();
        System.out.println("BasicFileRepositoryImpl Bean Created !!!!");
    }

    private void initialize() {
        try {
            File file = new File(DEFAULT_PATH_FILE);
            if (!file.isDirectory()) {
                file.mkdirs();
            }

            if (!Files.exists(dirPathFile)) {
                Files.createDirectories(dirPathFile);
                System.out.println("directory created");
            }

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("file created");
                Files.write(filePath, (HEADER + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE);
            }

            System.out.println("file exists");
        } catch (IOException e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INFRASTRUCTURE_FILE_PROCESS, "Failed to initialize file");
//            throw new RuntimeException("Failed to initialize file" + e.getMessage());
        }
    }

    private String addToFile(String object) {
        try {
            Files.write(filePath, (object + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            return object;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ENTITY save(ENTITY entity) {
        String typeName = getEntityClass().getSuperclass().getGenericSuperclass().getTypeName();
        System.out.println(" getEntityClass().getSuperclass().getGenericSuperclass().getTypeName()" + typeName);
        entity.setId((ID) NumberUtils.generateUniqueLongNumber());
        try {
            addToFile(objectMapper.writeValueAsString(entity));
            return entity;
        } catch (JsonProcessingException e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INFRASTRUCTURE_FILE_PROCESS_ADD_IN_FILE, e.getMessage());
        }
    }

    @Override
    public ENTITY findById(ID id) {
        for (ENTITY entity : findAll()) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    public List<ENTITY> findAll() {
        try {
            List<String> readAllLines = Files.readAllLines(filePath);
            readAllLines.remove(0);
            List<ENTITY> result = new ArrayList<>();
            for (String line : readAllLines) {
                result.add(objectMapper.readValue(line, getEntityClass()));
            }
            return result;
        } catch (IOException e) {
            throw new AppRunTimeException(BasicInternalSystemExceptionType.INFRASTRUCTURE_FILE_PROCESS_FIND_ALL_FROM_FILE, e.getMessage());
        }
    }

    @Override
    public Boolean softDeleted(ENTITY entity) {
        if (entity == null || entity.getId() == null) return false;

        List<ENTITY> entities = findAll();
        boolean deleted = false;
        for (ENTITY ENTITY : entities) {
            if (ENTITY.getId().equals(entity.getId())) {
                ENTITY.setIsDelete(true);
                deleted = true;
                break;
            }
        }

        if (deleted) {
            try {
                // بازنویسی کامل فایل
                List<String> lines = new ArrayList<>();
                lines.add(HEADER);
                for (ENTITY ENTITY : entities) {
                    lines.add(objectMapper.writeValueAsString(ENTITY));
                }
                Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException ex) {
                throw new AppRunTimeException(BasicInternalSystemExceptionType.INFRASTRUCTURE_FILE_PROCESS_DELETE_LINE_FROM_FILE, "Failed to soft delete entity: " + ex.getMessage());
            }
        }

        return deleted;
    }

    protected abstract Class<ENTITY> getEntityClass();

}
