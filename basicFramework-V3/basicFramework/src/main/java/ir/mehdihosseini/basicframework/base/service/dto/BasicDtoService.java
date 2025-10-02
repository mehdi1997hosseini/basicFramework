package ir.mehdihosseini.basicframework.base.service.dto;

import java.util.List;

/**
 * اینترفیس پایه‌ای برای سرویس‌های DTO.
 * عملیات پایه‌ای برای تبدیل DTO و Entity مانند save، saveList و findAll را ارائه می‌دهد.
 *<p></p>
 * Base interface for DTO services.
 * Provides basic methods for DTO-Entity transformation such as save, saveList, and findAll.
 *
 * @param <DTO> نوع DTO
 */
public interface BasicDtoService<DTO> {

    DTO save(DTO dto);

    List<DTO> saveList(List<DTO> listDto);

    List<DTO> findAll();

}
