package com.wzd.model.mapper;

import java.util.List;
import java.util.Map;

import com.wzd.model.entity.Entryform;
import com.wzd.web.dto.entryForm.EntryFormDto;

public interface EntryformMapper extends MyMapper<Entryform> {

	List<EntryFormDto> getSignList(Map<String, Object> map);
}