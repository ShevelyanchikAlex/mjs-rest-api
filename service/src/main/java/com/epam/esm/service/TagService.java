package com.epam.esm.service;

import com.epam.esm.dto.TagDto;

import java.util.List;

public interface TagService {
    long save(TagDto tagDto);

    TagDto findById(long id);

    List<TagDto> findAll();

    TagDto findByName(String name);

    int delete(long id);
}